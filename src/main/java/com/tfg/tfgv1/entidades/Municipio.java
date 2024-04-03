/**
 * @file Municipio.java
 * @brief Clase para representar los Municipios del sistema
 */
package com.tfg.tfgv1.entidades;

import com.tfg.tfgv1.Ids.MunicipioId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "MUNICIPIO", schema="ALBAGOMEZ")
public class Municipio
{
    @EmbeddedId
    private MunicipioId id;

    @NotBlank
    @Column(name = "NOMBRE")
    private String nombre; //Nombre del municipio

    /**
     * @brief constructor por defecto
     */
    public Municipio()
    {
        this.id = new MunicipioId();
        this.nombre = "";
    }

    /**
     * @brief constructor parametrizado
     * @param id Código del municipio y provincia
     * @param nombre Nombre del municipio
     */
    public Municipio(MunicipioId id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    //GETTERS DE LA CLASE
    public MunicipioId getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }


    //SETTERS DE LA CLASE
    public void setId(MunicipioId id)
    {
        this.id = id;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio)
    {
        this.id.setCodigoMunicipio(codigoMunicipio);
    }

    public Integer getCodigoMunicipio()
    {
        return this.id.getCodigoMunicipio();
    }

    public Integer getProvinciaCodigo()
    {
        return this.id.getProvinciaCodigo();
    }
    public void setProvinciaCodigo(Integer provinciaCodigo)
    {
        this.id.setProvinciaCodigo(provinciaCodigo);
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @brief método toString para imprimir la información del Municipio
     * @return string con los parámetros
     */
    @Override
    public String toString()
    {
        return "Municipio{" +
                "codigoMunicipio=" + id.getCodigoMunicipio() +
                ", nombre='" + nombre + '\'' +
                ", provinciaCodigo=" + id.getProvinciaCodigo() +
                '}';
    }
}
