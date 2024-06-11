package com.tfg.tfgv1.rest.dto;

import com.tfg.tfgv1.entidades.Provincia;

public record DTOProvincia(
        Integer codigoProvincia,
        String nombreProvincia)
{
    public DTOProvincia(Provincia provincia)
    {
        this(
                provincia.getCodigoProvincia(),
                provincia.getNombreProvincia()
        );
    }

    public Provincia aProvincia()
    {
        return new Provincia(
                this.codigoProvincia,
                this.nombreProvincia
        );
    }
}
