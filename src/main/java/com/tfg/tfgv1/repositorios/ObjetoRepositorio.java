package com.tfg.tfgv1.repositorios;

import com.tfg.tfgv1.entidades.Objeto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    /**
     * @brief Obtener todos los objetos de una zona
     * @return Lista de objetos
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Objeto> obtenerObjetosFinca(String zona_ubicacion, int municipio_codigo, int provincia_codigo)
    {
        String jpql = "SELECT DISTINCT o FROM Objeto o WHERE o.zona.id.ubicacion = :zona_ubicacion " +
                "AND o.zona.id.municipio.id.codigoMunicipio = :municipio_codigo " +
                "AND o.zona.id.municipio.id.provincia.id = :provincia_codigo";

        return em.createQuery(jpql, Objeto.class)
                .setParameter("zona_ubicacion", zona_ubicacion)
                .setParameter("municipio_codigo", municipio_codigo)
                .setParameter("provincia_codigo", provincia_codigo)
                .getResultList();
    }
}
