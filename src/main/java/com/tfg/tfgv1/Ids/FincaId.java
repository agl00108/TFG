package com.tfg.tfgv1.Ids;

import com.tfg.tfgv1.entidades.Municipio;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class FincaId implements Serializable
{
    @NotNull
    @Column(name = "POLIGONO")
    private Integer poligono; //Poligono del código SIGPAC
    @NotNull
    @Column(name = "PARCELA")
    private Integer parcela; //Parcela del código SIGPAC
    @NotNull
    @Column(name = "RECINTO")
    private Integer recinto; //Recinto del código SIGPAC
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "MUNICIPIO_CODIGO", referencedColumnName = "CODIGO_MUNICIPIO"),
            @JoinColumn(name = "PROVINCIA_CODIGO", referencedColumnName = "PROVINCIA_CODIGO_PROVINCIA")
    })
    private Municipio municipio;
    public FincaId(Integer poligono, Integer parcela, Integer recinto, Municipio municipioCodigo)
    {
        this.poligono = poligono;
        this.parcela = parcela;
        this.recinto = recinto;
        this.municipio = municipioCodigo;
    }

    public FincaId()
    {
        this.poligono = -1;
        this.parcela = -1;
        this.recinto = -1;
        this.municipio = null;
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

    public Integer getMunicipio()
    {
        return municipio.getId().getCodigoMunicipio();
    }

    public Integer getCodigoProvincia()
    {
        return municipio.getId().getProvinciaCodigo();
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

    public void setMunicipio(Integer municipioCodigo)
    {
        this.municipio = municipioCodigo;
    }

    public void setCodigoProvincia(Integer codigoProvincia)
    {
        this.codigoProvincia = codigoProvincia;
    }

}
