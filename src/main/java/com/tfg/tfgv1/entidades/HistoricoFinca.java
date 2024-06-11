/**
 * @file Historico_finca.java
 * @brief Clase para representar los históricos de datos de las fincas
 */
package com.tfg.tfgv1.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tfg.tfgv1.Ids.HistoricoFincaId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Entity
@Table(name = "HISTORICO_FINCA", schema="ALBAGOMEZ")
public class HistoricoFinca
{
    @EmbeddedId
    private HistoricoFincaId id;

    @JsonProperty("Reflectancia")
    @Setter @Getter
    @Column(name = "REFLECTANCIA")
    @Lob @NotBlank
    private byte[] reflectancia; //Índices de vegetación en ese momento

    @JsonProperty("Temperatura")
    @Column(name = "TEMPERATURA")
    @Setter @Getter
    @Lob @NotBlank
    private byte[] temperatura; //Temperatura en ese momento

    @Min(0)
    @Setter
    @Getter
    private Double lluvia; //Suma de la lluvia en la fecha de toma de datos

    @NotBlank
    @Column(name = "NOMBRE_FUENTE")
    @Setter
    @Getter
    private String nombreFuente; //Nombre de la fuente de procedencia

    @NotBlank
    @Column(name = "TIPO_FUENTE")
    @Setter
    @Getter
    private String tipoFuente; //Tipo de la fuente de procedencia

    /**
     * @brief constructor por defecto
     */
    public HistoricoFinca()
    {
        this.id = new HistoricoFincaId();
        lluvia=0.0;
        nombreFuente="defecto";
        tipoFuente="defecto";
    }

    /**
     * @brief Constructor parametrizado
     * @param fecha Fecha de la toma de datos
     * @param finca Finca a la que pertenecen los datos
     * @param reflectancia Índices de vegetación en ese momento
     * @param temperatura Temperatura en ese momento
     * @param lluvia Suma de la lluvia en la fecha de toma de datos
     * @param nombreFuente Nombre de la fuente de procedencia
     * @param tipoFuente Tipo de la fuente de procedencia
     */
    public HistoricoFinca(LocalDate fecha, Finca finca, String reflectancia,
                          String temperatura, Double lluvia, String nombreFuente, String tipoFuente)
    {
        this.id = new HistoricoFincaId(fecha, finca);
        this.reflectancia = reflectancia.getBytes(StandardCharsets.UTF_8);
        this.temperatura = temperatura.getBytes(StandardCharsets.UTF_8);
        this.lluvia = lluvia;
        this.nombreFuente = nombreFuente;
        this.tipoFuente = tipoFuente;
    }

    //GETTERS DE LA CLASE

    public LocalDate getFecha()
    {
        return id.getFecha();
    }

    public Integer getMunicipioCodigo()
    {
        return id.getFinca().getMunicipioCodigo();
    }

    public Integer getProvinciaCodigo()
    {
        return id.getFinca().getCodigoProvincia();
    }

    public Integer getPoligono()
    {
        return id.getFinca().getPoligono();
    }

    public Integer getParcela()
    {
        return id.getFinca().getParcela();
    }

    public Integer getRecinto()
    {
        return id.getFinca().getRecinto();
    }

    public String getZonaUbicacion()
    {
        return id.getFinca().getZonaUbicacion();
    }
    //SETTERS DE LA CLASE
    public void setFecha(LocalDate fecha)
    {
        this.id.setFecha(fecha);
    }


    /**
     * @brief método toString para imprimir la información del HISTÓRICO
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Historico_finca{" +
                "fecha=" + id.getFecha() +
                ", municipioCodigo=" + id.getFinca().getMunicipioCodigo() +
                ", provinciaCodigo=" + id.getFinca().getCodigoProvincia() +
                ", poligono=" + id.getFinca().getPoligono() +
                ", parcela=" + id.getFinca().getParcela() +
                ", recinto=" + id.getFinca().getRecinto() +
                ", reflectancia='" + reflectancia.toString() + '\'' +
                ", temperatura='" + temperatura.toString() + '\'' +
                ", lluvia=" + lluvia +
                ", nombreFuente='" + nombreFuente + '\'' +
                ", tipoFuente='" + tipoFuente + '\'' +
                '}';
    }
}
