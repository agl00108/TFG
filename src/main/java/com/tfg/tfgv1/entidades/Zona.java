/**
 * @file Zona.java
 * @brief Clase para representar las Zonas del sistema
 */
package com.tfg.tfgv1.entidades;

public class Zona
{
    private String ubicacion; //Ubicacion de la zona
    private Float longitud; //Longitud de la zona
    private Float latitud; //Latitud de la zona
    private String descripcion; //Descripción de la zona
    private Integer municipioCodigo; //Código del municipio donde se ubica
    private Integer provinciaCodigo; //Código de la provincia donde se ubica
    private Float radio; //Radio de extensión de la zona

    /**
     * @brief constructor por defecto
     */
    public Zona()
    {
        ubicacion="";
        longitud= 0.0F;
        latitud= 0.0F;
        descripcion="";
        municipioCodigo=-1;
        provinciaCodigo=-1;
        radio=0.0F;
    }

    /**
     * @brief Constructor parametrizado
     * @param ubicacion Ubicacion de la zona
     * @param longitud Longitud de la zona
     * @param latitud Latitud de la zona
     * @param descripcion Descripción de la zona
     * @param municipioCodigo Código del municipio donde se ubica
     * @param provinciaCodigo Código de la provincia donde se ubica
     * @param radio Radio de extensión de la zona
     */
    public Zona(String ubicacion, Float longitud, Float latitud, String descripcion,
                Integer municipioCodigo, Integer provinciaCodigo, Float radio)
    {
        this.ubicacion = ubicacion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.descripcion = descripcion;
        this.municipioCodigo = municipioCodigo;
        this.provinciaCodigo = provinciaCodigo;
        this.radio = radio;
    }

    //GETTERS DE LA CLASE

    public String getUbicacion()
    {
        return ubicacion;
    }

    public Float getLongitud()
    {
        return longitud;
    }

    public Float getLatitud()
    {
        return latitud;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public Integer getMunicipioCodigo()
    {
        return municipioCodigo;
    }

    public Integer getProvinciaCodigo()
    {
        return provinciaCodigo;
    }

    public Float getRadio()
    {
        return radio;
    }

    //SETTERS DE LA CLASE
    public void setUbicacion(String ubicacion)
    {
        this.ubicacion = ubicacion;
    }

    public void setLongitud(Float longitud)
    {
        this.longitud = longitud;
    }

    public void setLatitud(Float latitud)
    {
        this.latitud = latitud;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public void setMunicipioCodigo(Integer municipioCodigo)
    {
        this.municipioCodigo = municipioCodigo;
    }

    public void setProvinciaCodigo(Integer provinciaCodigo)
    {
        this.provinciaCodigo = provinciaCodigo;
    }

    public void setRadio(Float radio)
    {
        this.radio = radio;
    }

    /**
     * @brief método toString para imprimir la información de la Zona
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Zona{" +
                "ubicacion='" + ubicacion + '\'' +
                ", longitud=" + longitud +
                ", latitud=" + latitud +
                ", descripcion='" + descripcion + '\'' +
                ", municipioCodigo=" + municipioCodigo +
                ", provinciaCodigo=" + provinciaCodigo +
                ", radio=" + radio +
                '}';
    }
}
