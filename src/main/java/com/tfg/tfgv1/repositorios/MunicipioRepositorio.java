package com.tfg.tfgv1.repositorios;

import com.tfg.tfgv1.Ids.MunicipioId;
import com.tfg.tfgv1.entidades.Municipio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class MunicipioRepositorio
{
    @PersistenceContext
    EntityManager em;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Municipio> buscar(MunicipioId codigo)
    {
        return Optional.ofNullable(em.find(Municipio.class, codigo));
    }

    public void guardar(Municipio municipio)
    {
        em.persist(municipio);
    }

    public void actualizar(Municipio municipio)
    {
        em.merge(municipio);
    }

    public void borrar(Municipio municipio)
    {
        em.remove(em.merge(municipio));
    }
}
