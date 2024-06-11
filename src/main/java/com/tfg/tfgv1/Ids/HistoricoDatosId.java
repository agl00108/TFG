package com.tfg.tfgv1.Ids;

import com.tfg.tfgv1.entidades.Objeto;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class HistoricoDatosId implements Serializable
{
    @NotNull
    @Getter
    @Setter
    @Column(name = "FECHA")
    private LocalDate fecha; //Fecha de la toma de datos

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_OBJETO", referencedColumnName = "ID_OBJETO")
    @Getter
    @Setter
    private Objeto objeto; //ID del objeto al que hace referencia

    public HistoricoDatosId()
    {
        fecha=LocalDate.now();
        objeto=null;
    }

    public HistoricoDatosId(LocalDate fecha, Objeto objeto)
    {
        this.fecha = fecha;
        this.objeto = objeto;
    }
}
