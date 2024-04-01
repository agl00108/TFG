/**
 * @file Finca.java
 * @brief Clase para representar las Fincas del sistema
 */

package com.tfg.tfgv1.entidades;

import jakarta.persistence.Entity;

import javax.validation.constraints.*;

@Entity
public class Finca
{
    @Min(0)
    private Double area; //Area en m2 de la finca
    @NotNull
    private Integer poligono; //Poligono del código SIGPAC
    @NotNull
    private Integer parcela; //Parcela del código SIGPAC
    @NotNull
    private Integer recinto; //Recinto del código SIGPAC
    @Min(0)
    private Integer anioSigpac; //Año de inscripción del código SIGPAC
    @NotBlank
    private String zonaUbicacion; //Ubicación de la zona
    @NotNull
    private Integer municipioCodigo; //Código del municipio
    @NotNull
    private Integer codigoProvincia; //Código de la provincia

    /**
     * @brief constructor por defecto
     */
    public Finca()
    {
        area=0.0;
        poligono=0;
        parcela=0;
        recinto=0;
        anioSigpac=0;
        zonaUbicacion="defecto";
        municipioCodigo=0;
        codigoProvincia=0;
    }

    /**
     * @brief Constructor parametrizado
     * @param area Area en m2 de la finca
     * @param poligono Poligono del código SIGPAC
     * @param parcela Parcela del código SIGPAC
     * @param recinto Recinto del código SIGPAC
     * @param anioSigpac Año de inscripción del código SIGPAC
     * @param zonaUbicacion Ubicación de la zona
     * @param municipioCodigo Código del municipio
     * @param codigoProvincia Código de la provincia
     */
    public Finca(Double area, Integer poligono, Integer parcela, Integer recinto, Integer anioSigpac,
                 String zonaUbicacion, Integer municipioCodigo, Integer codigoProvincia)
    {
        this.area = area;
        this.poligono = poligono;
        this.parcela = parcela;
        this.recinto = recinto;
        this.anioSigpac = anioSigpac;
        this.zonaUbicacion = zonaUbicacion;
        this.municipioCodigo = municipioCodigo;
        this.codigoProvincia = codigoProvincia;
    }

    //GETTERS DE LA CLASE

    public Double getArea()
    {
        return area;
    }

    public Integer getPoligono()
    {
        return poligono;
    }

    public Integer getParcela()
    {
        return parcela;
    }

    public Integer getRecinto()
    {
        return recinto;
    }

    public Integer getAnioSigpac()
    {
        return anioSigpac;
    }

    public String getZonaUbicacion()
    {
        return zonaUbicacion;
    }

    public Integer getMunicipioCodigo()
    {
        return municipioCodigo;
    }

    public Integer getCodigoProvincia()
    {
        return codigoProvincia;
    }

    /**
     * @brief getter del código del SIGPAC en formato Provincia:Municipio:Agregado:Zona:Polígono:Parcela
     */
    public String getSIGPAC()
    {
        String sigpac=codigoProvincia+":"+municipioCodigo+":"+0+":"+0+":"+poligono+":"+parcela+":"+recinto;
        return sigpac;
    }

    //SETTERS DE LA CLASE

    public void setArea(Double area)
    {
        this.area = area;
    }

    public void setPoligono(Integer poligono)
    {
        this.poligono = poligono;
    }

    public void setParcela(Integer parcela)
    {
        this.parcela = parcela;
    }

    public void setRecinto(Integer recinto)
    {
        this.recinto = recinto;
    }

    public void setAnioSigpac(Integer anioSigpac)
    {
        this.anioSigpac = anioSigpac;
    }

    public void setZonaUbicacion(String zonaUbicacion)
    {
        this.zonaUbicacion = zonaUbicacion;
    }

    public void setMunicipioCodigo(Integer municipioCodigo)
    {
        this.municipioCodigo = municipioCodigo;
    }

    public void setCodigoProvincia(Integer codigoProvincia)
    {
        this.codigoProvincia = codigoProvincia;
    }

    /**
     * @brief setter de los campos de la finca de acuerdo con el código sigpac
     * @param sigpac código sigpac
     * @pre el formato del string debe ser codigoProvincia:municipioCodigo:0:0:poligono:parcela:recinto
     */
    public void setSIGPAC(String sigpac) throws IllegalArgumentException
    {
        String[] campos = sigpac.split(":");
        if (campos.length == 7)
        {
            try
            {
                int provincia = Integer.parseInt(campos[0]);
                int municipio = Integer.parseInt(campos[1]);
                int poligono = Integer.parseInt(campos[4]);
                int parcela = Integer.parseInt(campos[5]);
                int recinto = Integer.parseInt(campos[6]);

                if (provincia < 0 || municipio < 0 || poligono < 0 || parcela < 0 || recinto < 0)
                {
                    throw new IllegalArgumentException("Los campos numéricos deben ser mayores o iguales a 0");
                }

                this.codigoProvincia = provincia;
                this.municipioCodigo = municipio;
                this.poligono = poligono;
                this.parcela = parcela;
                this.recinto = recinto;
            } catch (NumberFormatException e)
            {
                throw new IllegalArgumentException("Los campos numéricos deben ser números enteros válidos");
            }
        } else
        {
            throw new IllegalArgumentException("El formato del String SIGPAC no es válido");
        }
    }

    /**
     * @brief método toString para imprimir la información de la Finca
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Finca{" +
                "area=" + area +
                ", poligono=" + poligono +
                ", parcela=" + parcela +
                ", recinto=" + recinto +
                ", anioSigpac=" + anioSigpac +
                ", zonaUbicacion='" + zonaUbicacion + '\'' +
                ", municipioCodigo=" + municipioCodigo +
                ", codigoProvincia=" + codigoProvincia +
                '}';
    }
}
