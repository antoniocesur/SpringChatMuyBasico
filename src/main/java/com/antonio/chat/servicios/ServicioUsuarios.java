package com.antonio.chat.servicios;

import com.antonio.chat.modelo.Usuario;
import com.antonio.chat.repositorios.RepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuarios {
    @Autowired
    RepositorioUsuarios repositorio;

    public List<Usuario> findAll(){
        return repositorio.findAll();
    }

    public Usuario findById(long id){
        return repositorio.findById(id);
    }

    public Usuario save(Usuario usuario){
        repositorio.save(usuario);
        return usuario;
    }

    public void delete(Usuario usuario){
        repositorio.delete(usuario);
    }

}
