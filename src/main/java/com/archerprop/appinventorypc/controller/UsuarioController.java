package com.archerprop.appinventorypc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/gestor/{id}")
    public String iniciarGestor2(@PathVariable String id, Model modelo) {
        int idCliente = Integer.parseInt(id);
        modelo.addAttribute("idCliente", idCliente);
        return "/gestor";
    }

    @PostMapping("/gestor/{id}")
    public String verificar(@PathVariable String id, @ModelAttribute("articulo") Articulos articuloDTO) {
        int idCliente = Integer.parseInt(id);
        if (!articuloService.verificar(articuloDTO)) {
            if (count > 0) {
                System.out.println("Articulo segundo");
                articuloDTO.setPrecioB(articuloDTO.getPrecioU() * articuloDTO.getStock());
                System.out.println(articuloDTO.getPrecioB());
                articuloDTO.setFechModi(new java.sql.Timestamp(new java.util.Date().getTime()));
                articuloDTO.setProveedor(idCliente);
                articuloService.crearArticulo(articuloDTO);
                count = 0;
                return "redirect:gestor?none&success";
            } else {
                count++;
                System.out.println(articuloDTO);
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
