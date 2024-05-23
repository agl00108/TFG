package com.tfg.tfgv1.rest.dto;

import com.tfg.tfgv1.entidades.Objeto;

public record DTOObjeto(
        Integer idObjeto,
        String tipoObjeto,
        String zonaUbicacion,
        Integer zonaMunicipioCodigo,
        Integer zonaProvinciaCodigo,
        DTOPoint puntoMedio)
{
    public DTOObjeto(Objeto objeto)
    {
        this(
                objeto.getIdObjeto(),
                objeto.getTipoObjeto(),
                objeto.getZona().getUbicacion(),
                objeto.getZona().getMunicipioCodigo(),
                objeto.getZona().getProvinciaCodigo(),
                new DTOPoint(objeto.getPuntoMedio())
        );
    }
}