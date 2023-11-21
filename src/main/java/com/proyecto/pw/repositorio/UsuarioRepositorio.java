package com.proyecto.pw.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.pw.modelo.Usuario;

/**
 * Repositorio para la entidad Usuario.
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    /**
     * Encuentra un usuario por su dirección de correo electrónico.
     * @param email La dirección de correo electrónico del usuario.
     * @return El usuario asociado a la dirección de correo electrónico especificada.
     */
    public Usuario findByEmail(String email);
}
