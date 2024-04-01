/**
 * @file Objeto.java
 * @brief Clase para representar los Objetos del sistema
 */
package com.tfg.tfgv1.entidades;

import java.sql.SQLException;

public class Objeto
{
    private Integer idObjeto; //ID del objeto
    private String zonaUbicacion; //Nombre de la zona donde se ubica
    private Integer zonaMunicipioCodigo; //Código del municipio donde se ubica
    private Integer zonaProvinciaCodigo; //Código de la provincia donde se ubica la zona

    //private JGeometry prueba; Falta el SDO_GEOMETRY

    /**
     * @brief constructor por defecto
     */
    public Objeto()
    {
        idObjeto=-1;
        zonaUbicacion="";
        zonaMunicipioCodigo=-1;
        zonaProvinciaCodigo=-1;
    }

    /**
     * @brief Constructor por defecto
     * @param idObjeto ID del objeto
     * @param zonaUbicacion Nombre de la zona donde se ubica
     * @param zonaMunicipioCodigo Código del municipio donde se ubica
     * @param zonaProvinciaCodigo Código de la provincia donde se ubica la zona
     */
    public Objeto(Integer idObjeto, String zonaUbicacion, Integer zonaMunicipioCodigo, Integer zonaProvinciaCodigo)
    {
        this.idObjeto = idObjeto;
        this.zonaUbicacion = zonaUbicacion;
        this.zonaMunicipioCodigo = zonaMunicipioCodigo;
        this.zonaProvinciaCodigo = zonaProvinciaCodigo;
    }

    //GETTERS DE LA CLASE

    public Integer getIdObjeto()
    {
        return idObjeto;
    }

    public String getZonaUbicacion()
    {
        return zonaUbicacion;
    }

    public Integer getZonaMunicipioCodigo()
    {
        return zonaMunicipioCodigo;
    }

    public Integer getZonaProvinciaCodigo()
    {
        return zonaProvinciaCodigo;
    }

    //SETTERS DE LA CLASE

    public void setIdObjeto(Integer idObjeto)
    {
        this.idObjeto = idObjeto;
    }

    public void setZonaUbicacion(String zonaUbicacion)
    {
        this.zonaUbicacion = zonaUbicacion;
    }

    public void setZonaMunicipioCodigo(Integer zonaMunicipioCodigo)
    {
        this.zonaMunicipioCodigo = zonaMunicipioCodigo;
    }

    public void setZonaProvinciaCodigo(Integer zonaProvinciaCodigo)
    {
        this.zonaProvinciaCodigo = zonaProvinciaCodigo;
    }

    /**
     * @brief método toString para imprimir la información del Objeto
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Objeto{" +
                "idObjeto=" + idObjeto +
                ", zonaUbicacion='" + zonaUbicacion + '\'' +
                ", zonaMunicipioCodigo=" + zonaMunicipioCodigo +
                ", zonaProvinciaCodigo=" + zonaProvinciaCodigo +
                '}';
    }
}
