/**
 * @file Cosecha.java
 * @brief Clase para representar las Cosechas del sistema
 */
package com.tfg.tfgv1.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "COSECHA", schema="ALBAGOMEZ")
public class Cosecha
{
    //SETTERS DE LA CLASE
    @Getter
    @Setter
    @Id
    @Column(name = "ID_COSECHA")
    private Integer idCosecha; //ID de la cosecha
    @Getter
    @Setter
    @NotBlank
    @Column(name = "CAMPAÑA")
    private String campania; //Año de la campaña (en el que empezó)
    @Getter
    @Setter
    @Min(0)
    @Column(name = "ACEITUNAS")
    private Double aceitunas; //KG de aceitunas recogidos
    @Getter
    @Setter
    @Min(0)
    @Column(name = "ACEITE")
    private Double aceite; //KG de aceite recogidos
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "MUNICIPIO_CODIGO", referencedColumnName = "MUNICIPIO_CODIGO"),
            @JoinColumn(name = "PROVINCIA_CODIGO", referencedColumnName = "PROVINCIA_CODIGO"),
            @JoinColumn(name = "FINCA_POLIGONO", referencedColumnName = "POLIGONO"),
            @JoinColumn(name = "FINCA_PARCELA", referencedColumnName = "PARCELA"),
            @JoinColumn(name = "FINCA_RECINTO", referencedColumnName = "RECINTO"),
            @JoinColumn(name = "ZONA_UBICACION", referencedColumnName = "ZONA_UBICACION")
    })
    private Finca finca; //Finca

    /**
     * @brief constructor por defecto
     */
    public Cosecha()
    {
        idCosecha=0;
        campania="2000";
        aceite=0.0;
        aceitunas=0.0;
        finca=null;
    }

    /**
     * @brief Constructor de la clase
     * @param idCosecha ID de la cosecha
     * @param campania Año de la campaña (en el que empezó)
     * @param aceitunas KG de aceitunas recogidos
     * @param aceite KG de aceite recogidos
     */
    public Cosecha(Integer idCosecha, String campania, Double aceitunas, Double aceite, Finca finca)
    {
        this.idCosecha = idCosecha;
        this.campania = campania;
        this.aceitunas = aceitunas;
        this.aceite = aceite;
        this.finca = finca;
    }

    //GETTERS DE LA CLASE

    public Integer getMunicipioCodigo()
    {
        return finca.getMunicipioCodigo();
    }

    public Integer getProvinciaCodigo()
    {
        return finca.getCodigoProvincia();
    }

    public Integer getPoligono()
    {
        return finca.getPoligono();
    }

    public Integer getParcela()
    {
        return finca.getParcela();
    }

    public Integer getRecinto()
    {
        return finca.getRecinto();
    }

    public String getZonaUbicacion()
    {
        return finca.getZonaUbicacion();
    }


    /**
     * @brief método toString para imprimir la información de la Cosecha
     * @return string con los parámetros
     */
    @Override
    public String toString() {
        return "Cosecha{" +
                "idCosecha"+ (idCosecha != null ? idCosecha : "null") +
                "campania='" + (campania != null ? campania : "null") + '\'' +
                ", aceitunas=" + (aceitunas != null ? aceitunas : "null") +
                ", aceite=" + (aceite != null ? aceite : "null") +
                ", municipio_codigo=" + getMunicipioCodigo() +
                ", provincia_codigo=" + getProvinciaCodigo() +
                ", poligono=" + getPoligono() +
                ", parcela=" + getParcela() +
                ", recinto=" + getRecinto() +
                ", zona_ubicacion='" + getZonaUbicacion() + '\'' +
                '}';
    }
}