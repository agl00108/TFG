package com.tfg.tfgv1.servicios;

import com.tfg.tfgv1.TfgV1Application;
import com.tfg.tfgv1.entidades.Provincia;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

@SpringBootTest(classes = TfgV1Application.class)
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
}
