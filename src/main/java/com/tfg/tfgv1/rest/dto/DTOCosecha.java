package com.tfg.tfgv1.rest.dto;

import com.tfg.tfgv1.entidades.Cosecha;

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
        String zonaUbicacion
)
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
}
