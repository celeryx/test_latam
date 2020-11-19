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
        log.info(mapaNombres.get("primerNombre"));
        int edad = psu.ObtenerEdad();
        int diasProxCumple = psu.obtenerDiasRestantesCumple(fechaN);
        log.info("DIAS :: "+ diasProxCumple);
        String poema = "";
        if (diasProxCumple == 0) {
            poema = rpc.obtenerPoema();
            log.info("POEMA ::: "+ poema);
        }

        PoemaRS poemaRS = new PoemaRS();
        Persona persona = crearPersona(mapaNombres, mapaApellidos, edad, fechaN);
        poemaRS.setDiasRestantesCumpleanos(diasProxCumple);
        poemaRS.setPoema(poema);
        poemaRS.setPersona(persona);
        poemaRS.setMensajeFelicitacion("Feliz Cumpleaños, muchas felicidades por haber cumplido " + edad + " años");
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
