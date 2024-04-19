package com.tfg.tfgv1.rest;

import com.tfg.tfgv1.TfgV1Application;
import com.tfg.tfgv1.rest.dto.DTOFinca;
import com.tfg.tfgv1.rest.dto.DTOProvincia;
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
}
