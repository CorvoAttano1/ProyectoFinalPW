package com.proyecto.pw.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase que representa un rol en el sistema.
 */
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Constructor de Rol con parámetros.
     * @param id El identificador del rol.
     * @param nombre El nombre del rol.
     */
    public Rol(Long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Constructor de Rol sin parámetros.
     */
    public Rol() {

    }

    /**
     * Constructor de Rol con un parámetro para el nombre del rol.
     * @param nombre El nombre del rol.
     */
    public Rol(String nombre) {
        super();
        this.nombre = nombre;
    }
}
