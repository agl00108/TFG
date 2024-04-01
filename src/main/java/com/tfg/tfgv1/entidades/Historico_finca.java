/**
 * @file Historico_finca.java
 * @brief Clase para representar los históricos de datos de las fincas
 */
package com.tfg.tfgv1.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class Historico_finca
{
    private LocalDate fecha; //Fecha de la toma de datos
    private Integer municipioCodigo; //Código del municipio
    private Integer provinciaCodigo; //Código de la provincia
    private Integer poligono; //Poligono del código SIGPAC
    private Integer parcela; //Parcela del código SIGPAC
    private Integer recinto; //Recinto del código SIGPAC
    @JsonProperty("Reflectancia")
    private String reflectancia; //Índices de vegetación en ese momento
    @JsonProperty("Temperatura")
    private String temperatura; //Temperatura en ese momento
    private Double lluvia; //Suma de la lluvia en la fecha de toma de datos
    private String nombreFuente; //Nombre de la fuente de procedencia
    private String tipoFuente; //Tipo de la fuente de procedencia

    /**
     * @brief constructor por defecto
     */
    public Historico_finca()
    {
        fecha=null;
        municipioCodigo=-1;
        provinciaCodigo=-1;
        poligono=-1;
        parcela=-1;
        recinto=-1;
        reflectancia=null;
        temperatura=null;
        lluvia=0.0;
        nombreFuente="";
        tipoFuente="";
    }

    /**
     * @brief Constructor parametrizado
     * @param fecha Fecha de la toma de datos
     * @param municipioCodigo Código del municipio
     * @param provinciaCodigo Código de la provincia
     * @param poligono Poligono del código SIGPAC
     * @param parcela Parcela del código SIGPAC
     * @param recinto Recinto del código SIGPAC
     * @param reflectancia Índices de vegetación en ese momento
     * @param temperatura Temperatura en ese momento
     * @param lluvia Suma de la lluvia en la fecha de toma de datos
     * @param nombreFuente Nombre de la fuente de procedencia
     * @param tipoFuente Tipo de la fuente de procedencia
     */
    public Historico_finca(LocalDate fecha, Integer municipioCodigo, Integer provinciaCodigo,
                           Integer poligono, Integer parcela, Integer recinto, String reflectancia,
                           String temperatura, Double lluvia, String nombreFuente, String tipoFuente)
    {
        this.fecha = fecha;
        this.municipioCodigo = municipioCodigo;
        this.provinciaCodigo = provinciaCodigo;
        this.poligono = poligono;
        this.parcela = parcela;
        this.recinto = recinto;
        this.reflectancia = reflectancia;
        this.temperatura = temperatura;
        this.lluvia = lluvia;
        this.nombreFuente = nombreFuente;
        this.tipoFuente = tipoFuente;
    }

    //GETTERS DE LA CLASE

    public LocalDate getFecha()
    {
        return fecha;
    }

    public Integer getMunicipioCodigo()
    {
        return municipioCodigo;
    }

    public Integer getProvinciaCodigo()
    {
        return provinciaCodigo;
    }

    public Integer getPoligono()
    {
        return poligono;
    }

    public Integer getParcela()
    {
        return parcela;
    }

    public Integer getRecinto()
    {
        return recinto;
    }

    public String getReflectancia()
    {
        return reflectancia;
    }

    public String getTemperatura()
    {
        return temperatura;
    }

    public Double getLluvia()
    {
        return lluvia;
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

    public void setMunicipioCodigo(Integer municipioCodigo)
    {
        this.municipioCodigo = municipioCodigo;
    }

    public void setProvinciaCodigo(Integer provinciaCodigo)
    {
        this.provinciaCodigo = provinciaCodigo;
    }

    public void setPoligono(Integer poligono)
    {
        this.poligono = poligono;
    }

    public void setParcela(Integer parcela)
    {
        this.parcela = parcela;
    }

    public void setRecinto(Integer recinto)
    {
        this.recinto = recinto;
    }

    public void setReflectancia(String reflectancia)
    {
        this.reflectancia = reflectancia;
    }

    public void setTemperatura(String temperatura)
    {
        this.temperatura = temperatura;
    }

    public void setLluvia(Double lluvia)
    {
        this.lluvia = lluvia;
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
        return "Historico_finca{" +
                "fecha=" + fecha +
                ", municipioCodigo=" + municipioCodigo +
                ", provinciaCodigo=" + provinciaCodigo +
                ", poligono=" + poligono +
                ", parcela=" + parcela +
                ", recinto=" + recinto +
                ", reflectancia='" + reflectancia + '\'' +
                ", temperatura='" + temperatura + '\'' +
                ", lluvia=" + lluvia +
                ", nombreFuente='" + nombreFuente + '\'' +
                ", tipoFuente='" + tipoFuente + '\'' +
                '}';
    }
}
