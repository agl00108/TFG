/**
 * @file Cosecha.java
 * @brief Clase para representar las Cosechas del sistema
 */
package com.tfg.tfgv1.entidades;

import jakarta.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "COSECHA", schema="ALBAGOMEZ")
public class Cosecha
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COSECHA")
    private Integer idCosecha; //ID de la cosecha
    @NotBlank
    @Column(name = "CAMPAÑA")
    private String campania; //Año de la campaña (en el que empezó)
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "MUNICIPIO_CODIGO", referencedColumnName = "CODIGO_MUNICIPIO"),
            @JoinColumn(name = "PROVINCIA_CODIGO", referencedColumnName = "PROVINCIA_CODIGO_PROVINCIA")
    })
    private Municipio municipioCodigo;

    @Min(0)
    @Column(name = "ACEITUNAS")
    private Double aceitunas; //KG de aceitunas recogidos
    @Min(0)
    private Double aceite; //KG de aceite recogidos
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "FINCA_POLIGONO", referencedColumnName = "POLIGONO"),
            @JoinColumn(name = "FINCA_PARCELA", referencedColumnName = "PARCELA"),
            @JoinColumn(name = "FINCA_RECINTO", referencedColumnName = "RECINTO")
    })
    private Finca finca; //Finca

    /**
     * @brief constructor por defecto
     */
    public Cosecha()
    {
        idCosecha=0;
        campania="2000";
        municipioCodigo=null;
        aceite=0.0;
        aceitunas=0.0;
        finca=null;
    }

    /**
     * @brief Constructor de la clase
     * @param idCosecha ID de la cosecha
     * @param campania Año de la campaña (en el que empezó)
     * @param municipioCodigo Código del municipio
     * @param provinciaCodigo Código de la provincia
     * @param aceitunas KG de aceitunas recogidos
     * @param aceite KG de aceite recogidos
     */
    public Cosecha(Integer idCosecha, String campania, Municipio municipioCodigo,
                   Integer provinciaCodigo, Double aceitunas, Double aceite, Finca finca)
    {
        this.idCosecha = idCosecha;
        this.campania = campania;
        this.municipioCodigo = municipioCodigo;
        this.aceitunas = aceitunas;
        this.aceite = aceite;
        this.finca = finca;
    }

    //GETTERS DE LA CLASE

    public Integer getIdCosecha()
    {
        return idCosecha;
    }

    public String getCampania()
    {
        return campania;
    }

    public Integer getMunicipioCodigo()
    {
        return municipioCodigo.getId().getCodigoMunicipio();
    }

    public Double getAceitunas()
    {
        return aceitunas;
    }

    public Double getAceite()
    {
        return aceite;
    }


    //SETTERS DE LA CLASE
    public void setIdCosecha(Integer idCosecha)
    {
        this.idCosecha = idCosecha;
    }

    public void setCampania(String campania)
    {
        this.campania = campania;
    }

    public void setMunicipioCodigo(Municipio municipioCodigo)
    {
        this.municipioCodigo = municipioCodigo;
    }

    public void setAceitunas(Double aceitunas)
    {
        this.aceitunas = aceitunas;
    }

    public void setAceite(Double aceite)
    {
        this.aceite = aceite;
    }


    /**
     * @brief método toString para imprimir la información de la Cosecha
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Cosecha{" +
                "idCosecha=" + idCosecha +
                ", campania='" + campania + '\'' +
                ", municipioCodigo=" + municipioCodigo +
                ", aceitunas=" + aceitunas +
                ", aceite=" + aceite +
                ", finca=" + finca + '}';
    }
}
