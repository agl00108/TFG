package com.tfg.tfgv1.rest.dto;

import com.tfg.tfgv1.Ids.MunicipioId;
import com.tfg.tfgv1.entidades.Municipio;
import com.tfg.tfgv1.entidades.Provincia;

public record DTOMunicipio(
        Integer codigoMunicipio,
        Integer provinciaCodigo,
        String nombre)
{
    public DTOMunicipio(Municipio municipio)
    {
        this(
                municipio.getId().getCodigoMunicipio(),
                municipio.getId().getProvinciaCodigo(),
                municipio.getNombre()
        );
    }

    public Municipio aMunicipio()
    {
        return new Municipio( new MunicipioId(this.codigoMunicipio, new Provincia(this.provinciaCodigo,
                "")), this.nombre);
    }

}

