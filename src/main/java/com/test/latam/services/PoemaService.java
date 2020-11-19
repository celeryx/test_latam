package com.test.latam.services;

import com.test.latam.clients.RandomPoemsClient;
import com.test.latam.domain.entities.Persona;
import com.test.latam.domain.request.PoemaRQ;
import com.test.latam.domain.response.PoemaRS;
import com.test.latam.util.poemaService.PoemaServiceUtils;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Log
@Service
public class PoemaService {

    @Autowired
    PoemaServiceUtils psu;
    @Autowired
    RandomPoemsClient rpc;

    public PoemaRS obtenerPoema(PoemaRQ req) {
        return new PoemaRS();
    }

    public PoemaRS obtenerPoema(String nombres, String apellidos, String fechaN) {

        Map<String, String> mapaNombres = psu.obtenerNombres(nombres);
        Map<String, String> mapaApellidos = psu.obtenerApellidos(apellidos);
        int edad = psu.ObtenerEdad(fechaN);
        int diasProxCumple = psu.obtenerDiasRestantesCumple(fechaN);
        String poema = diasProxCumple <= 0 ? rpc.obtenerPoema() : "";
        String mensajeFelicitacion = diasProxCumple <= 0
                                   ? "Feliz Cumpleaños, muchas felicidades por haber cumplido " + edad + " años"
                                   : "";

        String msjProximoCumple = diasProxCumple <= 0 ? "" : "Faltan " + diasProxCumple + " dias para su cumpleaños";
        PoemaRS poemaRS = new PoemaRS();
        Persona persona = crearPersona(mapaNombres, mapaApellidos, edad, fechaN);
        poemaRS.setDiasRestantesCumpleanos(msjProximoCumple);
        poemaRS.setPoema(poema);
        poemaRS.setPersona(persona);
        poemaRS.setMensajeFelicitacion(mensajeFelicitacion);
        return poemaRS;
    }

    private Persona crearPersona(Map<String, String> mapaNombres, Map<String, String> mapaApellidos, int edad,
                                 String fechaN) {
        Persona persona = new Persona();
        persona.setPrimerNombre(mapaNombres.get("primerNombre"));
        persona.setSegundoNombre(mapaNombres.get("segundoNombre"));
        persona.setApellidoPaterno(mapaApellidos.get("apellidoPaterno"));
        persona.setApellidoMaterno(mapaApellidos.get("apellidoMaterno"));
        persona.setEdad(edad);
        persona.setFechaNacimiento(fechaN.replaceAll("-", "/"));
        return persona;
    }
}
