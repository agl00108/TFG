package com.tfg.tfgv1.servicios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tfg.tfgv1.Ids.*;
import com.tfg.tfgv1.TfgV1Application;
import com.tfg.tfgv1.entidades.*;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TfgV1Application.class)
@Transactional
public class SistemaFincasTest
{
    @Autowired
    SistemaFincas sistemaFincas;

    @Test
    public void accesoSistema()
    {
        Assertions.assertThat(sistemaFincas).isNotNull();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarProvincia()
    {
        Integer codigoProvincia = 23;
        Provincia provincia = new Provincia(23,"Jaén");
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(codigoProvincia);
        Assertions.assertThat(optional.isPresent()).isTrue();
        Assertions.assertThat(optional.get().getCodigoProvincia()).isEqualTo(provincia.getCodigoProvincia());
        Assertions.assertThat(optional.get().getNombreProvincia()).isEqualTo(provincia.getNombreProvincia());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testInsertarProvincia()
    {
        Provincia provincia = new Provincia(18,"Granada");
        sistemaFincas.agregarProvincia(provincia);
        Integer codigoProvincia=18;
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(codigoProvincia);
        Assertions.assertThat(optional.isPresent()).isTrue();
        Assertions.assertThat(optional.get().getCodigoProvincia()).isEqualTo(provincia.getCodigoProvincia());
        Assertions.assertThat(optional.get().getNombreProvincia()).isEqualTo(provincia.getNombreProvincia());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testModificarProvincia()
    {
        Provincia provincia = new Provincia(21,"Cádiz");
        sistemaFincas.agregarProvincia(provincia);
        String nombreProvincia="Huelva";
        provincia.setNombreProvincia(nombreProvincia);
        sistemaFincas.actualizarProvincia(provincia);
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(21);
        Assertions.assertThat(optional.isPresent()).isTrue();
        Assertions.assertThat(optional.get().getCodigoProvincia()).isEqualTo(provincia.getCodigoProvincia());
        Assertions.assertThat(optional.get().getNombreProvincia()).isEqualTo("Huelva");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testEliminarProvincia()
    {
        Provincia provincia = new Provincia(18,"Granada");
        sistemaFincas.agregarProvincia(provincia);
        sistemaFincas.eliminarProvincia(provincia);
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(21);
        Assertions.assertThat(optional.isPresent()).isFalse();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarMunicipio()
    {
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        Assertions.assertThat(optional.isPresent()).isTrue();
        MunicipioId municipioId = new MunicipioId(87, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        Assertions.assertThat(optionalMunicipio.isPresent()).isTrue();
        Assertions.assertThat(optionalMunicipio.get().getCodigoMunicipio()).isEqualTo(87);
        Assertions.assertThat(optionalMunicipio.get().getNombre()).isEqualTo("Torredonjimeno");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testInsertarMunicipio()
    {
        Optional<Provincia> optionalProvincia=sistemaFincas.buscarProvincia(23);
        Assertions.assertThat(optionalProvincia.isPresent()).isTrue();
        MunicipioId municipioId = new MunicipioId(51, optionalProvincia.get());
        Municipio municipio = new Municipio(municipioId, "Jamilena");
        sistemaFincas.agregarMunicipio(municipio);
        Optional<Municipio> optional = sistemaFincas.buscarMunicipio(municipioId);
        Assertions.assertThat(optional.isPresent()).isTrue();
        Assertions.assertThat(optional.get().getNombre()).isEqualTo("Jamilena");
        Assertions.assertThat(optional.get().getCodigoMunicipio()).isEqualTo(51);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testModificarMunicipio()
    {
        Optional<Provincia> optionalProvincia=sistemaFincas.buscarProvincia(23);
        Assertions.assertThat(optionalProvincia.isPresent()).isTrue();
        MunicipioId municipioId = new MunicipioId(39, optionalProvincia.get());
        Municipio municipio = new Municipio(municipioId, "Torres");
        sistemaFincas.agregarMunicipio(municipio);
        String nombreMunicipio = "Guarromán";
        municipio.setNombre("Guarromán");
        sistemaFincas.actualizarMunicipio(municipio);
        Optional<Municipio> optional = sistemaFincas.buscarMunicipio(municipioId);
        Assertions.assertThat(optional.isPresent()).isTrue();
        Assertions.assertThat(optional.get().getCodigoMunicipio()).isEqualTo(municipioId.getCodigoMunicipio());
        Assertions.assertThat(optional.get().getNombre()).isEqualTo("Guarromán");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testEliminarMunicipio()
    {
        Optional<Provincia> optionalProvincia=sistemaFincas.buscarProvincia(23);
        Assertions.assertThat(optionalProvincia.isPresent()).isTrue();
        MunicipioId municipioId = new MunicipioId(51, optionalProvincia.get());
        Municipio municipio = new Municipio(municipioId, "Jamilena");
        sistemaFincas.agregarMunicipio(municipio);
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        Assertions.assertThat(optionalMunicipio.isPresent()).isTrue();
        sistemaFincas.eliminarMunicipio(optionalMunicipio.get());
        Optional<Municipio> optional = sistemaFincas.buscarMunicipio(municipioId);
        Assertions.assertThat(optional.isPresent()).isFalse();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarZona()
    {
        //Primero buscamos el municipio donde se ubica
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        Assertions.assertThat(optional.isPresent()).isTrue();
        MunicipioId municipioId = new MunicipioId(87, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);

        ZonaId zonaId = new ZonaId("T10", optionalMunicipio.get());
        Zona zona = new Zona("T10", -3.9714766f,37.777341f,"CAMINO ANCHO", optionalMunicipio.get(), null);
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        Assertions.assertThat(optionalZona.isPresent()).isTrue();
        Assertions.assertThat(optionalZona.get().getUbicacion()).isEqualTo(zona.getUbicacion());
        Assertions.assertThat(optionalZona.get().getMunicipioCodigo()).isEqualTo(zona.getMunicipioCodigo());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testInsertarZona()
    {
        //Primero buscamos el municipio donde se ubica
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        Assertions.assertThat(optional.isPresent()).isTrue();
        MunicipioId municipioId = new MunicipioId(87, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);

        ZonaId zonaId = new ZonaId("T31", optionalMunicipio.get());
        Zona zona = new Zona("T31", -3.9714766f,37.777341f,"PRUEBA INSERCION", optionalMunicipio.get(), null);
        sistemaFincas.agregarZona(zona);
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        Assertions.assertThat(optionalZona.isPresent()).isTrue();
        Assertions.assertThat(optionalZona.get().getUbicacion()).isEqualTo(zona.getUbicacion());
        Assertions.assertThat(optionalZona.get().getMunicipioCodigo()).isEqualTo(zona.getMunicipioCodigo());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testModificarZona()
    {
        //Primero buscamos el municipio donde se ubica
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        Assertions.assertThat(optional.isPresent()).isTrue();
        MunicipioId municipioId = new MunicipioId(87, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);

        ZonaId zonaId = new ZonaId("T31", optionalMunicipio.get());
        Zona zona = new Zona("T31", -3.9714766f,37.777341f,"PRUEBA INSERCION", optionalMunicipio.get(), null);
        sistemaFincas.agregarZona(zona);
        zona.setDescripcion("INSERCION CONFIRMADA");
        sistemaFincas.actualizarZona(zona);
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        Assertions.assertThat(optionalZona.isPresent()).isTrue();
        Assertions.assertThat(optionalZona.get().getDescripcion()).isEqualTo(zona.getDescripcion());
        Assertions.assertThat(optionalZona.get().getUbicacion()).isEqualTo(zona.getUbicacion());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testEliminarZona()
    {
        //Primero buscamos el municipio donde se ubica
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        Assertions.assertThat(optional.isPresent()).isTrue();
        MunicipioId municipioId = new MunicipioId(87, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);

        ZonaId zonaId = new ZonaId("T31", optionalMunicipio.get());
        Zona zona = new Zona("T31", -3.9714766f,37.777341f,"PRUEBA INSERCION", optionalMunicipio.get(), null);
        sistemaFincas.agregarZona(zona);
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        sistemaFincas.eliminarZona(optionalZona.get());
        Optional<Zona> optionalZona1 = sistemaFincas.buscarZona(zonaId);
        Assertions.assertThat(optionalZona1.isPresent()).isFalse();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarFinca()
    {
        //Primero buscamos la zona donde se ubica
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        Assertions.assertThat(optional.isPresent()).isTrue();
        MunicipioId municipioId = new MunicipioId(41, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("H1", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);

        FincaId fincaId = new FincaId(2,8,1,optionalZona.get());
        Optional<Finca> optionalFinca = sistemaFincas.buscarFinca(fincaId);
        Assertions.assertThat(optionalFinca.isPresent()).isTrue();
        Assertions.assertThat(optionalFinca.get().getZonaUbicacion()).isEqualTo(zonaId.getUbicacion());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testInsertarFinca()
    {   //Primero buscamos la zona
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        Assertions.assertThat(optional.isPresent()).isTrue();
        MunicipioId municipioId = new MunicipioId(87, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        Zona zona = new Zona("T31", -3.9714766f,37.777341f,"PRUEBA INSERCION", optionalMunicipio.get(), null);
        sistemaFincas.agregarZona(zona);

        FincaId fincaId = new FincaId(2,9,1,zona);
        Finca finca = new Finca(0.9639,2,9,1,2024,zona);
        sistemaFincas.agregarFinca(finca);
        Optional<Finca> optionalFinca = sistemaFincas.buscarFinca(fincaId);
        Assertions.assertThat(optionalFinca.isPresent()).isTrue();
        Assertions.assertThat(optionalFinca.get()).isEqualTo(finca);
        Assertions.assertThat(optionalFinca.get()).isEqualTo(finca);
        Assertions.assertThat(optionalFinca.get()).isEqualTo(finca);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testModificarFinca()
    {
        //Primero buscamos la zona
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        Assertions.assertThat(optional.isPresent()).isTrue();
        MunicipioId municipioId = new MunicipioId(87, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        Zona zona = new Zona("T31", -3.9714766f,37.777341f,"PRUEBA INSERCION", optionalMunicipio.get(), null);
        sistemaFincas.agregarZona(zona);

        FincaId fincaId = new FincaId(2,9,1,zona);
        Finca finca = new Finca(0.9639,2,9,1,2024,zona);
        sistemaFincas.agregarFinca(finca);
        finca.setArea(1.150);
        sistemaFincas.actualizarFinca(finca);
        Optional<Finca> optionalFinca = sistemaFincas.buscarFinca(fincaId);
        Assertions.assertThat(optionalFinca.isPresent()).isTrue();
        Assertions.assertThat(optionalFinca.get().getArea()).isEqualTo(finca.getArea());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testEliminarFinca()
    {
       //Primero buscamos la zona
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        Assertions.assertThat(optional.isPresent()).isTrue();
        MunicipioId municipioId = new MunicipioId(87, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        Zona zona = new Zona("T31", -3.9714766f,37.777341f,"PRUEBA INSERCION", optionalMunicipio.get(), null);
        sistemaFincas.agregarZona(zona);

        FincaId fincaId = new FincaId(2,9,1,zona);
        Finca finca = new Finca(0.9639,2,9,1,2024,zona);
        sistemaFincas.agregarFinca(finca);
        sistemaFincas.eliminarFinca(finca);
        Optional<Finca> optionalFinca = sistemaFincas.buscarFinca(fincaId);
        Assertions.assertThat(optionalFinca.isPresent()).isFalse();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarCosecha()
    {
        //Buscamos la finca donde se ubica
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        MunicipioId municipioId = new MunicipioId(41, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("H1", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        FincaId fincaId = new FincaId(2,8,1,optionalZona.get());
        Optional<Finca> optionalFinca = sistemaFincas.buscarFinca(fincaId);
        Finca fincaElegida=optionalFinca.get();

        //Ahora buscamos la cosecha
        Integer idCosecha = 23;
        Cosecha cosecha = new Cosecha(idCosecha, "2023", 8859.127483949862, 1506.051672271476, fincaElegida );
        Optional<Cosecha> optionalC = sistemaFincas.buscarCosecha(idCosecha);
        Assertions.assertThat(optionalC.isPresent()).isTrue();
        Assertions.assertThat(optionalC.get().getIdCosecha()).isEqualTo(cosecha.getIdCosecha());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testInsertarCosecha()
    {
        //Buscamos la finca donde se ubica
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        MunicipioId municipioId = new MunicipioId(41, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("H1", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        FincaId fincaId = new FincaId(2,8,1,optionalZona.get());
        Optional<Finca> optionalFinca = sistemaFincas.buscarFinca(fincaId);
        Finca fincaElegida=optionalFinca.get();

        //Ahora insertamos la cosecha
        Integer idCosecha = 1237;
        Cosecha cosecha = new Cosecha(idCosecha, "2024", 8759.127483949862, 1507.051672271476, fincaElegida );
        sistemaFincas.agregarCosecha(cosecha);
        Optional<Cosecha> optionalC = sistemaFincas.buscarCosecha(idCosecha);
        Assertions.assertThat(optionalC.isPresent()).isTrue();
        Assertions.assertThat(optionalC.get().getIdCosecha()).isEqualTo(cosecha.getIdCosecha());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testModificarCosecha()
    {
        //Buscamos la finca donde se ubica
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        MunicipioId municipioId = new MunicipioId(41, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("H1", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        FincaId fincaId = new FincaId(2,8,1,optionalZona.get());
        Optional<Finca> optionalFinca = sistemaFincas.buscarFinca(fincaId);
        Finca fincaElegida=optionalFinca.get();

        //Ahora insertamos y modificamos la cosecha
        Integer idCosecha = 1237;
        Cosecha cosecha = new Cosecha(idCosecha, "2024", 8759.127483949862, 1507.051672271476, fincaElegida );
        sistemaFincas.agregarCosecha(cosecha);
        cosecha.setCampania("2025");
        sistemaFincas.actualizarCosecha(cosecha);
        Optional<Cosecha> optionalC = sistemaFincas.buscarCosecha(idCosecha);
        Assertions.assertThat(optionalC.isPresent()).isTrue();
        Assertions.assertThat(optionalC.get().getCampania()).isEqualTo(cosecha.getCampania());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testEliminarCosecha()
    {
        //Buscamos la finca donde se ubica
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        MunicipioId municipioId = new MunicipioId(41, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("H1", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        FincaId fincaId = new FincaId(2,8,1,optionalZona.get());
        Optional<Finca> optionalFinca = sistemaFincas.buscarFinca(fincaId);
        Finca fincaElegida=optionalFinca.get();

        //Ahora insertamos y eliminamos la cosecha
        Integer idCosecha = 1237;
        Cosecha cosecha = new Cosecha(idCosecha, "2024", 8759.127483949862, 1507.051672271476, fincaElegida );
        sistemaFincas.agregarCosecha(cosecha);
        sistemaFincas.eliminarCosecha(cosecha);
        Optional<Cosecha> optionalC = sistemaFincas.buscarCosecha(idCosecha);
        Assertions.assertThat(optionalC.isPresent()).isFalse();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarObjeto()
    {
        //Primero buscamos la zona
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        MunicipioId municipioId = new MunicipioId(5, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("J2", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        Assertions.assertThat(optionalZona.isPresent()).isTrue();

        Integer idObjeto = 2;
        Optional<Objeto> olivo = sistemaFincas.buscarObjeto(idObjeto);
        Assertions.assertThat(olivo.isPresent()).isTrue();
        Assertions.assertThat(olivo.get().getIdObjeto()).isEqualTo(2);
        Assertions.assertThat(olivo.get().getZonaUbicacion()).isEqualTo("J2");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testInsertarObjeto()
    {
        //Primero buscamos la zona
        Optional<Provincia> optional = sistemaFincas.buscarProvincia(23);
        MunicipioId municipioId = new MunicipioId(5, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("J2", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);

        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] polygonCoordinates = new Coordinate[] {
                new Coordinate(410329.59375, 4207587.5),
                new Coordinate(410329.59375, 4207587.5),
                new Coordinate(410329.59375, 4207589),
                new Coordinate(410329.59375, 4207589),
                new Coordinate(410329.59375, 4207587.5)
        };
        Polygon polygon = geometryFactory.createPolygon(polygonCoordinates);

        Coordinate pointCoordinate = new Coordinate(410330.875, 4207588.25);
        Point point = geometryFactory.createPoint(pointCoordinate);

        Objeto objeto = new Objeto("Olivo", optionalZona.get(), polygon, point);
        sistemaFincas.agregarObjeto(objeto);
        Optional<Objeto> olivo = sistemaFincas.buscarObjeto(objeto.getIdObjeto());
        Assertions.assertThat(olivo.isPresent()).isTrue();
        Assertions.assertThat(olivo.get().getIdObjeto()).isEqualTo(objeto.getIdObjeto());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testModificarObjeto()
    {
        //Primero buscamos la zona
        Optional<Provincia> optional = sistemaFincas.buscarProvincia(23);
        MunicipioId municipioId = new MunicipioId(5, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("J2", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);

        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] polygonCoordinates = new Coordinate[] {
                new Coordinate(410329.59375, 4207587.5),
                new Coordinate(410329.59375, 4207587.5),
                new Coordinate(410329.59375, 4207589),
                new Coordinate(410329.59375, 4207589),
                new Coordinate(410329.59375, 4207587.5)
        };
        Polygon polygon = geometryFactory.createPolygon(polygonCoordinates);

        Coordinate pointCoordinate = new Coordinate(410330.875, 4207588.25);
        Point point = geometryFactory.createPoint(pointCoordinate);

        Objeto objeto = new Objeto("Arbusto", optionalZona.get(), polygon, point);
        sistemaFincas.agregarObjeto(objeto);
        objeto.setTipoObjeto("Olivo");
        sistemaFincas.actualizarObjeto(objeto);
        Optional<Objeto> olivo = sistemaFincas.buscarObjeto(objeto.getIdObjeto());
        Assertions.assertThat(olivo.isPresent()).isTrue();
        Assertions.assertThat(olivo.get().getTipoObjeto()).isEqualTo("Olivo");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testEliminarObjeto()
    {
        //Primero buscamos la zona
        Optional<Provincia> optional = sistemaFincas.buscarProvincia(23);
        MunicipioId municipioId = new MunicipioId(5, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("J2", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);

        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] polygonCoordinates = new Coordinate[] {
                new Coordinate(410329.59375, 4207587.5),
                new Coordinate(410329.59375, 4207587.5),
                new Coordinate(410329.59375, 4207589),
                new Coordinate(410329.59375, 4207589),
                new Coordinate(410329.59375, 4207587.5)
        };
        Polygon polygon = geometryFactory.createPolygon(polygonCoordinates);

        Coordinate pointCoordinate = new Coordinate(410330.875, 4207588.25);
        Point point = geometryFactory.createPoint(pointCoordinate);

        Objeto objeto = new Objeto("Arbusto", optionalZona.get(), polygon, point);
        sistemaFincas.agregarObjeto(objeto);
        sistemaFincas.eliminarObjeto(objeto);
        Optional<Objeto> existe = sistemaFincas.buscarObjeto(objeto.getIdObjeto());
        Assertions.assertThat(existe.isPresent()).isFalse();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarHistoricoFinca()
    {
        //Primero buscamos la finca de donde son los datos
        //Buscamos la finca donde se ubica
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        MunicipioId municipioId = new MunicipioId(87, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("T20", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        FincaId fincaId = new FincaId(43,1,1,optionalZona.get());
        Optional<Finca> optionalFinca = sistemaFincas.buscarFinca(fincaId);

        //Ahora buscamos el historico de la finca
        HistoricoFincaId historicoFincaId = new HistoricoFincaId(LocalDate.of(2018, 4, 1), optionalFinca.get());
        Optional<HistoricoFinca> optionalH = sistemaFincas.buscarHistoricoFinca(historicoFincaId);
        Assertions.assertThat(optionalH.isPresent()).isTrue();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testAgregarHistoricoFinca() throws JsonProcessingException
    {
        //Primero buscamos la finca de donde son los datos
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        MunicipioId municipioId = new MunicipioId(87, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("H1", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        FincaId fincaId = new FincaId(2,8,1,optionalZona.get());
        Optional<Finca> optionalFinca = sistemaFincas.buscarFinca(fincaId);

        //Ahora buscamos el historico de la finca
        HistoricoFincaId historicoFincaId = new HistoricoFincaId(LocalDate.now(), optionalFinca.get());
        HistoricoFinca historicoFinca = new HistoricoFinca(LocalDate.now(), optionalFinca.get(), "", "", 1.23, "prueba", "satélite");
        sistemaFincas.agregarHistoricoFinca(historicoFinca);
        Optional<HistoricoFinca> optionalH = sistemaFincas.buscarHistoricoFinca(historicoFincaId);
        Assertions.assertThat(optionalH.isPresent()).isTrue();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testModificarHistoricoFinca() throws JsonProcessingException
    {
        //Primero buscamos la finca de donde son los datos
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        MunicipioId municipioId = new MunicipioId(87, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("H1", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        FincaId fincaId = new FincaId(2,8,1,optionalZona.get());
        Optional<Finca> optionalFinca = sistemaFincas.buscarFinca(fincaId);

        //Ahora buscamos el historico de la finca
        HistoricoFincaId historicoFincaId = new HistoricoFincaId(LocalDate.now(), optionalFinca.get());
        HistoricoFinca historicoFinca = new HistoricoFinca(LocalDate.now(), optionalFinca.get(), "", "", 1.23, "prueba", "satélite");
        sistemaFincas.agregarHistoricoFinca(historicoFinca);
        historicoFinca.setLluvia(2.23);
        sistemaFincas.actualizarHistoricoFinca(historicoFinca);
        Optional<HistoricoFinca> optionalH = sistemaFincas.buscarHistoricoFinca(historicoFincaId);
        Assertions.assertThat(optionalH.isPresent()).isTrue();
        Assertions.assertThat(optionalH.get().getLluvia()).isEqualTo(2.23);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testEliminarHistoricoFinca() throws JsonProcessingException
    {
        //Primero buscamos la finca de donde son los datos
        Optional<Provincia> optional=sistemaFincas.buscarProvincia(23);
        MunicipioId municipioId = new MunicipioId(87, optional.get());
        Optional<Municipio> optionalMunicipio = sistemaFincas.buscarMunicipio(municipioId);
        ZonaId zonaId = new ZonaId("H1", optionalMunicipio.get());
        Optional<Zona> optionalZona = sistemaFincas.buscarZona(zonaId);
        FincaId fincaId = new FincaId(2,8,1,optionalZona.get());
        Optional<Finca> optionalFinca = sistemaFincas.buscarFinca(fincaId);

        //Ahora buscamos el historico de la finca
        HistoricoFincaId historicoFincaId = new HistoricoFincaId(LocalDate.now(), optionalFinca.get());
        HistoricoFinca historicoFinca = new HistoricoFinca(LocalDate.now(), optionalFinca.get(), "", "", 1.23, "prueba", "satélite");
        sistemaFincas.agregarHistoricoFinca(historicoFinca);
        sistemaFincas.eliminarHistoricoFinca(historicoFinca);
        Optional<HistoricoFinca> optionalH = sistemaFincas.buscarHistoricoFinca(historicoFincaId);
        Assertions.assertThat(optionalH.isPresent()).isFalse();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarHistoricoDatos()
    {
        Integer idObjeto = 34;
        Optional<Objeto> olivo = sistemaFincas.buscarObjeto(idObjeto);

        //Ahora buscamos el historico del olivo
        HistoricoDatosId historicoDatosId = new HistoricoDatosId(LocalDate.of(2018, 4, 1), olivo.get());
        Optional<HistoricoDatos> optionalH = sistemaFincas.buscarHistoricoDatos(historicoDatosId);
        Assertions.assertThat(optionalH.isPresent()).isTrue();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testAgregarHistoricoDatos()
    {
        Integer idObjeto = 34;
        Optional<Objeto> olivo = sistemaFincas.buscarObjeto(idObjeto);

        //Ahora buscamos el historico del olivo
        HistoricoDatosId historicoDatosId = new HistoricoDatosId(LocalDate.now(), olivo.get());
        String reflectancia="{\"data\":[{\"ID\":\"34\"},{\"Año\":\"2018\"},{\"Mes\":\"Febrero\"},{\"NDMI\":\"-0,03854723647236824\"},{\"NDVI\":\"0,1690598726272583\"},{\"SAVI\":\"0,2535554766654968\"}]}";
        HistoricoDatos historicoDatos=new HistoricoDatos(LocalDate.now(),null,reflectancia,olivo.get(),"defecto", "satelite");
        sistemaFincas.agregarHistoricoDatos(historicoDatos);
        Optional<HistoricoDatos> optionalH = sistemaFincas.buscarHistoricoDatos(historicoDatosId);
        Assertions.assertThat(optionalH.isPresent()).isTrue();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testModificarHistoricoDatos()
    {
        Integer idObjeto = 34;
        Optional<Objeto> olivo = sistemaFincas.buscarObjeto(idObjeto);

        //Ahora buscamos el historico del olivo
        HistoricoDatosId historicoDatosId = new HistoricoDatosId(LocalDate.now(), olivo.get());
        String reflectancia="{\"data\":[{\"ID\":\"34\"},{\"Año\":\"2018\"},{\"Mes\":\"Febrero\"},{\"NDMI\":\"-0,03854723647236824\"},{\"NDVI\":\"0,1690598726272583\"},{\"SAVI\":\"0,2535554766654968\"}]}";
        HistoricoDatos historicoDatos=new HistoricoDatos(LocalDate.now(),null,reflectancia,olivo.get(),"defecto", "satelite");
        sistemaFincas.agregarHistoricoDatos(historicoDatos);
        historicoDatos.setVolumen(1.23);
        sistemaFincas.actualizarHistoricoDatos(historicoDatos);
        Optional<HistoricoDatos> optionalH = sistemaFincas.buscarHistoricoDatos(historicoDatosId);
        Assertions.assertThat(optionalH.isPresent()).isTrue();
        Assertions.assertThat(optionalH.get().getVolumen()).isEqualTo(1.23);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testEliminarHistoricoDatos()
    {
        Integer idObjeto = 34;
        Optional<Objeto> olivo = sistemaFincas.buscarObjeto(idObjeto);

        //Ahora buscamos el historico del olivo
        HistoricoDatosId historicoDatosId = new HistoricoDatosId(LocalDate.now(), olivo.get());
        String reflectancia="{\"data\":[{\"ID\":\"34\"},{\"Año\":\"2018\"},{\"Mes\":\"Febrero\"},{\"NDMI\":\"-0,03854723647236824\"},{\"NDVI\":\"0,1690598726272583\"},{\"SAVI\":\"0,2535554766654968\"}]}";
        HistoricoDatos historicoDatos=new HistoricoDatos(LocalDate.now(),null,reflectancia,olivo.get(),"defecto", "satelite");
        sistemaFincas.agregarHistoricoDatos(historicoDatos);
        sistemaFincas.eliminarHistoricoDatos(historicoDatos);
        Optional<HistoricoDatos> optionalH = sistemaFincas.buscarHistoricoDatos(historicoDatosId);
        Assertions.assertThat(optionalH.isPresent()).isFalse();
    }


}
