package com.proyecto.pw.servicio;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.pw.controlador.dto.UsuarioRegistroDTO;
import com.proyecto.pw.modelo.Rol;
import com.proyecto.pw.modelo.Usuario;
import com.proyecto.pw.repositorio.UsuarioRepositorio;

/**
 * Implementación del servicio de usuario que extiende UserDetailsService.
 */
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructor de UsuarioServicioImpl.
     * @param usuarioRepositorio Repositorio de usuarios.
     */
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        super();
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Guarda un usuario a partir de un DTO de registro.
     * @param registroDTO DTO que contiene la información del usuario a registrar.
     * @return El usuario guardado o null si el correo electrónico ya está en uso.
     */
    @Override
    public Usuario guardar(UsuarioRegistroDTO registroDTO) {
        String email = registroDTO.getEmail();

        // Verificar si el correo electrónico ya está en uso
        if (usuarioRepositorio.findByEmail(email) != null) {
            // Aquí podrías manejar la lógica para indicar que el correo ya está registrado
            // Puede ser lanzando una excepción, mostrando un mensaje al usuario, etc.
            // Por ejemplo, puedes lanzar una excepción personalizada, o simplemente devolver null.
            // En este caso, se devuelve null, pero podrías ajustarlo a tu manejo de errores deseado.
            return null;
        }

        // Si el correo no está en uso, proceder con el registro del usuario
        Usuario usuario = new Usuario(registroDTO.getNombre(), registroDTO.getApellido(), email,
                passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(new Rol("ROLE_USER")));

        return usuarioRepositorio.save(usuario);
    }

    /**
     * Carga un usuario por su nombre de usuario (correo electrónico) para la autenticación.
     * @param username El nombre de usuario (correo electrónico) a buscar.
     * @return Detalles del usuario encontrado para la autenticación.
     * @throws UsernameNotFoundException Excepción si el usuario no se encuentra.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    /**
     * Mapea los roles del usuario a autoridades/permisos.
     * @param roles Colección de roles del usuario.
     * @return Colección de autoridades/permisos mapeados desde los roles.
     */
    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }
}
