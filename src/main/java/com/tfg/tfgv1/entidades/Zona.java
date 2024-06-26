/**
 * @file Zona.java
 * @brief Clase para representar las Zonas del sistema
 */
package com.tfg.tfgv1.entidades;

import com.tfg.tfgv1.Ids.ZonaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Zona
{
    @EmbeddedId
    private ZonaId id;

    @Setter
    @Getter
    @Column(name="LONGITUD")
    private Float longitud; //Longitud de la zona

    @Setter
    @Getter
    @Column(name="LATITUD")
    private Float latitud; //Latitud de la zona
    @Setter
    @Getter
    @NotBlank
    @Column(name="DESCRIPCION")
    private String descripcion; //Descripción de la zona
    @Setter
    @Getter
    @NotNull
    @Min(0)
    @Column(name="RADIO")
    private Float radio; //Radio de extensión de la zona

    /**
     * @brief constructor por defecto
     */
    public Zona()
    {
        this.id= new ZonaId();
        longitud= 0.0F;
        latitud= 0.0F;
        descripcion="";
        radio=0.0F;
    }

    /**
     * @brief Constructor parametrizado
     * @param ubicacion Ubicacion de la zona
     * @param longitud Longitud de la zona
     * @param latitud Latitud de la zona
     * @param descripcion Descripción de la zona
     * @param municipio  Municipio donde se ubica
     * @param radio Radio de extensión de la zona
     */
    public Zona(String ubicacion, Float longitud, Float latitud, String descripcion,
                Municipio municipio, Float radio)
    {
        this.longitud = longitud;
        this.latitud = latitud;
        this.descripcion = descripcion;
        this.id= new ZonaId(ubicacion, municipio);
        this.radio = radio;
    }

    //GETTERS DE LA CLASE

    public String getUbicacion()
    {
        return id.getUbicacion();
    }

    public Integer getMunicipioCodigo()
    {
        return id.getMunicipioCodigo();
    }

    public Integer getProvinciaCodigo()
    {
        return id.getProvinciaCodigo();
    }

    //SETTERS DE LA CLASE
    public void setUbicacion(String ubicacion)
    {
        this.id.setUbicacion(ubicacion);
    }

    public void setMunicipio(Municipio municipio)
    {
        this.id.setMunicipio(municipio);
    }

    /**
     * @brief método toString para imprimir la información de la Zona
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Zona{" +
                "ubicacion='" + id.getUbicacion() + '\'' +
                ", longitud=" + longitud +
                ", latitud=" + latitud +
                ", descripcion='" + descripcion + '\'' +
                ", municipioCodigo=" + id.getMunicipioCodigo() +
                ", provinciaCodigo=" + id.getProvinciaCodigo() +
                ", radio=" + radio +
                '}';
    }
}
