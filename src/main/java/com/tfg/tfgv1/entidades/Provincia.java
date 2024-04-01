/**
 * @file Provincia.java
 * @brief Clase para representar las Provincias del sistema
 */

package com.tfg.tfgv1.entidades;

import javax.validation.constraints.*;

public class Provincia
{
    @NotNull
    @Min(-1)
    private Integer codigoProvincia; //Codigo de la provincia
    @NotBlank
    private String nombreProvincia; //Nombre de la provincia

    /**
     * @brief constructor por defecto
     */
    public Provincia()
    {
        this.codigoProvincia=-1;
        this.nombreProvincia="defecto";
    }

    /**
     * @brief Constructor parametrizado
     * @param codigoProvincia codigo de la provincia
     * @param nombreProvincia nombre de la provincia
     */
    public Provincia(Integer codigoProvincia, String nombreProvincia)
    {
        this.codigoProvincia = codigoProvincia;
        this.nombreProvincia = nombreProvincia;
    }

    //GETTERS DE LA CLASE
    public Integer getCodigoProvincia()
    {
        return codigoProvincia;
    }

    public String getNombreProvincia()
    {
        return nombreProvincia;
    }

    //SETTERS DE LA CLASE
    public void setCodigoProvincia(Integer codigoProvincia)
    {
        this.codigoProvincia = codigoProvincia;
    }

    public void setNombreProvincia(String nombreProvincia)
    {
        this.nombreProvincia = nombreProvincia;
    }

    /**
     * @brief método toString para imprimir la información de la Provincia
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Provincia{" +
                "codigoProvincia=" + codigoProvincia +
                ", nombreProvincia='" + nombreProvincia + '\'' +
                '}';
    }
}
