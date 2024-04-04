package com.tfg.tfgv1.repositorios;

import com.tfg.tfgv1.entidades.Objeto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ObjetoRepositorio
{
    @PersistenceContext
    EntityManager em;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Objeto> buscar(Integer codigo)
    {
        return Optional.ofNullable(em.find(Objeto.class, codigo));
    }

    public void guardar(Objeto objeto)
    {
        em.persist(objeto);
    }

    public void actualizar(Objeto objeto)
    {
        em.merge(objeto);
    }

    public void borrar(Objeto objeto)
    {
        em.remove(em.merge(objeto));
    }
}
