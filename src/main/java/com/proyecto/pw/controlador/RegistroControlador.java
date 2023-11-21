package com.proyecto.pw.controlador;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.pw.modelo.Tarea;
import com.proyecto.pw.modelo.Usuario;
import com.proyecto.pw.repositorio.TareaRepository;
import com.proyecto.pw.repositorio.UsuarioRepositorio;

/**
 * Controlador encargado de gestionar el registro de usuarios y las tareas asociadas.
 */
@Controller
public class RegistroControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private TareaRepository tareaRepository;

    /**
     * Método para mostrar la página de inicio de sesión.
     * @return Nombre de la vista de la página de inicio de sesión.
     */
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    /**
     * Método para listar las tareas del usuario actual.
     * @param model El modelo que se utilizará para enviar datos a la vista.
     * @param principal Objeto que representa al usuario autenticado.
     * @return Nombre de la vista que muestra las tareas del usuario.
     */
    @GetMapping("/")
    public String listarMisTareas(Model model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioRepositorio.findByEmail(username);

        // Obtener las tareas del usuario actual
        List<Tarea> tareas = tareaRepository.findByUsuario(usuario);

        model.addAttribute("tareas", tareas);
        return "listaTareas"; // Nombre de la vista que muestra las tareas del usuario
    }
}





