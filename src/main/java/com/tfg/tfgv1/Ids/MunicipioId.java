package com.tfg.tfgv1.Ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class MunicipioId implements Serializable
{
    @NotNull
    @Min(-1)
    @Column(name = "CODIGO_MUNICIPIO")
    private Integer codigoMunicipio;

    @NotNull
    @Min(-1)
    @Column(name = "PROVINCIA_CODIGO_PROVINCIA")
    private Integer provinciaCodigo;

    public MunicipioId(Integer codigoMunicipio, Integer provinciaCodigo)
    {
        this.codigoMunicipio = codigoMunicipio;
        this.provinciaCodigo = provinciaCodigo;
    }

    public MunicipioId()
    {
        this.codigoMunicipio = -1;
        this.provinciaCodigo = -1;
    }

    public Integer getCodigoMunicipio()
    {
        return codigoMunicipio;
    }

    public Integer getProvinciaCodigo()
    {
        return provinciaCodigo;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio)
    {
        this.codigoMunicipio = codigoMunicipio;
    }

    public void setProvinciaCodigo(Integer provinciaCodigo)
    {
        this.provinciaCodigo = provinciaCodigo;
    }
}
