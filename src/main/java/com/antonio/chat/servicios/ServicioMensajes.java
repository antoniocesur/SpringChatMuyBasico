package com.antonio.chat.servicios;

import com.antonio.chat.modelo.Mensaje;
import com.antonio.chat.repositorios.RepositorioMensajes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioMensajes {
    @Autowired
    RepositorioMensajes repositorio;

    public List<Mensaje> findAll(){
        return repositorio.findAll();
    }

    public Mensaje findById(long id){
        return repositorio.findById(id);
    }

    public Mensaje save(Mensaje mensaje){
        return repositorio.save(mensaje);
    }

    public void delete(Mensaje mensaje){
        repositorio.delete(mensaje);
    }

}
