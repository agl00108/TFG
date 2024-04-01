/**
 * @file Municipio.java
 * @brief Clase para representar los Municipios del sistema
 */
package com.tfg.tfgv1.entidades;

import jakarta.persistence.Entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
public class Municipio
{
    @NotNull
    @Min(-1)
    private Integer codigoMunicipio; //Código del municipio
    @NotBlank
    private String nombre; //Nombre del municipio
    @NotNull
    @Min(-1)
    private Integer provinciaCodigo; //Codigo de la provincia


    /**
     * @brief constructor por defecto
     */
    public Municipio()
    {
        codigoMunicipio=-1;
        nombre="defecto";
        provinciaCodigo=-1;
    }

    /**
     * @brief constructor parametrizado
     * @param codigoMunicipio Código del municipio
     * @param nombre Nombre del municipio
     * @param provinciaCodigo Codigo de la provincia
     */
    public Municipio(Integer codigoMunicipio, String nombre, Integer provinciaCodigo)
    {
        this.codigoMunicipio = codigoMunicipio;
        this.nombre = nombre;
        this.provinciaCodigo = provinciaCodigo;
    }

    //GETTERS DE LA CLASE
    public Integer getCodigoMunicipio()
    {
        return codigoMunicipio;
    }

    public String getNombre()
    {
        return nombre;
    }

    public Integer getProvinciaCodigo()
    {
        return provinciaCodigo;
    }

    //SETTERS DE LA CLASE
    public void setCodigoMunicipio(Integer codigoMunicipio)
    {
        this.codigoMunicipio = codigoMunicipio;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setProvinciaCodigo(Integer provinciaCodigo)
    {
        this.provinciaCodigo = provinciaCodigo;
    }

    /**
     * @brief método toString para imprimir la información del Municipio
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Municipio{" +
                "codigoMunicipio=" + codigoMunicipio +
                ", nombre='" + nombre + '\'' +
                ", provinciaCodigo=" + provinciaCodigo +
                '}';
    }
}
