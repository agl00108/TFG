package com.tfg.tfgv1.rest.dto;

import com.tfg.tfgv1.Ids.MunicipioId;
import com.tfg.tfgv1.entidades.Finca;
import com.tfg.tfgv1.entidades.Municipio;
import com.tfg.tfgv1.entidades.Provincia;
import com.tfg.tfgv1.entidades.Zona;

public record DTOFinca(
        Integer poligono,
        Integer parcela,
        Integer recinto,
        Double area,
        Integer anioSigpac,
        String zonaUbicacion,
        Integer municipioCodigo,
        Integer provinciaCodigo)
{
    public DTOFinca(Finca finca)
    {
        this(
                finca.getPoligono(),
                finca.getParcela(),
                finca.getRecinto(),
                finca.getArea(),
                finca.getAnioSigpac(),
                finca.getZonaUbicacion(),
                finca.getMunicipioCodigo(),
                finca.getCodigoProvincia()
        );
    }

    public Finca aFinca()
    {
        return new Finca(
                this.area,
                this.poligono,
                this.parcela,
                this.recinto,
                this.anioSigpac,
                new Zona(this.zonaUbicacion, null,null,"",
                        new Municipio(new MunicipioId(this.municipioCodigo, new Provincia(this.provinciaCodigo, "")), ""), 0.0f)
        );
    }
}