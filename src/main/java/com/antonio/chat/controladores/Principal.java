package com.antonio.chat.controladores;

import com.antonio.chat.modelo.Mensaje;
import com.antonio.chat.modelo.Usuario;
import com.antonio.chat.servicios.ServicioMensajes;
import com.antonio.chat.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    @GetMapping("/chat/{id}")
    public String chat(@PathVariable long id, Model model){

        //Envío a la vista el usuario actual y el destinatario al que le quiere enviar mensajes
        Usuario destinatario=servicioUsuarios.findById(id);
        Usuario actual=servicioUsuarios.findByUsername("antonio");
        //Usuario actual=new Usuario("antonio2","antonio@gmail.com");
        //Usuario destinatario=new Usuario("maría1", "maria@gmail.com");
        model.addAttribute("actual", actual);
        model.addAttribute("destinatario", destinatario);

        //Debo enviar a la vista la lista de mensajes de "actual" a "destinatario" y viceversa
        List<Mensaje> lista1=servicioMensajes.findByEmisorAndDestinatario(actual, destinatario);
        List<Mensaje> lista2=servicioMensajes.findByEmisorAndDestinatario(destinatario, actual);

        //Mezclo las dos listas en una y la ordeno por fecha
        List<Mensaje> mezcla=new ArrayList<>();
        mezcla.addAll(lista1);
        mezcla.addAll(lista2);
        Collections.sort(mezcla, new Comparator<Mensaje>() {
            @Override
            public int compare(Mensaje m1, Mensaje m2) {
                return m1.getFecha().compareTo(m2.getFecha());
            }
        });
        model.addAttribute("listaMensajes", mezcla);
        return "chat";
    }
}
