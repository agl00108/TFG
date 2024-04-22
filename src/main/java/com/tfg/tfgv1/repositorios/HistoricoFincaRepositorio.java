package com.tfg.tfgv1.repositorios;

import com.tfg.tfgv1.Ids.HistoricoFincaId;
import com.tfg.tfgv1.entidades.Finca;
import com.tfg.tfgv1.entidades.HistoricoFinca;
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
public class HistoricoFincaRepositorio
{
    @PersistenceContext
    EntityManager em;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<HistoricoFinca> buscar(HistoricoFincaId id)
    {
        return Optional.ofNullable(em.find(HistoricoFinca.class, id));
    }

    public void guardar(HistoricoFinca historicoFinca)
    {
        em.persist(historicoFinca);
    }

    public void actualizar(HistoricoFinca historicoFinca)
    {
        em.merge(historicoFinca);
    }

    public void borrar(HistoricoFinca historicoFinca)
    {
        em.remove(em.merge(historicoFinca));
    }

    /**
     * @brief Obtener las Fincas que tienen datos históricos
     * @return Lista de Fincas
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Finca> obtenerFincasConHistorico()
    {
        String jpql = "SELECT DISTINCT hf.id.finca FROM HistoricoFinca hf";
        TypedQuery<Finca> query = em.createQuery(jpql, Finca.class);
        return query.getResultList();
    }

}
