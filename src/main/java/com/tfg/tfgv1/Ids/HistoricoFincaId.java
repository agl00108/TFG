package com.tfg.tfgv1.Ids;

import com.tfg.tfgv1.entidades.Finca;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class HistoricoFincaId implements Serializable
{
    @NotNull
    @Column(name = "FECHA")
    private LocalDate fecha; //Fecha de la toma de datos
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "MUNICIPIO_CODIGO", referencedColumnName = "MUNICIPIO_CODIGO"),
            @JoinColumn(name = "PROVINCIA_CODIGO", referencedColumnName = "PROVINCIA_CODIGO"),
            @JoinColumn(name = "POLIGONO", referencedColumnName = "POLIGONO"),
            @JoinColumn(name = "PARCELA", referencedColumnName = "PARCELA"),
            @JoinColumn(name = "RECINTO", referencedColumnName = "RECINTO"),
            @JoinColumn(name = "ZONA_UBICACION", referencedColumnName = "ZONA_UBICACION")
    })
    private Finca finca; //Finca

    public HistoricoFincaId()
    {
        fecha=LocalDate.now();
        finca=null;
    }

    public HistoricoFincaId(LocalDate fecha, Finca finca)
    {
        this.fecha = fecha;
        this.finca = finca;
    }

    public LocalDate getFecha()
    {
        return fecha;
    }

    public Finca getFinca()
    {
        return finca;
    }

    public void setFecha(LocalDate fecha)
    {
        this.fecha = fecha;
    }

    public void setFinca(Finca finca)
    {
        this.finca = finca;
    }
}
