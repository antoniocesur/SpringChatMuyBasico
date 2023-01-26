package com.antonio.chat.repositorios;

import com.antonio.chat.modelo.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioMensajes extends JpaRepository<Mensaje, Long> {
    public Mensaje findById(long id);
}
