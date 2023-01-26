package com.antonio.chat.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="emisor_id")
    private Usuario emisor;
    @ManyToOne
    @JoinColumn(name="destinatario_id")
    private Usuario destinatario;
    private String contenido;
    private LocalDateTime fecha;

    public Mensaje(){
        fecha=LocalDateTime.now();
    }
}
