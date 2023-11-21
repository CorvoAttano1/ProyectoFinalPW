package com.proyecto.pw.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.pw.controlador.dto.UsuarioRegistroDTO;
import com.proyecto.pw.modelo.Usuario;
import com.proyecto.pw.servicio.UsuarioServicio;

/**
 * Controlador encargado de la gestión de registros de usuarios.
 */
@Controller
@RequestMapping("/register")
public class RegistroUsuarioControlador {

    private UsuarioServicio usuarioServicio;

    public RegistroUsuarioControlador(UsuarioServicio usuarioServicio) {
        super();
        this.usuarioServicio = usuarioServicio;
    }

    /**
     * Añade un nuevo objeto UsuarioRegistroDTO al modelo para el formulario de registro.
     * @return Un nuevo objeto UsuarioRegistroDTO para el formulario de registro.
     */
    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    /**
     * Muestra el formulario de registro de usuario.
     * @return Nombre de la vista del formulario de registro.
     */
    @GetMapping
    public String mostrarFormularioDeRegistro() {
        return "register";
    }

    /**
     * Procesa el formulario de registro de usuario.
     * @param registroDTO Datos del usuario a registrar.
     * @return Redirige a la página de inicio de sesión en caso de éxito o a la página de registro con un mensaje de error.
     */
    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
        Usuario usuarioRegistrado = usuarioServicio.guardar(registroDTO);
        if (usuarioRegistrado == null) {
            // Si el correo ya está registrado, redirige al inicio de sesión con un mensaje de error
            return "redirect:/login?error=emailExists";
        }
        // Si el registro es exitoso, redirige a la página de registro con un mensaje de éxito
        return "redirect:/register?exito";
    }
}
