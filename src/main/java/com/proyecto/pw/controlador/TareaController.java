package com.proyecto.pw.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proyecto.pw.modelo.Tarea;
import com.proyecto.pw.modelo.Usuario;
import com.proyecto.pw.repositorio.TareaRepository;
import com.proyecto.pw.repositorio.UsuarioRepositorio;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Controlador encargado de la gestión de tareas.
 */
@Controller
@RequestMapping("/tareas")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Lista las tareas del usuario actual.
     * @param model El modelo que se utilizará para enviar datos a la vista.
     * @param principal Objeto que representa al usuario autenticado.
     * @return Nombre de la vista que muestra las tareas del usuario.
     */
    @GetMapping
    public String listarTareas(Model model, Principal principal) {
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        List<Tarea> tareas = tareaRepository.findByUsuario(usuario);
        model.addAttribute("tareas", tareas);
        return "listaTareas";
    }

    /**
     * Muestra los detalles de una tarea específica.
     * @param id El identificador de la tarea a visualizar.
     * @param model El modelo que se utilizará para enviar datos a la vista.
     * @return Nombre de la vista que muestra los detalles de la tarea.
     */
    @GetMapping("/{id}")
    public String verDetalleTarea(@PathVariable Long id, Model model) {
        Optional<Tarea> tarea = tareaRepository.findById(id);
        if (tarea.isPresent()) {
            model.addAttribute("tarea", tarea.get());
            return "detallesTarea";
        }
        return "redirect:/tareas";
    }

    /**
     * Muestra el formulario para una nueva tarea.
     * @param model El modelo que se utilizará para enviar datos a la vista.
     * @return Nombre de la vista del formulario para una nueva tarea.
     */
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaTarea(Model model) {
        model.addAttribute("tarea", new Tarea());
        return "formularioTarea";
    }

    /**
     * Guarda una nueva tarea.
     * @param tarea La tarea a guardar.
     * @param principal Objeto que representa al usuario autenticado.
     * @return Redirige a la página de tareas después de guardar la nueva tarea.
     */
    @PostMapping("/nueva")
    public String guardarNuevaTarea(@ModelAttribute Tarea tarea, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioRepositorio.findByEmail(username);
        tarea.setUsuario(usuario);
        tareaRepository.save(tarea);
        return "redirect:/tareas";
    }

    /**
     * Muestra el formulario para editar una tarea.
     * @param id El identificador de la tarea a editar.
     * @param model El modelo que se utilizará para enviar datos a la vista.
     * @param principal Objeto que representa al usuario autenticado.
     * @return Nombre de la vista del formulario para editar una tarea.
     */
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarTarea(@PathVariable Long id, Model model, Principal principal) {
        Optional<Tarea> tarea = tareaRepository.findById(id);
        if (tarea.isPresent()) {
            Tarea tareaParaEditar = tarea.get();
            String username = principal.getName();
            Usuario usuario = usuarioRepositorio.findByEmail(username);
            if (tareaParaEditar.getUsuario().equals(usuario)) {
                model.addAttribute("tarea", tareaParaEditar);
                return "formularioTarea";
            }
        }
        return "redirect:/tareas";
    }

    /**
     * Actualiza una tarea existente.
     * @param id El identificador de la tarea a actualizar.
     * @param tarea La tarea con los nuevos datos.
     * @param principal Objeto que representa al usuario autenticado.
     * @return Redirige a la página de tareas después de actualizar la tarea.
     */
    @PostMapping("/{id}/editar")
    public String actualizarTarea(@PathVariable Long id, @ModelAttribute Tarea tarea, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioRepositorio.findByEmail(username);
        Optional<Tarea> tareaExistente = tareaRepository.findById(id);
        if (tareaExistente.isPresent()) {
            Tarea tareaParaEditar = tareaExistente.get();
            if (tareaParaEditar.getUsuario().equals(usuario)) {
                tareaParaEditar.setNombre(tarea.getNombre());
                tareaParaEditar.setDescripcion(tarea.getDescripcion());
                tareaParaEditar.setFechaDeEntrega(tarea.getFechaDeEntrega());
                tareaParaEditar.setCompleta(tarea.isCompleta());
                tareaRepository.save(tareaParaEditar);
            }
        }
        return "redirect:/tareas";
    }

    /**
     * Muestra la confirmación para eliminar una tarea.
     * @param id El identificador de la tarea a eliminar.
     * @param model El modelo que se utilizará para enviar datos a la vista.
     * @return Nombre de la vista de confirmación para eliminar una tarea.
     */
    @GetMapping("/{id}/eliminar")
    public String mostrarConfirmacionEliminarTarea(@PathVariable Long id, Model model) {
        Optional<Tarea> tarea = tareaRepository.findById(id);
        if (tarea.isPresent()) {
            model.addAttribute("tarea", tarea.get());
            return "eliminarTarea";
        }
        return "redirect:/tareas";
    }

    /**
     * Elimina una tarea específica.
     * @param id El identificador de la tarea a eliminar.
     * @return Redirige a la página de tareas después de la eliminación.
     */
    @PostMapping("/{id}/eliminar")
    public String eliminarTarea(@PathVariable Long id) {
        tareaRepository.deleteById(id);
        return "redirect:/tareas";
    }
}

