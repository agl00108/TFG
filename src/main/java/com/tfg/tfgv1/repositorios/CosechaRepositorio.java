package com.tfg.tfgv1.repositorios;

import com.tfg.tfgv1.entidades.Cosecha;
import com.tfg.tfgv1.entidades.Finca;
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
public class CosechaRepositorio
{
    @PersistenceContext
    EntityManager em;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Cosecha> buscar(Integer codigo)
    {
        return Optional.ofNullable(em.find(Cosecha.class, codigo));
    }

    public void guardar(Cosecha cosecha)
    {
        em.persist(cosecha);
    }

    public void actualizar(Cosecha cosecha)
    {
        em.merge(cosecha);
    }

    public void borrar(Cosecha cosecha)
    {
        em.remove(em.merge(cosecha));
    }

    /**
     * @brief función para obtener todos los datos de las cosechas de una finca
     * @param finca
     * @return lista de cosechas
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Cosecha> obtenerCosechasPorFinca(Finca finca)
    {
        TypedQuery<Cosecha> query = em.createQuery("SELECT c FROM Cosecha c WHERE c.finca = :finca", Cosecha.class);
        query.setParameter("finca", finca);
        return query.getResultList();
    }
}
