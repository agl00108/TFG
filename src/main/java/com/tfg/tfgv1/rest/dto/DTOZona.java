package com.tfg.tfgv1.rest.dto;

import com.tfg.tfgv1.Ids.MunicipioId;
import com.tfg.tfgv1.entidades.*;

    public record DTOZona(
            String ubicacion,
            Integer municipioCodigo,
            Integer provinciaCodigo,
            Float longitud,
            Float latitud,
            String descripcion,
            Float radio)
    {
        public DTOZona(Zona zona)
        {
            this(
                    zona.getUbicacion(),
                    zona.getMunicipioCodigo(),
                    zona.getProvinciaCodigo(),
                    zona.getLongitud(),
                    zona.getLatitud(),
                    zona.getDescripcion(),
                    zona.getRadio()
            );
        }

        public Zona aZona()
        {
            return new Zona(
                    this.ubicacion,
                    this.longitud,
                    this.latitud,
                    this.descripcion,
                    new Municipio(new MunicipioId(this.municipioCodigo, new Provincia(this.provinciaCodigo, "")), ""),
                    this.radio
            );
        }
}
