package com.antonio.chat.controladores;

import com.antonio.chat.modelo.Mensaje;
import com.antonio.chat.modelo.Usuario;
import com.antonio.chat.servicios.ServicioMensajes;
import com.antonio.chat.servicios.ServicioUsuarios;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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
        //Recupero la lista de usuarios y la mando a la plantilla para decir quién soy
        model.addAttribute("lista", servicioUsuarios.findAll());
        return "identificar";
    }

    @GetMapping("/destinatario")
    public String destino(Model model){
        //Recupero la lista de usuarios y la mando a la plantilla para decir quién soy
        model.addAttribute("lista", servicioUsuarios.findAll());
        return "destinatario";
    }


    @GetMapping("/elegir/{id}")
    public String elegir(@PathVariable long id, Model model, HttpSession request){
        //Selecciono y guardo en una variable de sesión el usuario actual, el que envía los mensajes
        request.setAttribute("idActual", servicioUsuarios.findById(id).getId());
        System.out.println(request.getAttribute("idActual"));
        return "redirect:/destinatario";
    }

    @GetMapping("/chat/{id}")
    public String chat(@PathVariable long id, Model model, HttpSession request){

        //Envío a la vista el usuario actual (variable de sesión) y el receptor al que le quiere enviar mensajes
        Usuario actual=servicioUsuarios.findById((long) request.getAttribute("idActual"));
        Usuario destinatario=servicioUsuarios.findById(id);
        model.addAttribute("actual", actual);  //Esto después lo "cogeremos" de Spring Security
        model.addAttribute("receptor", destinatario);

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

        //Envío un mensaje vacío que es el que después nos devolverá en PostMapping si escriben uno
        Mensaje mensaje=new Mensaje();
        mensaje.setEmisor(actual);
        mensaje.setDestinatario(destinatario);
        model.addAttribute("mensaje", new Mensaje());
        return "chat";
    }

    @PostMapping("/enviar")
    public String guardarMensaje(@ModelAttribute("mensaje") Mensaje mensaje,
                                 HttpServletRequest request, //Esto es para volver a la página desde la que nos han "llamado"
                                 @RequestParam("emisor") Long emisorid, //Es el id de quien envía el mensaje
                                 @RequestParam("destinatario") Long destinatarioid) // Es el id de quien recibe el mensaje
    {
        mensaje.setFecha(LocalDateTime.now());
        mensaje.setEmisor(servicioUsuarios.findById(emisorid));
        mensaje.setDestinatario(servicioUsuarios.findById(destinatarioid));
        servicioMensajes.save(mensaje);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
