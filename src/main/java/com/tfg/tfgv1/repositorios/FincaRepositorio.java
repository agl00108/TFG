package com.tfg.tfgv1.repositorios;

import com.tfg.tfgv1.Ids.FincaId;
import com.tfg.tfgv1.entidades.Finca;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class FincaRepositorio
{
    @PersistenceContext
    EntityManager em;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Finca> buscar(FincaId codigo)
    {
        return Optional.ofNullable(em.find(Finca.class, codigo));
    }


    public void guardar(Finca finca)
    {
        em.persist(finca);
    }

    public void actualizar(Finca municipio)
    {
        em.merge(municipio);
    }

    public void borrar(Finca municipio)
    {
        em.remove(em.merge(municipio));
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Finca> buscarFinca(int provinciaCodigo, int municipioCodigo, int poligono, int parcela, int recinto)
    {
        String jpql = "SELECT f FROM Finca f WHERE f.id.zona.id.municipio.id.codigoMunicipio = :municipioCodigo " +
                "AND f.id.zona.id.municipio.id.provincia.codigoProvincia = :provinciaCodigo " +
                "AND f.id.poligono = :poligono " +
                "AND f.id.parcela = :parcela AND f.id.recinto = :recinto";

        return em.createQuery(jpql, Finca.class)
                .setParameter("provinciaCodigo", provinciaCodigo)
                .setParameter("municipioCodigo", municipioCodigo)
                .setParameter("poligono", poligono)
                .setParameter("parcela", parcela)
                .setParameter("recinto", recinto)
                .getResultList()
                .stream()
                .findFirst();
    }
}
