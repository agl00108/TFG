package com.tfg.tfgv1.repositorios;

import com.tfg.tfgv1.Ids.HistoricoDatosId;
import com.tfg.tfgv1.entidades.HistoricoDatos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public void guardar(HistoricoDatos historicoDatos)
    {
        em.persist(historicoDatos);
    }

    public void actualizar(HistoricoDatos historicoDatos)
    {
        em.merge(historicoDatos);
    }

    public void borrar(HistoricoDatos historicoDatos)
    {
        em.remove(em.merge(historicoDatos));
    }

    /**
     * @brief Obtener los años de los que se tienen datos históricos de una finca de satélite
     * @param anio Año del que se quieren obtener los datos
     * @param id ID del objeto al que se refieren los datos
     * @return Lista de HistoricoDatos
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<HistoricoDatos> obtenerHistoricoOlivoAnioSat(int anio, int id)
    {
        String jpql = "SELECT h FROM HistoricoDatos h WHERE h.id.objeto.id = :id " +
                "AND EXTRACT(YEAR FROM h.id.fecha) = :anio AND h.tipoFuente = 'Satelite'";
        TypedQuery<HistoricoDatos> query = em.createQuery(jpql, HistoricoDatos.class)
                .setParameter("id", id)
                .setParameter("anio", anio);
        return query.getResultList();
    }
    /**
     * @brief Obtener los años de los que se tienen datos históricos de una finca de satélite
     * @param anio Año del que se quieren obtener los datos
     * @param id ID del objeto al que se refieren los datos
     * @return Lista de HistoricoDatos
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<HistoricoDatos> obtenerHistoricoOlivoAnioDron(int anio, int id)
    {
        String jpql = "SELECT h FROM HistoricoDatos h WHERE h.id.objeto.id = :id " +
                "AND EXTRACT(YEAR FROM h.id.fecha) = :anio AND h.tipoFuente = 'Dron'";
        TypedQuery<HistoricoDatos> query = em.createQuery(jpql, HistoricoDatos.class)
                .setParameter("id", id)
                .setParameter("anio", anio);
        return query.getResultList();
    }
}