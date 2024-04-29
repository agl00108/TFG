package com.tfg.tfgv1.rest;

import com.tfg.tfgv1.Ids.FincaId;
import com.tfg.tfgv1.Ids.MunicipioId;
import com.tfg.tfgv1.Ids.ZonaId;
import com.tfg.tfgv1.entidades.*;
import com.tfg.tfgv1.excepciones.ProvinciaYaRegistrada;
import com.tfg.tfgv1.rest.dto.*;
import com.tfg.tfgv1.servicios.SistemaFincas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/TFG")
public class ControladorREST
{
    @Autowired
    SistemaFincas sistemaFincas;

    /**
     * Handler para excepciones de violación de restricciones
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handlerViolacionRestricciones(ConstraintViolationException e)
    {
         return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * Creación de provincias
     */
    @PostMapping("/provincias")
    ResponseEntity<DTOProvincia> altaProvincia(@RequestBody DTOProvincia provincia)
    {
        try {
            Provincia p = sistemaFincas.agregarProvincia(provincia.aProvincia());
            return ResponseEntity.status(HttpStatus.CREATED).body(new DTOProvincia(p));
        } catch (ProvinciaYaRegistrada e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Consultar provincia en concreto
     */
    @GetMapping("/provincias/{id}")
    ResponseEntity<DTOProvincia> verProvincia(@PathVariable Integer id)
    {
        Optional<Provincia> p = sistemaFincas.buscarProvincia(id);
        return p
                .map(u -> ResponseEntity.ok(new DTOProvincia(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Consultar municipio en concreto
     */
    @GetMapping("/provincias/{idP}/municipios/{idM}")
    ResponseEntity<DTOMunicipio> verMunicipio(@PathVariable Integer idP, @PathVariable Integer idM)
    {
        Optional<Provincia> p = sistemaFincas.buscarProvincia(idP);
        if(p.isPresent())
        {
            Optional<Municipio> m = sistemaFincas.buscarMunicipio(new MunicipioId(idM, p.get()));
            return m
                    .map(u -> ResponseEntity.ok(new DTOMunicipio(u)))
                    .orElse(ResponseEntity.notFound().build());
        }
        else
            return ResponseEntity.notFound().build();
    }

    /**
     * Consulta de Zona
     * @param idP id de la provincia
     * @param idM id del municipio
     * @param ubZ nombre de la zona
     */
    @GetMapping("/provincias/{idP}/municipios/{idM}/zonas/{ubZ}")
    ResponseEntity<DTOZona> verZona(@PathVariable Integer idP, @PathVariable Integer idM, @PathVariable String ubZ)
    {
        Optional<Provincia> p = sistemaFincas.buscarProvincia(idP);
        if(p.isPresent())
        {
            Optional<Municipio> m = sistemaFincas.buscarMunicipio(new MunicipioId(idM, p.get()));
            if(m.isPresent())
            {
                Optional<Zona> z = sistemaFincas.buscarZona(new ZonaId(ubZ, m.get()));
                return z
                        .map(u -> ResponseEntity.ok(new DTOZona(u)))
                        .orElse(ResponseEntity.notFound().build());
            }
            else
                return ResponseEntity.notFound().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

    /**
     * Consulta de Finca
     * @param idP id de la provincia
     * @param idM id del municipio
     * @param ubZ nombre de la zona
     * @param poligono poligono de la finca
     * @param parcela parcela de la finca
     * @param recinto recinto de la finca
     */
    @GetMapping("/provincias/{idP}/municipios/{idM}/zonas/{ubZ}/fincas/{poligono}/{parcela}/{recinto}")
    ResponseEntity<DTOFinca> verFinca(@PathVariable Integer idP, @PathVariable Integer idM, @PathVariable String ubZ,
                                      @PathVariable Integer poligono, @PathVariable Integer parcela, @PathVariable Integer recinto)
    {
        Optional<Provincia> p = sistemaFincas.buscarProvincia(idP);
        if(p.isPresent())
        {
            Optional<Municipio> m = sistemaFincas.buscarMunicipio(new MunicipioId(idM, p.get()));
            if(m.isPresent())
            {
                Optional<Zona> z = sistemaFincas.buscarZona(new ZonaId(ubZ, m.get()));
                if(z.isPresent())
                {
                    Optional<Finca> f = sistemaFincas.buscarFinca(new FincaId(poligono, parcela, recinto, z.get()));
                    return f
                            .map(u -> ResponseEntity.ok(new DTOFinca(u)))
                            .orElse(ResponseEntity.notFound().build());
                }
                else
                    return ResponseEntity.notFound().build();
            }
            else
                return ResponseEntity.notFound().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

    /**
     * @brief Función para obtener las fincas con histórico
     * @return Lista de Fincas
     */
    @GetMapping("/FincasConHistorico")
    @ResponseStatus(HttpStatus.OK)
    public List<DTOFinca> obtenerFincasConHistorico()
    {
        return sistemaFincas.obtenerFincasConHistorico()
                .stream().map(DTOFinca::new).toList();
    }

    /**
     * @brief Función para obtener todas las fincas
     * @return Lista de Fincas
     */
    @GetMapping("/FincasCompletas")
    @ResponseStatus(HttpStatus.OK)
    public List<DTOFinca> obtenerTodasFincas()
    {
        return sistemaFincas.obtenerTodasFincas()
                .stream().map(DTOFinca::new).toList();
    }

    /**
     * @brief Función para obtener todas las zonas
     * @return Lista de Zonas
     */
    @GetMapping("/ZonasCompletas")
    @ResponseStatus(HttpStatus.OK)
    public List<DTOZona> obtenerTodasZonas()
    {
        return sistemaFincas.obtenerTodasZonas()
                .stream().map(DTOZona::new).toList();
    }

    @GetMapping("/provincia/{provinciaCodigo}/municipio/{municipioCodigo}/finca/{poligono}/{parcela}/{recinto}")
    public ResponseEntity<Finca> buscarFincaEsp(
            @PathVariable int provinciaCodigo,
            @PathVariable int municipioCodigo,
            @PathVariable int poligono,
            @PathVariable int parcela,
            @PathVariable int recinto)
    {
        Optional<Finca> fincaOptional = sistemaFincas.buscarFincaEsp(provinciaCodigo, municipioCodigo, poligono, parcela, recinto);
        return fincaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/provincia/{provinciaCodigo}/municipio/{municipioCodigo}/finca/{poligono}/{parcela}/{recinto}/cosechas")
    public List<DTOCosecha> buscarCosechasFinca(
            @PathVariable int provinciaCodigo,
            @PathVariable int municipioCodigo,
            @PathVariable int poligono,
            @PathVariable int parcela,
            @PathVariable int recinto)
    {
        List<Cosecha> cosechas = sistemaFincas.obtenerDatosCosechas(provinciaCodigo, municipioCodigo, poligono, parcela, recinto);
        Stream<Cosecha> streamCosechas = cosechas.stream();
        Stream<DTOCosecha> streamDTOCosechas = streamCosechas.map(DTOCosecha::new);
        return streamDTOCosechas.toList();
    }

    @GetMapping("/provincia/{provinciaCodigo}/municipio/{municipioCodigo}/finca/{poligono}/{parcela}/{recinto}/historico/{anio}")
    public List<DTOHistoricoFinca> buscarHistoricoAnio(
            @PathVariable int provinciaCodigo, @PathVariable int municipioCodigo,
            @PathVariable int poligono, @PathVariable int parcela,
            @PathVariable int recinto, @PathVariable int anio)
    {
        Optional<Finca> fincaOptional = sistemaFincas.buscarFincaEsp(provinciaCodigo, municipioCodigo, poligono, parcela, recinto);
        if(fincaOptional.isPresent())
        {
            List<HistoricoFinca> historico = sistemaFincas.obtenerHistoricoFincaAnio(fincaOptional.get(), anio);
            Stream<HistoricoFinca> stream = historico.stream();
            Stream<DTOHistoricoFinca> streamDTO = stream.map(DTOHistoricoFinca::new);
            return streamDTO.toList();
        }
        return List.of();
    }

}
