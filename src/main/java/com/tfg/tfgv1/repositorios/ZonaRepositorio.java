package com.tfg.tfgv1.repositorios;

import com.tfg.tfgv1.Ids.ZonaId;
import com.tfg.tfgv1.entidades.Zona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ZonaRepositorio
{
    @PersistenceContext
    EntityManager em;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Zona> buscar(ZonaId codigo)
    {
        return Optional.ofNullable(em.find(Zona.class, codigo));
    }

    public void guardar(Zona zona)
    {
        em.persist(zona);
    }

    public void actualizar(Zona zona)
    {
        em.merge(zona);
    }

    public void borrar(Zona zona)
    {
        em.remove(em.merge(zona));
    }
}
