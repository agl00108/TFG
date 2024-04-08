package com.tfg.tfgv1.repositorios;

import com.tfg.tfgv1.Ids.HistoricoDatosId;
import com.tfg.tfgv1.entidades.HistoricoDatos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class HistoricoDatosRepositorio
{
    @PersistenceContext
    EntityManager em;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<HistoricoDatos> buscar(HistoricoDatosId id)
    {
        return Optional.ofNullable(em.find(HistoricoDatos.class, id));
    }

    public void guardar(HistoricoDatosId historicoDatosId)
    {
        em.persist(historicoDatosId);
    }

    public void actualizar(HistoricoDatosId historicoDatosId)
    {
        em.merge(historicoDatosId);
    }

    public void borrar(HistoricoDatosId historicoDatosId)
    {
        em.remove(em.merge(historicoDatosId));
    }
}