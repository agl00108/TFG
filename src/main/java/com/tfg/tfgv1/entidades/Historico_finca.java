package com.tfg.tfgv1.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Historico_finca
{
    private LocalDate fecha;
    private Integer municipioCodigo;
    private Integer provinciaCodigo;
    private Integer poligono;
    private Integer parcela;
    private Integer recinto;
    @JsonProperty("Reflectancia")
    private String reflectancia;
    @JsonProperty("Temperatura")
    private String temperatura;
    private Double lluvia;
    private String nombreFuente;
    private String tipoFuente;
}
