package com.archerprop.appinventorypc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.archerprop.appinventorypc.entidad.Usuarios;
import com.archerprop.appinventorypc.services.UsuarioService;

/**
 *
 * @author Aschente
 */
@Controller
public class AppController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public String iniciar() {
        return "index";
    }

    @GetMapping("/index")
    public String iniciarIndex() {
        return "index";
    }

    @GetMapping("/")
    public String iniciarIndex2() {
        return "index";
    }

    @PostMapping("/index")
    public String autenticar(@ModelAttribute("user") Usuarios usuarioDTO) {
        if (!usuarioService.autenticar(usuarioDTO)) {
            System.out.println("Usuario no encontrado");
            System.out.println(usuarioDTO);
            return "redirect:/index?error";
        }
        Usuarios usuario = usuarioService.obtenerUsuario(usuarioDTO.getCorreo());
        System.out.println(usuario);
        int cedula = usuario.getCedula();
        return "redirect:/gestor/" + cedula + "?none";
    }

    @ModelAttribute("user")
    public Usuarios setUsuarios() {

        return new Usuarios();
    }
}
