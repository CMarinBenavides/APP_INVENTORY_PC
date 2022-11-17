package com.archerprop.appinventorypc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.archerprop.appinventorypc.entidad.Articulos;
import com.archerprop.appinventorypc.entidad.Inventarios;
import com.archerprop.appinventorypc.entidad.Usuarios;
import com.archerprop.appinventorypc.services.ArticuloService;
import com.archerprop.appinventorypc.services.UsuarioService;

/**
 *
 * @author Aschente
 */
@Controller
public class UsuarioController {

    @Autowired
    ArticuloService articuloService;

    @Autowired
    UsuarioService usuarioService;

    int count = 0;
    boolean flag = false;

    @GetMapping("/gestor")
    public String obtenerGestor1(@RequestParam int cedula, Model model) {
        System.out.println(cedula);
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
        return "usuario/gestor";
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
            articuloDTO.setSerial(articulo.getSerial());
            articuloDTO.setNombre(articulo.getNombre());
            articuloDTO.setPrecioB(articuloDTO.getPrecioU() * articuloDTO.getStock());
            articuloDTO.setFechm(new java.sql.Timestamp(new java.util.Date().getTime()));
            articuloDTO.setProveedor(id);
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

}
