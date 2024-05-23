package com.tfg.tfgv1.servicios;

import com.tfg.tfgv1.Ids.*;
import com.tfg.tfgv1.entidades.*;
import com.tfg.tfgv1.repositorios.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class SistemaFincas
{
    @Autowired
    ProvinciaRepositorio provinciaRepositorio;

    @Autowired
    MunicipioRepositorio municipioRepositorio;

    @Autowired
    FincaRepositorio fincaRepositorio;

    @Autowired
    ZonaRepositorio zonaRepositorio;

    @Autowired
    CosechaRepositorio cosechaRepositorio;

    @Autowired
    ObjetoRepositorio objetoRepositorio;

    @Autowired
    HistoricoFincaRepositorio historicoFincaRepositorio;

    @Autowired
    HistoricoDatosRepositorio historicoDatosRepositorio;

    public SistemaFincas()
    {}

    @Cacheable("provincias")
    public Optional<Provincia> buscarProvincia(Integer codigo)
    {
        return provinciaRepositorio.buscar(codigo);
    }

    @CacheEvict(value = "provincias", key = "#codigo")
    public Provincia agregarProvincia(@NotNull @Valid Provincia provincia)
    {
        Optional<Provincia> p = provinciaRepositorio.buscar(provincia.getCodigoProvincia());
        if (p.isPresent())
            return p.get();
        else
        {
            provinciaRepositorio.guardar(provincia);
            return provincia;
        }
    }

    @Transactional
    public void actualizarProvincia(Provincia provincia)
    {
        provinciaRepositorio.actualizar(provincia);
    }

    @Cacheable("provincias")
    public void eliminarProvincia(Provincia provincia)
    {
        provinciaRepositorio.borrar(provincia);
    }

    @Cacheable("municipios")
    public Optional<Municipio> buscarMunicipio(MunicipioId codigo)
    {
        return municipioRepositorio.buscar(codigo);
    }

    @CacheEvict(value = "municipios", key = "#codigo")
    public void agregarMunicipio(Municipio municipio)
    {
        municipioRepositorio.guardar(municipio);
    }

    @Transactional
    public void actualizarMunicipio(Municipio municipio)
    {
        municipioRepositorio.actualizar(municipio);
    }

    @CacheEvict(value = "municipios", key = "#municipio.codigo")
    public void eliminarMunicipio(Municipio municipio)
    {
        municipioRepositorio.borrar(municipio);
    }

    @Cacheable("fincas")
    public Optional<Finca> buscarFinca(FincaId id)
    {
        return fincaRepositorio.buscar(id);
    }

    @CacheEvict(value = "fincas", key = "#finca.fincaId")
    public void agregarFinca(Finca finca)
    {
        fincaRepositorio.guardar(finca);
    }

    @Transactional
    public void actualizarFinca(Finca finca)
    {
        fincaRepositorio.actualizar(finca);
    }

    @CacheEvict(value = "fincas", key = "#finca.fincaId")
    public void eliminarFinca(Finca finca)
    {
        fincaRepositorio.borrar(finca);
    }

    @Cacheable("zonas")
    public Optional<Zona> buscarZona(ZonaId id)
    {
        return zonaRepositorio.buscar(id);
    }

    @CacheEvict(value = "zonas", key = "#zona.zonaId")
    public void agregarZona(Zona zona)
    {
        zonaRepositorio.guardar(zona);
    }

    @Transactional
    public void actualizarZona(Zona zona)
    {
        zonaRepositorio.actualizar(zona);
    }

    @CacheEvict(value = "zonas", key = "#zona.zonaId")
    public void eliminarZona(Zona zona)
    {
        zonaRepositorio.borrar(zona);
    }

    @Cacheable("cosechas")
    public Optional<Cosecha> buscarCosecha(Integer id)
    {
        return cosechaRepositorio.buscar(id);
    }

    @CacheEvict(value = "cosechas", key = "#zona.idCosecha")
    public void agregarCosecha(Cosecha cosecha)
    {
        cosechaRepositorio.guardar(cosecha);
    }

    @Transactional
    public void actualizarCosecha(Cosecha cosecha)
    {
        cosechaRepositorio.actualizar(cosecha);
    }

    @CacheEvict(value = "cosechas", key = "#cosecha.idCosecha")
    public void eliminarCosecha(Cosecha cosecha)
    {
        cosechaRepositorio.borrar(cosecha);
    }

    @Cacheable("objetos")
    public Optional<Objeto> buscarObjeto(Integer id)
    {
        return objetoRepositorio.buscar(id);
    }

    @CacheEvict(value = "objetos", key = "#objeto.idObjeto")
    public void agregarObjeto(Objeto objeto)
    {
        objetoRepositorio.guardar(objeto);
    }

    @Transactional
    public void actualizarObjeto(Objeto objeto)
    {
        objetoRepositorio.actualizar(objeto);
    }

    @CacheEvict(value = "objetos", key = "#objeto.idObjeto")
    public void eliminarObjeto(Objeto objeto)
    {
        objetoRepositorio.borrar(objeto);
    }

    @Cacheable("historicoFincas")
    public Optional<HistoricoFinca> buscarHistoricoFinca(HistoricoFincaId id)
    {
        return historicoFincaRepositorio.buscar(id);
    }

    @CacheEvict(value = "historicoFincas", key = "#historicoFinca.id")
    public void agregarHistoricoFinca(HistoricoFinca historicoFinca)
    {
        historicoFincaRepositorio.guardar(historicoFinca);
    }

    @Transactional
    public void actualizarHistoricoFinca(HistoricoFinca historicoFinca)
    {
        historicoFincaRepositorio.actualizar(historicoFinca);
    }

    @CacheEvict(value = "historicoFincas", key = "#historicoFinca.id")
    public void eliminarHistoricoFinca(HistoricoFinca historicoFinca)
    {
        historicoFincaRepositorio.borrar(historicoFinca);
    }

    @Cacheable("historicoDatos")
    public Optional<HistoricoDatos> buscarHistoricoDatos(HistoricoDatosId id)
    {
        return historicoDatosRepositorio.buscar(id);
    }

    @CacheEvict(value = "historicoDatos", key = "#historicoDatos.id")
    public void agregarHistoricoDatos(HistoricoDatos historicoDatos)
    {
        historicoDatosRepositorio.guardar(historicoDatos);
    }

    @Transactional
    public void actualizarHistoricoDatos(HistoricoDatos historicoDatos)
    {
        historicoDatosRepositorio.actualizar(historicoDatos);
    }

    @CacheEvict(value = "historicoDatos", key = "#historicoDatos.id")
    public void eliminarHistoricoDatos(HistoricoDatos historicoDatos)
    {
        historicoDatosRepositorio.borrar(historicoDatos);
    }

    @Transactional
    public List<Finca> obtenerFincasConHistorico()
    {
        return historicoFincaRepositorio.obtenerFincasConHistorico();
    }

    @Transactional
    public List<Finca> obtenerTodasFincas()
    {
        return fincaRepositorio.obtenerTodasFincas();
    }

    @Transactional
    public List<Zona> obtenerTodasZonas()
    {
        return zonaRepositorio.obtenerTodasZonas();
    }

    @Transactional
    public Optional<Finca> buscarFincaEsp(int provinciaCodigo, int municipioCodigo,
                                          int poligono, int parcela, int recinto)
    {
        return fincaRepositorio.buscarFinca(provinciaCodigo, municipioCodigo, poligono, parcela, recinto);
    }

    @Transactional
    public List<Cosecha> obtenerDatosCosechas(int provinciaCodigo, int municipioCodigo,
                                              int poligono, int parcela, int recinto )
    {
        Optional<Finca> finca = fincaRepositorio.buscarFinca(provinciaCodigo, municipioCodigo, poligono, parcela, recinto);
        if(finca.isEmpty())
            throw new IllegalArgumentException("Finca no encontrada");
        else
        {
            return cosechaRepositorio.obtenerCosechasPorFinca(finca.get());
        }
    }

    @Transactional
    public List<HistoricoFinca> obtenerHistoricoFincaAnioSat(Finca finca, int anio)
    {
        return historicoFincaRepositorio.obtenerHistoricoFincaAnioSat(finca, anio);
    }

    @Transactional
    public List<HistoricoFinca> obtenerHistoricoFincaAnioDron(Finca finca, int anio)
    {
        return historicoFincaRepositorio.obtenerHistoricoFincaAnioDron(finca, anio);
    }

    @Transactional
    public List<Objeto> obtenerObjetosZona(Zona zona)
    {
        return objetoRepositorio.obtenerObjetosFinca(zona.getUbicacion(), zona.getMunicipioCodigo(), zona.getProvinciaCodigo());
    }
}
