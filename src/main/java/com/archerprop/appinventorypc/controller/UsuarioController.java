package com.archerprop.appinventorypc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.archerprop.appinventorypc.entidad.Articulos;
import com.archerprop.appinventorypc.entidad.Reportes;
import com.archerprop.appinventorypc.entidad.Usuarios;
import com.archerprop.appinventorypc.services.ArticuloService;
import com.archerprop.appinventorypc.services.ReporteService;
import com.archerprop.appinventorypc.services.UsuarioService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.html2pdf.HtmlConverter;

import lombok.extern.log4j.Log4j2;

/**
 *
 * @author Aschente
 */
@Log4j2
@Controller
public class UsuarioController {

    @Autowired
    ArticuloService articuloService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ReporteService reporteService;

    int count = 0;
    boolean flag = false;

    @GetMapping("/gestor")
    public String obtenerGestor1(@RequestParam(defaultValue = "0", required = false) int cedula, Model model) {
        if (cedula == 0) {
            return "redirect:/index?errorNoUser";
        }
        return "usuario/gestor";
    }

    Articulos articulo = new Articulos();

    @GetMapping("/gestor/{cedula}")
    public String obtenerGestor2(@PathVariable int cedula, Model model) {
        if (cedula == 0) {
            return "redirect:/index?errorNoUser";
        }
        if (!usuarioService.usuarioExiste(cedula)) {
            return "redirect:/index?errorNoUser";
        }
        Usuarios usuario = usuarioService.obtenerUsuarioConCedula(cedula);
        List<Articulos> articulos = articuloService.listarArticulosPorProveedor(usuario.getCedula());
        List<Articulos> articulosT = articuloService.listarArticulos();
        List<Usuarios> usuarios = usuarioService.listarProveedores();
        List<Reportes> reportes = reporteService.listarReportes();
        // hacer una lista con todos los articulos pero donde el proveedor sea el nombre
        // y apellido del usuario

        Map<Integer, String> nombresProveedores = new HashMap<Integer, String>();
        for (Usuarios u : usuarios) {
            nombresProveedores.put(u.getCedula(), u.getNombre() + " " + u.getApellido());
        }

        model.addAttribute("articulos", articulos);
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("articulosT", articulosT);
        model.addAttribute("nombreProveedor", nombresProveedores);
        model.addAttribute("reportes", reportes);
        return "usuario/gestor";
    }

    @GetMapping("/gestor/{cedula}/{restart}")
    public String obtenerGestor3(@PathVariable int cedula, @PathVariable String restart, Model model) {
        if (restart.equals("true")) {
            count = 0;
            flag = false;
        }
        return "redirect:/gestor/" + cedula + "?none";
    }

    @PostMapping("/{id}")
    public String verificar(@PathVariable("id") int id, @ModelAttribute("articulo") Articulos articuloDTO,
            Model model) {
        if (!articuloService.verificar(articuloDTO) && !flag) {
            if (count > 0) {
                flag = false;
                articuloDTO.setPrecioB(articuloDTO.getPrecioU() * articuloDTO.getStock());
                articuloDTO.setFechm(new java.sql.Timestamp(new java.util.Date().getTime()));
                articuloDTO.setProveedor(id);
                articuloService.crearArticulo(articuloDTO);
                count = 0;
                return "redirect:gestor/" + id + "?success";
            } else {
                count++;
                flag = false;
                articulo = articuloDTO;
                return "redirect:gestor/" + id + "?new";
            }
        }
        if (count > 0) {
            count = 0;
            articuloDTO.setSerial(articulo.getSerial());
            articuloDTO.setNombre(articulo.getNombre());
            articuloDTO.setFechm(new java.sql.Timestamp(new java.util.Date().getTime()));
            articuloDTO.setProveedor(id);
            Articulos articuloViejo = articuloService.obtenerArticuloPorSerial(articuloDTO.getSerial());
            if (articuloDTO.getStock() == 0) {
                articuloDTO.setStock(articuloViejo.getStock());
            }
            if (articuloDTO.getPrecioU() == 0) {
                articuloDTO.setPrecioU(articuloViejo.getPrecioU());
            }
            articuloDTO.setPrecioB(articuloDTO.getPrecioU() * articuloDTO.getStock());
            if (articuloService.modificarArticulo(articuloDTO)) {
                flag = false;
                return "redirect:gestor/" + id + "?success";
            } else {
                return "redirect:gestor/" + id + "?error";
            }
        } else {
            count++;
            flag = true;
            articulo = articuloDTO;
            return "redirect:gestor/" + id + "?old";
        }
    }

    @ModelAttribute("articulo")
    public Articulos setArticuloDTO() {
        return new Articulos();
    }

    @GetMapping("/reporte/{cedula}")
    public String obtenerReporte(@PathVariable int cedula, Model model) {
        Usuarios usuario = usuarioService.obtenerUsuarioConCedula(cedula);
        List<Articulos> articulos = articuloService.listarArticulosPorProveedor(usuario.getCedula());
        List<Articulos> articulosT = articuloService.listarArticulos();
        List<Usuarios> usuarios = usuarioService.listarProveedores();
        Reportes reporte = new Reportes();
        reporte.setNumReporte(cedula);
        reporte.setFechaR(new java.sql.Timestamp(new java.util.Date().getTime()));
        reporte.setUsuario(cedula);
        reporteService.crearReporte(reporte);
        Map<Integer, String> nombresProveedores = new HashMap<Integer, String>();
        for (Usuarios u : usuarios) {
            nombresProveedores.put(u.getCedula(), u.getNombre() + " " + u.getApellido());
        }
        model.addAttribute("articulos", articulos);
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("articulosT", articulosT);
        model.addAttribute("nombreProveedor", nombresProveedores);
        model.addAttribute("reporte", reporte);

        try {
            String ruta = "C:\\projects\\AppInventoryPC\\src\\main\\resources\\templates\\usuario";
            HtmlConverter.convertToPdf(new FileInputStream(ruta),
                    new FileOutputStream("C:\\projects\\AppInventoryPC\\src\\main\\resources\\templates\\report.pdf"));
        } catch (FileNotFoundException e) {
            log.error("Error al generar el reporte", e);
        } catch (IOException e) {
            log.error("Error al generar el reporte", e);
        }

        return "usuario/report";
    }

    @GetMapping("/reporte/{cedula}/{delete}")
    public String obtenerReporte2(@PathVariable int cedula, @PathVariable String delete, Model model) {
        Reportes reporteDTO = new Reportes();
        reporteDTO.setNumReporte(cedula);
        if (reporteService.eliminarReporte(reporteDTO)) {
            return "redirect:/gestor/" + cedula + "?successD";
        } else {
            return "redirect:/gestor/" + cedula + "?none";
        }
    }
}
