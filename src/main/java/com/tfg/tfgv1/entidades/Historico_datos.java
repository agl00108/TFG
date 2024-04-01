/**
 * @file Historico_datos.java
 * @brief Clase para representar los históricos de datos de los olivos
 */
package com.tfg.tfgv1.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class Historico_datos
{
    @NotNull
    private LocalDate fecha; //Fecha de la toma de datos
    @Min(0)
    private Double volumen; //Volumen del objeto en ese momento
    @JsonProperty("Reflectancia")
    @NotBlank
    private String reflectancia; //Índices de vegetación en ese momento
    @NotNull
    private Integer idObjeto; //ID del objeto al que hace referencia
    @NotBlank
    private String nombreFuente; //Nombre de la fuente de procedencia
    @NotBlank
    private String tipoFuente; //Tipo de la fuente de procedencia

    /**
     * @brief constructor por defecto
     */
    public Historico_datos()
    {
        fecha=LocalDate.now();
        volumen=0.0;
        reflectancia="defecto";
        idObjeto=-1;
        nombreFuente="defecto";
        tipoFuente="defecto";
    }

    /**
     * @brief Constructor parametrizado
     * @param fecha Fecha de la toma de datos
     * @param volumen Volumen del objeto en ese momento
     * @param reflectancia Índices de vegetación en ese momento
     * @param idObjeto ID del objeto al que hace referencia
     * @param nombreFuente Nombre de la fuente de procedencia
     * @param tipoFuente Tipo de la fuente de procedencia
     */
    public Historico_datos(LocalDate fecha, Double volumen, String reflectancia,
                           Integer idObjeto, String nombreFuente, String tipoFuente)
    {
        this.fecha = fecha;
        this.volumen = volumen;
        this.reflectancia = reflectancia;
        this.idObjeto = idObjeto;
        this.nombreFuente = nombreFuente;
        this.tipoFuente = tipoFuente;
    }

    //GETTERS DE LA CLASE
    public LocalDate getFecha()
    {
        return fecha;
    }

    public Double getVolumen()
    {
        return volumen;
    }

    public String getReflectancia()
    {
        return reflectancia;
    }

    public Integer getIdObjeto()
    {
        return idObjeto;
    }

    public String getNombreFuente()
    {
        return nombreFuente;
    }

    public String getTipoFuente()
    {
        return tipoFuente;
    }

    //SETTERS DE LA CLASE

    public void setFecha(LocalDate fecha)
    {
        this.fecha = fecha;
    }

    public void setVolumen(Double volumen)
    {
        this.volumen = volumen;
    }

    public void setReflectancia(String reflectancia)
    {
        this.reflectancia = reflectancia;
    }

    public void setIdObjeto(Integer idObjeto)
    {
        this.idObjeto = idObjeto;
    }

    public void setNombreFuente(String nombreFuente)
    {
        this.nombreFuente = nombreFuente;
    }

    public void setTipoFuente(String tipoFuente)
    {
        this.tipoFuente = tipoFuente;
    }

    /**
     * @brief método toString para imprimir la información del HISTÓRICO
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Historico_datos{" +
                "fecha=" + fecha +
                ", volumen=" + volumen +
                ", reflectancia='" + reflectancia + '\'' +
                ", idObjeto=" + idObjeto +
                ", nombreFuente='" + nombreFuente + '\'' +
                ", tipoFuente='" + tipoFuente + '\'' +
                '}';
    }
}
