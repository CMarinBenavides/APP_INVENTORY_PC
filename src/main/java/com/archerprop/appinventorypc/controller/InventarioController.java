package com.archerprop.appinventorypc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.archerprop.appinventorypc.services.InventarioService;
import com.archerprop.appinventorypc.services.UsuarioService;

/**
 *
 * @author Aschente
 */
@Controller
@RequestMapping("/registro")
public class InventarioController {
    @Autowired
    InventarioService inventarioService;

    // @PostMapping
    // public String registrarArticulo(@ModelAttribute("articulo") Articulos
    // articuloDTO) {
    // if (articuloService.crearArticulo(articuloDTO)) {
    // return "redirect:/registro?success";
    // } else {
    // return "redirect:/registro?error";
    // }
    // }

    // @GetMapping
    // public String obtenerRegistro() {
    // return "registro";
    // }

}
