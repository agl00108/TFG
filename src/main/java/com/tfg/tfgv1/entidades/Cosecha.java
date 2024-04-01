/**
 * @file Cosecha.java
 * @brief Clase para representar las Cosechas del sistema
 */
package com.tfg.tfgv1.entidades;

public class Cosecha
{
    private Integer idCosecha; //ID de la cosecha
    private String campania; //Año de la campaña (en el que empezó)
    private Integer municipioCodigo; //Código del municipio
    private Integer provinciaCodigo; //Código de la provincia
    private Double aceitunas; //KG de aceitunas recogidos
    private Double aceite; //KG de aceite recogidos
    private Integer fincaPoligono; //Polígono de la finca
    private Integer fincaParcela; //Parcela de la finca
    private Integer fincaRecinto; //Recinto de la finca

    /**
     * @brief constructor por defecto
     */
    public Cosecha()
    {
        idCosecha=-1;
        campania="";
        municipioCodigo=-1;
        provinciaCodigo=-1;
        aceite=0.0;
        aceitunas=0.0;
        fincaPoligono=-1;
        fincaParcela=-1;
        fincaRecinto=-1;
    }

    /**
     * @brief Constructor de la clase
     * @param idCosecha ID de la cosecha
     * @param campania Año de la campaña (en el que empezó)
     * @param municipioCodigo Código del municipio
     * @param provinciaCodigo Código de la provincia
     * @param aceitunas KG de aceitunas recogidos
     * @param aceite KG de aceite recogidos
     * @param fincaPoligono Polígono de la finca
     * @param fincaParcela Parcela de la finca
     * @param fincaRecinto Recinto de la finca
     */
    public Cosecha(Integer idCosecha, String campania, Integer municipioCodigo,
                   Integer provinciaCodigo, Double aceitunas, Double aceite, Integer fincaPoligono,
                   Integer fincaParcela, Integer fincaRecinto)
    {
        this.idCosecha = idCosecha;
        this.campania = campania;
        this.municipioCodigo = municipioCodigo;
        this.provinciaCodigo = provinciaCodigo;
        this.aceitunas = aceitunas;
        this.aceite = aceite;
        this.fincaPoligono = fincaPoligono;
        this.fincaParcela = fincaParcela;
        this.fincaRecinto = fincaRecinto;
    }

    //GETTERS DE LA CLASE

    public Integer getIdCosecha()
    {
        return idCosecha;
    }

    public String getCampania()
    {
        return campania;
    }

    public Integer getMunicipioCodigo()
    {
        return municipioCodigo;
    }

    public Integer getProvinciaCodigo()
    {
        return provinciaCodigo;
    }

    public Double getAceitunas()
    {
        return aceitunas;
    }

    public Double getAceite()
    {
        return aceite;
    }

    public Integer getFincaPoligono()
    {
        return fincaPoligono;
    }

    public Integer getFincaParcela()
    {
        return fincaParcela;
    }

    public Integer getFincaRecinto()
    {
        return fincaRecinto;
    }

    //SETTERS DE LA CLASE
    public void setIdCosecha(Integer idCosecha)
    {
        this.idCosecha = idCosecha;
    }

    public void setCampania(String campania)
    {
        this.campania = campania;
    }

    public void setMunicipioCodigo(Integer municipioCodigo)
    {
        this.municipioCodigo = municipioCodigo;
    }

    public void setProvinciaCodigo(Integer provinciaCodigo)
    {
        this.provinciaCodigo = provinciaCodigo;
    }

    public void setAceitunas(Double aceitunas)
    {
        this.aceitunas = aceitunas;
    }

    public void setAceite(Double aceite)
    {
        this.aceite = aceite;
    }

    public void setFincaPoligono(Integer fincaPoligono)
    {
        this.fincaPoligono = fincaPoligono;
    }

    public void setFincaParcela(Integer fincaParcela)
    {
        this.fincaParcela = fincaParcela;
    }

    public void setFincaRecinto(Integer fincaRecinto)
    {
        this.fincaRecinto = fincaRecinto;
    }

    /**
     * @brief método toString para imprimir la información de la Cosecha
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Cosecha{" +
                "idCosecha=" + idCosecha +
                ", campania='" + campania + '\'' +
                ", municipioCodigo=" + municipioCodigo +
                ", provinciaCodigo=" + provinciaCodigo +
                ", aceitunas=" + aceitunas +
                ", aceite=" + aceite +
                ", fincaPoligono=" + fincaPoligono +
                ", fincaParcela=" + fincaParcela +
                ", fincaRecinto=" + fincaRecinto +
                '}';
    }
}
