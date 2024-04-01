package com.tfg.tfgv1.entidades;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class Historico_datos
{
    private LocalDate fecha;
    private Double volumen;
    @JsonProperty("Reflectancia")
    private String reflectancia;

    private Integer idObjeto;
    private String nombreFuente;
    private String tipoFuente;
}
