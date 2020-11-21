package com.test.latam.services;

import com.test.latam.clients.RandomPoemsClient;
import com.test.latam.domain.entities.Persona;
import com.test.latam.domain.request.PoemaRQ;
import com.test.latam.domain.response.PoemaRS;
import com.test.latam.util.poemaService.PoemaServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        PoemaRS poemaRS = new PoemaRS();
        Persona persona = psu.crearPersona(mapaNombres, mapaApellidos, edad, fechaN);
        String mensajeFelicitacion = diasProxCumple <= 0
                ? "Feliz Cumpleaños "+ persona.getPrimerNombre() +" "+ persona.getApellidoPaterno() +
                ", muchas felicidades por haber cumplido " + edad + " años"
                : "";
        poemaRS.setDiasRestantesCumpleanos(diasProxCumple);
        poemaRS.setPoema(poema);
        poemaRS.setPersona(persona);
        poemaRS.setMensajeFelicitacion(mensajeFelicitacion);
        return poemaRS;
    }

}
