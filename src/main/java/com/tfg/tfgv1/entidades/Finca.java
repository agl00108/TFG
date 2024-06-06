/**
 * @file Finca.java
 * @brief Clase para representar las Fincas del sistema
 */

package com.tfg.tfgv1.entidades;

import com.tfg.tfgv1.Ids.FincaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import javax.validation.constraints.Min;

/**
 * @brief Clase para representar las Fincas del sistema
 */
@Entity
public class Finca
{
    @EmbeddedId
    FincaId id;
    @Min(0)
    @Column(name = "AREA")
    private Double area; //Area en m2 de la finca
    @Min(0)
    @Column(name = "AÑO_SIGPAC")
    private Integer anioSigpac; //Año de inscripción del código SIGPAC

    /**
     * @brief constructor por defecto
     */
    public Finca()
    {
        id = new FincaId();
        area=0.0;
        anioSigpac=0;
    }

    /**
     * @brief Constructor parametrizado
     * @param area Area en m2 de la finca
     * @param poligono Poligono del código SIGPAC
     * @param parcela Parcela del código SIGPAC
     * @param recinto Recinto del código SIGPAC
     * @param anioSigpac Año de inscripción del código SIGPAC
     * @param zona Código del municipio
     */
    public Finca(Double area, Integer poligono, Integer parcela, Integer recinto, Integer anioSigpac, Zona zona)
    {
        this.area = area;
        this.id = new FincaId(poligono, parcela, recinto, zona);
        this.anioSigpac = anioSigpac;
    }

    //GETTERS DE LA CLASE

    public Double getArea()
    {
        return area;
    }

    public Integer getPoligono()
    {
        return id.getPoligono();
    }

    public Integer getParcela()
    {
        return id.getParcela();
    }

    public Integer getRecinto()
    {
        return id.getRecinto();
    }

    public Integer getAnioSigpac()
    {
        return anioSigpac;
    }

    public String getZonaUbicacion()
    {
        return id.getZonaUbicacion();
    }

    public Integer getMunicipioCodigo()
    {
        return id.getMunicipio();
    }

    public Integer getCodigoProvincia()
    {
        return id.getCodigoProvincia();
    }

    /**
     * @brief getter del código del SIGPAC en formato Provincia:Municipio:Agregado:Zona:Polígono:Parcela
     */
    public String getSIGPAC()
    {
        String sigpac=id.getCodigoProvincia()+":"+id.getMunicipio()+":"+0+":"+0+":"+id.getPoligono()+":"+id.getParcela()+":"+id.getRecinto();
        return sigpac;
    }

    //SETTERS DE LA CLASE

    public void setArea(Double area)
    {
        this.area = area;
    }

    public void setPoligono(Integer poligono)
    {
        this.id.setPoligono(poligono);
    }

    public void setParcela(Integer parcela)
    {
        this.id.setParcela(parcela);
    }

    public void setRecinto(Integer recinto)
    {
        this.id.setRecinto(recinto);
    }

    public void setAnioSigpac(Integer anioSigpac)
    {
        this.anioSigpac = anioSigpac;
    }

    public void setZona(Zona zona)
    {
        this.id.setZona(zona);
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
                ", poligono=" + id.getPoligono() +
                ", parcela=" + id.getParcela() +
                ", recinto=" + id.getRecinto() +
                ", anioSigpac=" + anioSigpac +
                ", zonaUbicacion='" + id.getZonaUbicacion() + '\'' +
                ", municipioCodigo=" + id.getMunicipio() +
                ", codigoProvincia=" + id.getCodigoProvincia() +
                '}';
    }
}
