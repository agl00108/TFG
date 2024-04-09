package com.tfg.tfgv1.rest.dto;

import com.tfg.tfgv1.entidades.HistoricoDatos;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public record DTOHistoricoDatos(
        LocalDate fecha,
        Integer idObjeto,
        Double volumen,
        byte[] reflectancia,
        String nombreFuente,
        String tipoFuente)
{
    public DTOHistoricoDatos(HistoricoDatos historicoDatos)
    {
        this(
                historicoDatos.getFecha(),
                historicoDatos.getIdObjeto(),
                historicoDatos.getVolumen(),
                historicoDatos.getReflectancia().getBytes(StandardCharsets.UTF_8),
                historicoDatos.getNombreFuente(),
                historicoDatos.getTipoFuente()
        );
    }
/*
    public HistoricoDatos aHistoricoDatos()
    {
        return new HistoricoDatos(
                this.fecha,
                this.volumen,
                Arrays.toString(this.reflectancia),
               // new Objeto("",this.idObjeto, null, null, null, null),
                this.nombreFuente,
                this.tipoFuente
        );
    }*/
}
