package com.antonio.chat.repositorios;

import com.antonio.chat.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario, Long> {
    public Usuario findById(long id);
}
