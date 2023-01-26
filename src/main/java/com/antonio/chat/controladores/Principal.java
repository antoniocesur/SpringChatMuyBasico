package com.antonio.chat.controladores;

import com.antonio.chat.servicios.ServicioMensajes;
import com.antonio.chat.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Principal {
    @Autowired
    ServicioMensajes servicioMensajes;
    @Autowired
    ServicioUsuarios servicioUsuarios;

    @GetMapping("/")
    public String principal(Model model){
        //Recupero la lista de usuarios y la mando a la plantilla
        model.addAttribute("lista", servicioUsuarios.findAll());
        return "index";
    }
}
