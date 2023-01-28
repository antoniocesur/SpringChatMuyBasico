package com.antonio.chat.repositorios;

import com.antonio.chat.modelo.Mensaje;
import com.antonio.chat.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RepositorioMensajes extends JpaRepository<Mensaje, Long> {
    public Mensaje findById(long id);
    public List<Mensaje> findByEmisorAndDestinatario(Usuario emisor, Usuario destinatario);

    @Query(
            value = "SELECT * FROM USERS u WHERE u.status = 1",
            nativeQuery = true)
    Collection<Usuario> findAllActiveUsersNative();
}
