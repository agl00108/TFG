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
    public Optional<Cosecha> buscar(Integer codigo) {
        return Optional.ofNullable(em.find(Cosecha.class, codigo));
    }

    public void guardar(Cosecha cosecha) {
        em.persist(cosecha);
    }

    public void actualizar(Cosecha cosecha) {
        em.merge(cosecha);
    }

    public void borrar(Cosecha cosecha) {
        em.remove(em.merge(cosecha));
    }

    /**
     * @param finca
     * @return lista de cosechas
     * @brief función para obtener todos los datos de las cosechas de una finca
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Cosecha> obtenerCosechasPorFinca(Finca finca)
    {
        String jpql= "SELECT c FROM Cosecha c WHERE c.finca.id.zona.id.municipio.id.codigoMunicipio = :municipioCodigo " +
                "AND c.finca.id.zona.id.municipio.id.provincia.codigoProvincia = :provinciaCodigo" +
                " AND c.finca.id.poligono = :fincaPoligono AND c.finca.id.parcela = :fincaParcela AND c.finca.id.recinto = :fincaRecinto";
        TypedQuery<Cosecha> query = em.createQuery(jpql, Cosecha.class)
                .setParameter("municipioCodigo", finca.getMunicipioCodigo())
                .setParameter("provinciaCodigo", finca.getCodigoProvincia())
                .setParameter("fincaPoligono", finca.getPoligono())
                .setParameter("fincaParcela", finca.getParcela())
                .setParameter("fincaRecinto", finca.getRecinto());
        return query.getResultList();
    }
}
