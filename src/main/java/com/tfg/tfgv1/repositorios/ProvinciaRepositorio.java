package com.tfg.tfgv1.repositorios;

import com.tfg.tfgv1.entidades.Provincia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ProvinciaRepositorio
{
    @PersistenceContext
    EntityManager em;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Provincia> buscar(Integer codigo)
    {
        return Optional.ofNullable(em.find(Provincia.class, codigo));
    }

    public void guardar(Provincia provincia)
    {
        em.persist(provincia);
    }

    public void actualizar(Provincia provincia)
    {
        em.merge(provincia);
    }

    public void borrar(Provincia provincia)
    {
        em.remove(em.merge(provincia));
    }
}
