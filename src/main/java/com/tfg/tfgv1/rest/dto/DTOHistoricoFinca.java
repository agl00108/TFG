package com.tfg.tfgv1.rest.dto;

import com.tfg.tfgv1.Ids.MunicipioId;
import com.tfg.tfgv1.entidades.*;

import java.time.LocalDate;
import java.util.Arrays;

public record DTOHistoricoFinca(
        LocalDate fecha,
        Integer fincaMunicipioCodigo,
        Integer fincaProvinciaCodigo,
        Integer fincaPoligono,
        Integer fincaParcela,
        Integer fincaRecinto,
        String zonaUbicacion,
        byte[] reflectancia,
        byte[] temperatura,
        Double lluvia,
        String nombreFuente,
        String tipoFuente)
{
    public DTOHistoricoFinca(HistoricoFinca historicoFinca)
    {
        this(
                historicoFinca.getFecha(),
                historicoFinca.getMunicipioCodigo(),
                historicoFinca.getProvinciaCodigo(),
                historicoFinca.getPoligono(),
                historicoFinca.getParcela(),
                historicoFinca.getRecinto(),
                historicoFinca.getZonaUbicacion(),
                historicoFinca.getReflectancia(),
                historicoFinca.getTemperatura(),
                historicoFinca.getLluvia(),
                historicoFinca.getNombreFuente(),
                historicoFinca.getTipoFuente()
        );
    }

    public HistoricoFinca aHistoricoFinca()
    {
        return new HistoricoFinca(
                this.fecha,
                new Finca(
                        0.0,
                        this.fincaPoligono,
                        this.fincaParcela,
                        this.fincaRecinto,
                        0,
                        new Zona(this.zonaUbicacion, null,null,"",
                                new Municipio(new MunicipioId(this.fincaMunicipioCodigo, new Provincia(this.fincaProvinciaCodigo, "")), ""), 0.0f)
                ),
                Arrays.toString(this.reflectancia),
                Arrays.toString(this.temperatura),
                this.lluvia,
                this.nombreFuente,
                this.tipoFuente
        );
    }
}
