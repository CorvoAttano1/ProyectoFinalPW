package com.proyecto.pw.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.pw.modelo.Tarea;
import com.proyecto.pw.modelo.Usuario;

import java.util.List;

/**
 * Repositorio para la entidad Tarea.
 */
@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    /**
     * Encuentra tareas por su estado de completitud.
     * @param completa Estado de la tarea (completa o no).
     * @return Lista de tareas con el estado indicado.
     */
    List<Tarea> findByCompleta(boolean completa);

    /**
     * Encuentra tareas asociadas a un usuario.
     * @param usuario El usuario al que pertenecen las tareas.
     * @return Lista de tareas del usuario.
     */
    List<Tarea> findByUsuario(Usuario usuario);
}
