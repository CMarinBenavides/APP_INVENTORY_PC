package com.archerprop.appinventorypc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Aschente
 */
@Controller
public class AppController {
    @GetMapping
    public String iniciar() {
        return "index";
    }

    @GetMapping("/index")
    public String iniciarIndex() {
        return "index";
    }
}
