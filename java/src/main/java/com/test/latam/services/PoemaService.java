package com.test.latam.services;

import com.test.latam.clients.RandomPoemsClient;
import com.test.latam.components.Messages;
import com.test.latam.constants.Constants;
import com.test.latam.domain.entities.Persona;
import com.test.latam.domain.request.PoemaRQ;
import com.test.latam.domain.response.PoemaRS;
import com.test.latam.util.poemaService.PoemaServiceUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service

public class PoemaService {

    @Autowired
    PoemaServiceUtils psu;
    @Autowired
    RandomPoemsClient rpc;
    @Autowired
    private Environment environment;
    @Autowired
    private Messages messages;

    public PoemaRS obtenerPoema(PoemaRQ req) {
        return new PoemaRS();
    }

    public PoemaRS obtenerPoema(String nombres, String apellidos, String fechaN) {

        Map<String, String> mapaNombres = psu.obtenerNombresOApellidos(nombres, Constants.PRIMER_NOMBRE,
                                                                       Constants.SEGUNDO_NOMBRE);

        Map<String, String> mapaApellidos = psu.obtenerNombresOApellidos(apellidos, Constants.APELLIDO_PATERNO,
                                                                         Constants.APELLIDO_MATERNO);

        int edad = psu.ObtenerEdad(fechaN);
        int diasProxCumple = psu.obtenerDiasRestantesCumple(fechaN);
        String poema = diasProxCumple <= 0 ? rpc.obtenerPoema(environment) : StringUtils.EMPTY;


        Persona persona = psu.crearPersona(mapaNombres, mapaApellidos, edad, fechaN);

        String mensajeFelicitacion = String.format(messages.get(Constants.MENSAJE_CUMPLE), persona.getPrimerNombre(),
                                                                                           persona.getApellidoPaterno(),
                                                                                           persona.getEdad());

        mensajeFelicitacion = diasProxCumple <= 0 ? mensajeFelicitacion : StringUtils.EMPTY;

        PoemaRS poemaRS = new PoemaRS();
        poemaRS.setDiasRestantesCumpleanos(diasProxCumple);
        poemaRS.setPoema(poema);
        poemaRS.setPersona(persona);
        poemaRS.setMensajeFelicitacion(mensajeFelicitacion);

        return poemaRS;
    }

}
