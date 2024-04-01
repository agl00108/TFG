/**
 * @file Usuario.java
 * @brief Clase para representar los Usuarios del sistema
 */
package com.tfg.tfgv1.entidades;

public class Usuario
{
    private String email; //Email corporativo del usuario
    private String rol; //Rol del usuario
    private String contrasenia; //Contraseña del usuario
    private String nombre; //Nombre del usuario

    /**
     * @brief constructor por defecto
     */
    public Usuario()
    {
        email="";
        rol="";
        contrasenia="";
        nombre="";
    }

    /**
     * @brief constructor por defecto
     * @param email Email corporativo del usuario
     * @param rol Rol del usuario
     * @param contrasenia Contraseña del usuario
     * @param nombre Nombre del usuario
     */
    public Usuario(String email, String rol, String contrasenia, String nombre)
    {
        this.email = email;
        this.rol = rol;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
    }

    //GETTERS DE LA CLASE

    public String getEmail()
    {
        return email;
    }

    public String getRol()
    {
        return rol;
    }

    public String getContrasenia()
    {
        return contrasenia;
    }

    public String getNombre()
    {
        return nombre;
    }

    //SETTERS DE LA CLASE

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setRol(String rol)
    {
        this.rol = rol;
    }

    public void setContrasenia(String contrasenia)
    {
        this.contrasenia = contrasenia;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @brief método toString para imprimir la información del Usuario
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
