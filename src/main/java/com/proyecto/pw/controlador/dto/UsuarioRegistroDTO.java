package com.proyecto.pw.controlador.dto;

/**
 * DTO (Data Transfer Object) que representa los datos de registro de un usuario.
 */
public class UsuarioRegistroDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Constructor de UsuarioRegistroDTO con parámetros.
     * @param nombre El nombre del usuario.
     * @param apellido El apellido del usuario.
     * @param email El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     */
    public UsuarioRegistroDTO(String nombre, String apellido, String email, String password) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor de UsuarioRegistroDTO sin parámetros.
     */
    public UsuarioRegistroDTO() {

    }
}
