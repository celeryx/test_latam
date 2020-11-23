package com.test.latam.util.poemaService;

import com.test.latam.constants.Constants;
import com.test.latam.domain.entities.Persona;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;


@Component
public class PoemaServiceUtils {

    public Map<String, String> obtenerNombresOApellidos(String texto, String constanteA, String constanteB) {

        HashMap<String,String> mapa = new HashMap<>();
        String[] textoL = texto.split(Constants.ESPACIO_BLANCO);
        String texto1 = textoL.length > 0 ? textoL[0].trim() : StringUtils.EMPTY;
        String texto2 = textoL.length > 1 ? textoL[1].trim() : StringUtils.EMPTY;
        mapa.put(constanteA,texto1);
        mapa.put(constanteB,texto2);

        return mapa;
    }

    public int obtenerDiasRestantesCumple(String fechaN) {

        LocalDate fechaNacimiento = formatearFecha(fechaN, Constants.FORMATO_FECHA_DDMMAAAA);
        LocalDate fechaH = obtenerFechaHoy();
        int year = fechaH.getYear ();
        MonthDay monthDayOfBirth = MonthDay.from ( fechaNacimiento );
        LocalDate proximoCumple = monthDayOfBirth.atYear ( year );
        proximoCumple = proximoCumple.isBefore(fechaH) ? proximoCumple.plusYears ( 1 ) : proximoCumple;
        int diasRestantes = (int)ChronoUnit.DAYS.between(fechaH, proximoCumple);

        return diasRestantes;
    }

    public int obtenerEdad(String fechaN) {

        LocalDate fechaNacimiento = formatearFecha(fechaN, Constants.FORMATO_FECHA_DDMMAAAA);
        LocalDate fechaH = obtenerFechaHoy();

        return Period.between(fechaNacimiento, fechaH).getYears();
    }

    public LocalDate formatearFecha(String fecha, String formato){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        LocalDate fechaFinal = LocalDate.parse ( fecha, formatter );

        return fechaFinal;
    }

    public LocalDate obtenerFechaHoy(){

        ZoneId idZona = ZoneId.systemDefault();
        LocalDate fechaH = LocalDate.now ( idZona );

        return fechaH;
    }

    public Persona crearPersona(Map<String, String> mapaNombres, Map<String, String> mapaApellidos,
                                int edad, String fechaN) {

        Persona persona = new Persona();
        persona.setPrimerNombre(mapaNombres.get(Constants.PRIMER_NOMBRE));
        persona.setSegundoNombre(mapaNombres.get(Constants.SEGUNDO_NOMBRE));
        persona.setApellidoPaterno(mapaApellidos.get(Constants.APELLIDO_PATERNO));
        persona.setApellidoMaterno(mapaApellidos.get(Constants.APELLIDO_MATERNO));
        persona.setEdad(edad);
        persona.setFechaNacimiento(fechaN.replaceAll(Constants.GUION, Constants.SLASH));

        return persona;
    }

}
