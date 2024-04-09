/**
 * @file Historico_datos.java
 * @brief Clase para representar los históricos de datos de los olivos
 */
package com.tfg.tfgv1.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tfg.tfgv1.Ids.HistoricoDatosId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Entity
@Table(name = "HISTORICO_DATOS", schema="ALBAGOMEZ")
public class HistoricoDatos
{
    @EmbeddedId
    private HistoricoDatosId id;

    @Min(0)
    @Column(name = "VOLUMEN")
    @Getter
    @Setter
    private Double volumen; //Volumen del objeto en ese momento

    @JsonProperty("Reflectancia")
    @NotBlank
    @Column(name = "REFLECTANCIA")
    @Lob // Esta anotación indica a Hibernate que este campo es un BLOB
    private byte[] reflectancia; // Índices de vegetación en ese momento

    @NotBlank
    @Getter
    @Setter
    @Column(name = "NOMBRE_FUENTE")
    private String nombreFuente; //Nombre de la fuente de procedencia

    @NotBlank
    @Getter
    @Setter
    @Column(name = "TIPO_FUENTE")
    private String tipoFuente; //Tipo de la fuente de procedencia

    /**
     * @brief constructor por defecto
     */
    public HistoricoDatos()
    {
        this.id = new HistoricoDatosId();
        volumen=0.0;
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
        this.reflectancia = reflectancia.getBytes(StandardCharsets.UTF_8);
        this.id = new HistoricoDatosId(fecha, objeto);
        this.nombreFuente = nombreFuente;
        this.tipoFuente = tipoFuente;
    }

    //GETTERS DE LA CLASE
    public LocalDate getFecha()
    {
        return id.getFecha();
    }


    public String getReflectancia()
    {
        return new String(reflectancia);
    }

    public Integer getIdObjeto()
    {
        return id.getObjeto().getIdObjeto();
    }


    //SETTERS DE LA CLASE

    public void setFecha(LocalDate fecha)
    {
        this.id.setFecha(fecha);
    }

    public void setReflectancia(String reflectancia)
    {
        this.reflectancia = reflectancia.getBytes(StandardCharsets.UTF_8);
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
