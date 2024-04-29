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

    /**
     * @brief Obtener los años de los que se tienen datos históricos de una finca
     * @param finca Finca de la que se quieren obtener los historicos
     * @param anio Año del que se quieren obtener los datos
     * @return Lista de HistoricoFinca
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<HistoricoFinca> obtenerHistoricoFincaAnio(Finca finca, int anio)
    {
        String jpql = "SELECT hf FROM HistoricoFinca hf WHERE hf.id.finca.id.parcela = :fincaParcela AND " +
                "hf.id.finca.id.poligono = :fincaPoligono AND hf.id.finca.id.recinto = :fincaRecinto AND " +
                "hf.id.finca.id.zona.id.municipio.id.codigoMunicipio = :municipioCodigo AND " +
                "hf.id.finca.id.zona.id.municipio.id.provincia.codigoProvincia = :provinciaCodigo AND " +
                "EXTRACT(YEAR FROM hf.id.fecha) = :anio";
        TypedQuery<HistoricoFinca> query = em.createQuery(jpql, HistoricoFinca.class)
                .setParameter("municipioCodigo", finca.getMunicipioCodigo())
                .setParameter("provinciaCodigo", finca.getCodigoProvincia())
                .setParameter("fincaPoligono", finca.getPoligono())
                .setParameter("fincaParcela", finca.getParcela())
                .setParameter("fincaRecinto", finca.getRecinto())
                .setParameter("anio", anio);
        return query.getResultList();
    }
}
