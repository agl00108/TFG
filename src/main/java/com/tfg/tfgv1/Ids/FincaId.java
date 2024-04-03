package com.tfg.tfgv1.Ids;

import com.tfg.tfgv1.entidades.Zona;
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
            @JoinColumn(name = "MUNICIPIO_CODIGO", referencedColumnName = "MUNICIPIO_CODIGO"),
            @JoinColumn(name = "CODIGO_PROVINCIA", referencedColumnName = "PROVINCIA_CODIGO")
    })
    private Zona zona;

    public FincaId(Integer poligono, Integer parcela, Integer recinto, Zona zona)
    {
        this.poligono = poligono;
        this.parcela = parcela;
        this.recinto = recinto;
        this.zona = zona;
    }

    public FincaId()
    {
        this.poligono = -1;
        this.parcela = -1;
        this.recinto = -1;
        this.zona = null;
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
        return zona.getMunicipioCodigo();
    }

    public Integer getCodigoProvincia()
    {
        return zona.getProvinciaCodigo();
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

    public void setZona(Zona zona)
    {
        this.zona=zona;
    }

}
