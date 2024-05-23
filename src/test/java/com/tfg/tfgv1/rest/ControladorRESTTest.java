package com.tfg.tfgv1.rest;

import com.tfg.tfgv1.TfgV1Application;
import com.tfg.tfgv1.rest.dto.*;
import jakarta.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(classes = TfgV1Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControladorRESTTest
{
    @LocalServerPort
    int localPort;

    @Autowired
    MappingJackson2HttpMessageConverter springBootJacksonConverter;

    TestRestTemplate restTemplate;

    /**
     * Crear un TestRestTemplate para las pruebas
     */
    @PostConstruct
    void crearRestTemplateBuilder() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .rootUri("http://localhost:" + localPort + "/TFG")
                .additionalMessageConverters(List.of(springBootJacksonConverter));

        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }
/*
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testAltaProvincia()
    {
        DTOProvincia provincia = new DTOProvincia(4, "Ciudad Real");
        ResponseEntity<DTOProvincia> response = restTemplate.postForEntity("/provincias", provincia, DTOProvincia.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
    */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testVerProvincia()
    {
        ResponseEntity<DTOProvincia> response = restTemplate.getForEntity("/provincias/1", DTOProvincia.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testVerProvinciaNoEncontrada()
    {
        ResponseEntity<DTOProvincia> response = restTemplate.getForEntity("/provincias/100", DTOProvincia.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testVerMunicipio()
    {
        ResponseEntity<DTOProvincia> response = restTemplate.getForEntity("/provincias/23/municipios/41", DTOProvincia.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testVerMunicipioNoEncontrado()
    {
        ResponseEntity<DTOProvincia> response = restTemplate.getForEntity("/provincias/1/municipios/100", DTOProvincia.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testVerMunicipioProvinciaNoEncontrada()
    {
        ResponseEntity<DTOProvincia> response = restTemplate.getForEntity("/provincias/100/municipios/1", DTOProvincia.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testVerZona()
    {
        ResponseEntity<DTOProvincia> response = restTemplate.getForEntity("/provincias/23/municipios/59/zonas/J1", DTOProvincia.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testVerZonaNoEncontrada()
    {
        ResponseEntity<DTOProvincia> response = restTemplate.getForEntity("/provincias/23/municipios/41/zonas/100", DTOProvincia.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testVerFinca()
    {
        ResponseEntity<DTOProvincia> response = restTemplate.getForEntity("/provincias/23/municipios/59/zonas/J1/fincas/6/211/2", DTOProvincia.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testVerFincaNoEncontrada()
    {
        ResponseEntity<DTOProvincia> response = restTemplate.getForEntity("/provincias/23/municipios/41/zonas/1/fincas/7/2/1", DTOProvincia.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testVerFincasConHistorico()
    {
        ResponseEntity<DTOFinca[]> response = restTemplate.getForEntity("/FincasConHistorico", DTOFinca[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DTOFinca[] fincas = response.getBody();
        Assertions.assertThat(fincas).isNotEmpty();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarFincaEsp()
    {
        int provinciaCodigo=23;
        int municipioCodigo=87;
        int poligono=42;
        int parcela=284;
        int recinto=2;
        ResponseEntity<DTOFinca> response = restTemplate.getForEntity("/provincia/"+provinciaCodigo+"/municipio/"+
                municipioCodigo+"/finca/"+poligono+"/"+parcela+"/"+recinto, DTOFinca.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DTOFinca finca = response.getBody();
        Assertions.assertThat(finca).isNotNull();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarFincasCompletas()
    {
        ResponseEntity<DTOFinca[]> response = restTemplate.getForEntity("/FincasCompletas", DTOFinca[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DTOFinca[] fincas = response.getBody();
        Assertions.assertThat(fincas).isNotEmpty();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarZonasCompletas()
    {
        ResponseEntity<DTOZona[]> response = restTemplate.getForEntity("/ZonasCompletas", DTOZona[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DTOZona[] zonas = response.getBody();
        Assertions.assertThat(zonas).isNotEmpty();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarCosechaFinca()
    {
        int provinciaCodigo=23;
        int municipioCodigo=87;
        int poligono=42;
        int parcela=284;
        int recinto=2;

        ResponseEntity<DTOCosecha[]> response = restTemplate.getForEntity("/provincia/"+provinciaCodigo+"/municipio/" +
                municipioCodigo + "/finca/" + poligono + "/" + parcela + "/" + recinto + "/cosechas", DTOCosecha[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DTOCosecha[] cosechas = response.getBody();
        Assertions.assertThat(cosechas).isNotEmpty();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarHistoricoFincaPorAnio()
    {
        int provinciaCodigo=23;
        int municipioCodigo=32;
        int poligono=3;
        int parcela=101;
        int recinto=3;
        int anio=2019;

        ResponseEntity<DTOHistoricoFinca[]> response = restTemplate.getForEntity("/provincia/"+provinciaCodigo+"/municipio/" +
                municipioCodigo + "/finca/" + poligono + "/" + parcela + "/" + recinto + "/historico/" + anio+"/sat", DTOHistoricoFinca[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DTOHistoricoFinca[] historicos = response.getBody();
        Assertions.assertThat(historicos).isNotEmpty();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarHistoricoFincaPorAnioDron()
    {
        int provinciaCodigo=23;
        int municipioCodigo=59;
        int poligono=6;
        int parcela=211;
        int recinto=1;
        int anio=2023;

        ResponseEntity<DTOHistoricoFinca[]> response = restTemplate.getForEntity("/provincia/"+provinciaCodigo+"/municipio/" +
                municipioCodigo + "/finca/" + poligono + "/" + parcela + "/" + recinto + "/historico/" + anio+"/dron", DTOHistoricoFinca[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DTOHistoricoFinca[] historicos = response.getBody();
        Assertions.assertThat(historicos).isNotEmpty();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarOlivosPorZona()
    {
        int provinciaCodigo=23;
        int municipioCodigo=5;
        String zonaUbicacion="J3";
        ResponseEntity<DTOObjeto[]> response = restTemplate.getForEntity("/provincia/"+provinciaCodigo+"/municipio/" +
                municipioCodigo + "/zona/" + zonaUbicacion + "/objetos", DTOObjeto[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DTOObjeto[] olivos = response.getBody();
        Assertions.assertThat(olivos).isNotEmpty();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarHistoricoOlivoPorAnioSat()
    {
        int id=588;
        int anio=2020;
        ResponseEntity<DTOHistoricoDatos[]> response = restTemplate.getForEntity("/historico/"+anio+"/olivo/" +
                id+"/sat", DTOHistoricoDatos[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DTOHistoricoDatos[] historicos = response.getBody();
        Assertions.assertThat(historicos).isNotEmpty();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testBuscarHistoricoOlivoPorAnioDron()
    {
        int id=2424;
        int anio=2023;
        ResponseEntity<DTOHistoricoDatos[]> response = restTemplate.getForEntity("/historico/"+anio+"/olivo/" +
                id+"/dron", DTOHistoricoDatos[].class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DTOHistoricoDatos[] historicos = response.getBody();
        Assertions.assertThat(historicos).isNotEmpty();
    }
}
