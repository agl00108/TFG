package com.tfg.tfgv1.Ids;

import com.tfg.tfgv1.entidades.Municipio;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Embeddable
public class ZonaId implements Serializable
{
    @NotBlank
    @Column(name="UBICACION")
    private String ubicacion; //Ubicación de la zona

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "MUNICIPIO_CODIGO", referencedColumnName = "CODIGO_MUNICIPIO"),
            @JoinColumn(name = "PROVINCIA_CODIGO", referencedColumnName = "PROVINCIA_CODIGO_PROVINCIA")
    })
    private Municipio municipio;

    public ZonaId()
    {
        this.ubicacion = "defecto";
        this.municipio = null;
    }

    public ZonaId(String ubicacion, Municipio municipio)
    {
        this.ubicacion = ubicacion;
        this.municipio = municipio;
    }

    public void setUbicacion(String ubicacion)
    {
        this.ubicacion = ubicacion;
    }

    public void setMunicipio(Municipio municipio)
    {
        this.municipio = municipio;
    }

    public String getUbicacion()
    {
        return ubicacion;
    }

    public Integer getMunicipioCodigo()
    {
        return municipio.getCodigoMunicipio();
    }

    public Integer getProvinciaCodigo()
    {
        return municipio.getProvinciaCodigo();
    }
}
