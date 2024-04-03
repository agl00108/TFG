package com.tfg.tfgv1.Ids;

import com.tfg.tfgv1.entidades.Provincia;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

    @ManyToOne
    @JoinColumn(name = "PROVINCIA_CODIGO_PROVINCIA", referencedColumnName = "CODIGO_PROVINCIA")
    private Provincia provincia;

    public MunicipioId(Integer codigoMunicipio, Provincia provincia)
    {
        this.codigoMunicipio = codigoMunicipio;
        this.provincia = provincia;
    }

    public MunicipioId()
    {
        this.codigoMunicipio = -1;
        this.provincia = null;
    }

    public Integer getCodigoMunicipio()
    {
        return codigoMunicipio;
    }

    public Integer getProvinciaCodigo()
    {
        return provincia.getCodigoProvincia();
    }

    public void setCodigoMunicipio(Integer codigoMunicipio)
    {
        this.codigoMunicipio = codigoMunicipio;
    }

    public void setProvincia(Provincia provincia)
    {
        this.provincia = provincia;
    }
}
