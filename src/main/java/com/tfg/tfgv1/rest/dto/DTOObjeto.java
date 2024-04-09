package com.tfg.tfgv1.rest.dto;

import com.tfg.tfgv1.entidades.*;
import com.tfg.tfgv1.Ids.MunicipioId;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

public record DTOObjeto(
        Integer idObjeto,
        String tipoObjeto,
        String zonaUbicacion,
        Integer zonaMunicipioCodigo,
        Integer zonaProvinciaCodigo,
        Polygon poligonoEnvolvente,
        Point puntoMedio)
{
    public DTOObjeto(Objeto objeto)
    {
        this(
                objeto.getIdObjeto(),
                objeto.getTipoObjeto(),
                objeto.getZona().getUbicacion(),
                objeto.getZona().getMunicipioCodigo(),
                objeto.getZona().getProvinciaCodigo(),
                objeto.getPoligonoEnvolvente(),
                objeto.getPuntoMedio()
        );
    }

    public Objeto aObjeto()
    {
        return new Objeto(
                this.tipoObjeto,
                new Zona(
                        this.zonaUbicacion,
                        null, // Longitud (asumido null, ajustar según sea necesario)
                        null, // Latitud (asumido null, ajustar según sea necesario)
                        "", // Descripción (asumido "", ajustar según sea necesario)
                        new Municipio(new MunicipioId(this.zonaMunicipioCodigo, new Provincia(this.zonaProvinciaCodigo, "")), ""),
                        null // Radio (asumido null, ajustar según sea necesario)
                ),
                this.poligonoEnvolvente,
                this.puntoMedio
        );
    }
}