package com.tfg.tfgv1.servicios;

import com.tfg.tfgv1.entidades.Provincia;
import com.tfg.tfgv1.repositorios.ProvinciaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class SistemaFincas
{
    @Autowired
    ProvinciaRepositorio provinciaRepositorio;

    public SistemaFincas()
    {}

    @Cacheable("provincias")
    public Optional<Provincia> buscarProvincia(Integer codigo)
    {
        return provinciaRepositorio.buscar(codigo);
    }

    @CacheEvict(value = "provincias", key = "#codigo")
    public void agregarProvincia(Provincia provincia)
    {
        provinciaRepositorio.guardar(provincia);
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
}
