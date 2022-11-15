package com.archerprop.appinventorypc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.archerprop.appinventorypc.entidad.Articulos;
import com.archerprop.appinventorypc.entidad.Usuarios;
import com.archerprop.appinventorypc.services.ArticuloService;

/**
 *
 * @author Aschente
 */
@Controller
@RequestMapping("/gestor")
public class UsuarioController {

    @Autowired
    ArticuloService articuloService;

    @GetMapping("/gestor")
    public String iniciarGestor() {
        return "/gestor";
    }

    int count = 0;

    @PostMapping
    public String verificar(@ModelAttribute("articulo") Articulos articuloDTO) {
        if (!articuloService.verificar(articuloDTO)) {
            if (count > 0) {
                System.out.println("Articulo segundo");
                articuloDTO.setPrecioB(articuloDTO.getPrecioU() * articuloDTO.getStock());
                System.out.println(articuloDTO.getPrecioB());
                articuloDTO.setFechModi(new java.sql.Timestamp(new java.util.Date().getTime()));
                articuloService.crearArticulo(articuloDTO);
                count = 0;
                return "redirect:gestor?none&success";
            } else {
                count++;
                return "redirect:gestor?new";
            }
        }
        return "redirect:gestor?old";
    }

    @ModelAttribute("articulo")
    public Articulos setArticuloDTO() {
        return new Articulos();
    }

    @GetMapping
    public String obtenerGestor() {
        return "proveedor/gestor";
    }
}
