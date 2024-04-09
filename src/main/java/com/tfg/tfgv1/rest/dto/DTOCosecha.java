package com.tfg.tfgv1.rest.dto;

import com.tfg.tfgv1.Ids.MunicipioId;
import com.tfg.tfgv1.entidades.*;

public record DTOCosecha(
        Integer idCosecha,
        String campania,
        Double aceitunas,
        Double aceite,
        Integer municipioCodigo,
        Integer provinciaCodigo,
        Integer poligono,
        Integer parcela,
        Integer recinto,
        String zonaUbicacion)
{
    public DTOCosecha(Cosecha cosecha)
    {
        this(
                cosecha.getIdCosecha(),
                cosecha.getCampania(),
                cosecha.getAceitunas(),
                cosecha.getAceite(),
                cosecha.getMunicipioCodigo(),
                cosecha.getProvinciaCodigo(),
                cosecha.getPoligono(),
                cosecha.getParcela(),
                cosecha.getRecinto(),
                cosecha.getZonaUbicacion()
        );
    }

    public Cosecha aCosecha()
    {
        return new Cosecha(
                this.idCosecha,
                this.campania,
                this.aceitunas,
                this.aceite,
                new Finca(
                        0.0,
                        this.poligono,
                        this.parcela,
                        this.recinto,
                        0,
                        new Zona(this.zonaUbicacion, null,null,"",
                                new Municipio(new MunicipioId(this.municipioCodigo, new Provincia(this.provinciaCodigo, "")), ""), 0.0f)
                )
        );
    }
}
