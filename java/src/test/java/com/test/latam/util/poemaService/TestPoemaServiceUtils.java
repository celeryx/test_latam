package com.test.latam.util.poemaService;

import com.test.latam.constants.Constants;
import com.test.latam.domain.entities.Persona;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class TestPoemaServiceUtils {

    @InjectMocks
    PoemaServiceUtils psu;

    private String nombres;
    private String nombre;
    private String apellidos;
    private String apellido;
    private String fechaN;
    private String fechaMH;
    private Persona persona;
    private HashMap<String,String> resObtenerNombres;
    private HashMap<String,String> resObtenerApellidos;
    private int resObtenerEdad;

    @Before
    public void init() {
        nombres = "Pablo Alejandro";
        nombre = "Pablo";
        apellidos = "Silva Aravena";
        apellido = "Silva";
        fechaN = "10-04-1990";
        LocalDate fecha = psu.obtenerFechaHoy();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        fechaMH = fecha.format(formatter);
        resObtenerEdad = 30;
        inicializarNombresYApellidos();
        inicializarPerona();

    }

    @Test
    public void testObtenerNombresOApellidos(){
        Assert.assertNotNull(psu.obtenerNombresOApellidos(nombres, Constants.PRIMER_NOMBRE,Constants.SEGUNDO_NOMBRE));
        Assert.assertNotNull(psu.obtenerNombresOApellidos(nombre, Constants.PRIMER_NOMBRE,Constants.SEGUNDO_NOMBRE));
        Assert.assertNotNull(psu.obtenerNombresOApellidos(StringUtils.SPACE, Constants.PRIMER_NOMBRE,Constants.SEGUNDO_NOMBRE));

    }

    @Test
    public void testObtenerDiasRestantesCumple(){
        Assert.assertNotNull(psu.obtenerDiasRestantesCumple(fechaN));
        Assert.assertNotNull(psu.obtenerDiasRestantesCumple(fechaMH));

    }

    @Test
    public void testObtenerEdad(){
        Assert.assertNotNull(psu.obtenerEdad(fechaN));
        Assert.assertNotNull(psu.obtenerEdad(fechaMH));

    }

    @Test
    public void testCrearPersona(){
        Assert.assertNotNull(psu.crearPersona(resObtenerNombres,resObtenerApellidos,resObtenerEdad,fechaN));
    }

    private void inicializarPerona() {

        persona = new Persona();
        persona.setPrimerNombre(resObtenerNombres.get(Constants.PRIMER_NOMBRE));
        persona.setSegundoNombre(resObtenerNombres.get(Constants.SEGUNDO_NOMBRE));
        persona.setApellidoPaterno(resObtenerApellidos.get(Constants.APELLIDO_PATERNO));
        persona.setApellidoMaterno(resObtenerApellidos.get(Constants.APELLIDO_MATERNO));
        persona.setEdad(resObtenerEdad);
        persona.setFechaNacimiento(fechaN.replaceAll(Constants.GUION, Constants.SLASH));
    }

    private void inicializarNombresYApellidos() {

        resObtenerNombres = new HashMap<>();
        resObtenerNombres.put(Constants.PRIMER_NOMBRE, "Pablo");
        resObtenerNombres.put(Constants.SEGUNDO_NOMBRE, "Alejandro");

        resObtenerApellidos = new HashMap<>();
        resObtenerApellidos.put(Constants.APELLIDO_PATERNO, "Silva");
        resObtenerApellidos.put(Constants.APELLIDO_MATERNO, "Aravena");
    }

}
