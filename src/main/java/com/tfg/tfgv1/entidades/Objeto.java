/**
 * @file Objeto.java
 * @brief Clase para representar los Objetos del sistema
 */
package com.tfg.tfgv1.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "OBJETO", schema="ALBAGOMEZ")
public class Objeto
{
    @NotNull
    @Min(-1)
    @Id
    @Column(name = "ID_OBJETO")
    private Integer idObjeto; //ID del objeto

    @Getter
    @Column(name="TIPO_OBJETO")
    private String tipoObjeto; //Tipo de objeto

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ZONA_MUNICIPIO_CODIGO", referencedColumnName = "MUNICIPIO_CODIGO"),
            @JoinColumn(name = "ZONA_PROVINCIA_CODIGO", referencedColumnName = "PROVINCIA_CODIGO"),
            @JoinColumn(name = "ZONA_UBICACION", referencedColumnName = "UBICACION")
    })
    private Zona zona;
    @Column(name="POLIGONO_ENVOLVENTE",columnDefinition = "MDSYS.SDO_GEOMETRY")
    private Polygon poligonoEnvolvente;

    @Column(name="PUNTO_MEDIO",columnDefinition = "MDSYS.SDO_GEOMETRY")
    private Point puntoMedio;

    /**
     * @brief constructor por defecto
     */
    public Objeto()
    {
        idObjeto=-1;
        zona=null;
        poligonoEnvolvente=null;
        puntoMedio=null;
    }

    /**
     * @brief Constructor por defecto
     * @param idObjeto ID del objeto
     * @param zona Zona donde se ubica
     * @param poligonoEnvolvente Poligono envolvente del objeto
     * @param puntoMedio Punto medio del objeto
     */
    public Objeto(Integer idObjeto, String tipoObjeto, Zona zona, Polygon poligonoEnvolvente, Point puntoMedio)
    {
        this.idObjeto = idObjeto;
        this.tipoObjeto = tipoObjeto;
        this.zona = zona;
        this.poligonoEnvolvente = poligonoEnvolvente;
        this.puntoMedio = puntoMedio;
    }

    //GETTERS DE LA CLASE
    public Integer getIdObjeto()
    {
        return idObjeto;
    }

    public String getZonaUbicacion()
    {
        return zona.getUbicacion();
    }

    public Integer getZonaMunicipioCodigo()
    {
        return zona.getMunicipioCodigo();
    }

    public Integer getZonaProvinciaCodigo()
    {
        return zona.getProvinciaCodigo();
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public Zona getZona() {
        return zona;
    }

    public Polygon getPoligonoEnvolvente() {
        return poligonoEnvolvente;
    }

    public Point getPuntoMedio() {
        return puntoMedio;
    }

    //SETTERS DE LA CLASE

    public void setZonaUbicacion(String zonaUbicacion)
    {
        this.zona.setUbicacion(zonaUbicacion);
    }

    /**
     * @brief método toString para imprimir la información del Objeto
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Objeto{" +
                "idObjeto=" + idObjeto +
                ", zonaUbicacion='" + zona.getUbicacion() + '\'' +
                ", zonaMunicipioCodigo=" + zona.getMunicipioCodigo() +
                ", zonaProvinciaCodigo=" + zona.getMunicipioCodigo() +
                '}';
    }
}
