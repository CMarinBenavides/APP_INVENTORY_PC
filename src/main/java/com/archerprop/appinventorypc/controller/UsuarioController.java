package com.archerprop.appinventorypc.controller;

import java.util.List;

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

    Model globalModel;

    int count = 0;

    @GetMapping("/gestor")
    public String obtenerGestor1(@RequestParam int cedula, Model model) {
        System.out.println(cedula);
        return "usuario/gestor";
    }

    Articulos articulo = new Articulos();

    @GetMapping("/gestor/{cedula}")
    public String obtenerGestor2(@PathVariable int cedula, Model model) {
        System.out.println(cedula);
        if (cedula == 0) {
            return "redirect:/index?errorNoUser";
        }
        if (!usuarioService.usuarioExiste(cedula)) {
            return "redirect:/index?errorNoUser";
        }
        List<Articulos> articulos = articuloService.listarArticulosPorProveedor(cedula);
        Usuarios usuario = usuarioService.obtenerUsuarioConCedula(cedula);
        model.addAttribute("articulos", articulos);
        model.addAttribute("id", cedula);
        model.addAttribute("tipoP", usuario.getTipoP());
        model.addAttribute("tipoE", usuario.getTipoE());
        return "usuario/gestor";
    }

    @PostMapping("/{id}")
    public String verificar(@PathVariable("id") int id, @ModelAttribute("articulo") Articulos articuloDTO,
            Model model) {
        if (!articuloService.verificar(articuloDTO)) {
            if (count > 0) {
                System.out.println("Articulo segundo");
                articuloDTO.setPrecioB(articuloDTO.getPrecioU() * articuloDTO.getStock());
                System.out.println(articuloDTO.getPrecioB());
                articuloDTO.setFechModi(new java.sql.Timestamp(new java.util.Date().getTime()));
                articuloDTO.setProveedor(id);
                articuloService.crearArticulo(articuloDTO);
                List<Articulos> articulos = articuloService.listarArticulosPorProveedor(id);
                model.addAttribute("articulos", articulos);
                count = 0;
                return "redirect:gestor/" + id + "?success";
            } else {
                count++;
                articulo = articuloDTO;
                return "redirect:gestor/" + id + "?new";
            }
        }

        return "redirect:gestor/" + id + "?old";
    }

    @ModelAttribute("articulo")
    public Articulos setArticuloDTO() {
        return new Articulos();
    }

}
