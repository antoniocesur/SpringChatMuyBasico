package com.antonio.chat.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String avatar;
    private String password;
    private String email;
    private String descripcion;
    @OneToMany(mappedBy="emisor")
    List<Mensaje> enviados;
    @OneToMany(mappedBy = "destinatario")
    List<Mensaje> recibidos;

    public Usuario(){
    }
    public Usuario(String username, String email){
        this.username=username;
        this.email=email;
    }
}
