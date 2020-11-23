package com.test.latam.services;

import com.test.latam.clients.RandomPoemsClient;
import com.test.latam.components.Messages;
import com.test.latam.constants.Constants;
import com.test.latam.domain.entities.Persona;
import com.test.latam.util.poemaService.PoemaServiceUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.core.env.Environment;

import java.util.HashMap;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest()
public class TestPoemaService {

    @InjectMocks
    private PoemaService poemaService;
    @Mock
    PoemaServiceUtils psu;
    @Mock
    RandomPoemsClient rpc;
    @Mock
    private Environment environment;
    @Mock
    private Messages messages;

    private String nombres;
    private String apellidos;
    private String fechaN;
    HashMap<String,String> resObtenerNombres;
    HashMap<String,String> resObtenerApellidos;
    int resObtenerEdad;
    int resObtenerDiasRestantesCumple;
    String resObtenerPoema;
    Persona persona;

    @Before
    public void init() {
        inicializarParametro();
        inicializarNombresYApellidos();
        inicializarPerona();
    }

    @Test
    public void testObtenerPoemaCumpleanos(){

        resObtenerEdad = 30;
        resObtenerDiasRestantesCumple = 0;
        resObtenerPoema = "POEMA";

        when(psu.obtenerNombresOApellidos(nombres, Constants.PRIMER_NOMBRE, Constants.SEGUNDO_NOMBRE)).thenReturn(resObtenerNombres);
        when(psu.obtenerNombresOApellidos(apellidos, Constants.APELLIDO_PATERNO, Constants.APELLIDO_MATERNO)).thenReturn(resObtenerApellidos);
        when(psu.obtenerEdad(fechaN)).thenReturn(resObtenerEdad);
        when(psu.obtenerDiasRestantesCumple(fechaN)).thenReturn(resObtenerDiasRestantesCumple);
        when(rpc.obtenerPoema(environment)).thenReturn(resObtenerPoema);
        when(psu.crearPersona(resObtenerNombres, resObtenerApellidos, resObtenerEdad, fechaN)).thenReturn(persona);
        when(messages.get(Constants.MENSAJE_CUMPLE)).thenReturn("Feliz Cumpleanios");
        Assert.assertNotNull(poemaService.obtenerPoema(nombres, apellidos, fechaN));
    }

    @Test
    public void testObtenerPoemaNoCumpleanos(){

        resObtenerEdad = 30;
        resObtenerDiasRestantesCumple = 142;
        resObtenerPoema = "";

        when(psu.obtenerNombresOApellidos(nombres, Constants.PRIMER_NOMBRE, Constants.SEGUNDO_NOMBRE)).thenReturn(resObtenerNombres);
        when(psu.obtenerNombresOApellidos(apellidos, Constants.APELLIDO_PATERNO, Constants.APELLIDO_MATERNO)).thenReturn(resObtenerApellidos);
        when(psu.obtenerEdad(fechaN)).thenReturn(resObtenerEdad);
        when(psu.obtenerDiasRestantesCumple(fechaN)).thenReturn(resObtenerDiasRestantesCumple);
        when(rpc.obtenerPoema(environment)).thenReturn(resObtenerPoema);
        when(psu.crearPersona(resObtenerNombres, resObtenerApellidos, resObtenerEdad, fechaN)).thenReturn(persona);
        when(messages.get(Constants.MENSAJE_CUMPLE)).thenReturn("");
        Assert.assertNotNull(poemaService.obtenerPoema(nombres, apellidos, fechaN));
    }

    private void inicializarParametro() {

        nombres = "Pablo Alejandro";
        apellidos = "Silva Aravena";
        fechaN = "10-04-1990";
    }

    private void inicializarNombresYApellidos() {

        resObtenerNombres = new HashMap<>();
        resObtenerNombres.put(Constants.PRIMER_NOMBRE, "Pablo");
        resObtenerNombres.put(Constants.SEGUNDO_NOMBRE, "Alejandro");

        resObtenerApellidos = new HashMap<>();
        resObtenerApellidos.put(Constants.APELLIDO_PATERNO, "Silva");
        resObtenerApellidos.put(Constants.APELLIDO_MATERNO, "Aravena");
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

}
