package com.proyecto.pw.servicio;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.proyecto.pw.controlador.dto.UsuarioRegistroDTO;
import com.proyecto.pw.modelo.Usuario;

/**
 * Interfaz para el servicio de usuarios, extiende UserDetailsService.
 */
public interface UsuarioServicio extends UserDetailsService {

    /**
     * Guarda un usuario a partir de un DTO de registro.
     * @param registroDTO DTO que contiene la información del usuario a registrar.
     * @return El usuario guardado.
     */
    public Usuario guardar(UsuarioRegistroDTO registroDTO);
}
