/**
 * @file Historico_datos.java
 * @brief Clase para representar los históricos de datos de los olivos
 */
package com.tfg.tfgv1.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tfg.tfgv1.Ids.HistoricoDatosId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "HISTORICO_DATOS", schema="ALBAGOMEZ")
public class HistoricoDatos
{
    @EmbeddedId
    private HistoricoDatosId id;

    @Min(0)
    @Column(name = "VOLUMEN")
    private Double volumen; //Volumen del objeto en ese momento

    @JsonProperty("Reflectancia")
    @NotBlank
    @Column(name = "REFLECTANCIA")
    private String reflectancia; //Índices de vegetación en ese momento

    @NotBlank
    @Column(name = "NOMBRE_FUENTE")
    private String nombreFuente; //Nombre de la fuente de procedencia

    @NotBlank
    @Column(name = "TIPO_FUENTE")
    private String tipoFuente; //Tipo de la fuente de procedencia

    /**
     * @brief constructor por defecto
     */
    public HistoricoDatos()
    {
        this.id = new HistoricoDatosId();
        volumen=0.0;
        reflectancia="defecto";
        nombreFuente="defecto";
        tipoFuente="defecto";
    }

    /**
     * @brief Constructor parametrizado
     * @param fecha Fecha de la toma de datos
     * @param volumen Volumen del objeto en ese momento
     * @param reflectancia Índices de vegetación en ese momento
     * @param objeto Objeto al que hace referencia
     * @param nombreFuente Nombre de la fuente de procedencia
     * @param tipoFuente Tipo de la fuente de procedencia
     */
    public HistoricoDatos(LocalDate fecha, Double volumen, String reflectancia,
                          Objeto objeto, String nombreFuente, String tipoFuente)
    {
        this.volumen = volumen;
        this.reflectancia = reflectancia;
        this.id = new HistoricoDatosId(fecha, objeto);
        this.nombreFuente = nombreFuente;
        this.tipoFuente = tipoFuente;
    }

    //GETTERS DE LA CLASE
    public LocalDate getFecha()
    {
        return id.getFecha();
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
        return id.getObjeto().getIdObjeto();
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
        this.id.setFecha(fecha);
    }

    public void setVolumen(Double volumen)
    {
        this.volumen = volumen;
    }

    public void setReflectancia(String reflectancia)
    {
        this.reflectancia = reflectancia;
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
                "fecha=" + id.getFecha() +
                ", volumen=" + volumen +
                ", reflectancia='" + reflectancia + '\'' +
                ", idObjeto=" + id.getObjeto().getIdObjeto() +
                ", nombreFuente='" + nombreFuente + '\'' +
                ", tipoFuente='" + tipoFuente + '\'' +
                '}';
    }
}
