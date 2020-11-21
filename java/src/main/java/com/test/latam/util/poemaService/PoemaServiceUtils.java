package com.test.latam.util.poemaService;

import com.test.latam.domain.entities.Persona;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Component
public class PoemaServiceUtils {

    public Map<String, String> obtenerNombres(String nombres) {
        HashMap<String,String> mapaNombres = new HashMap<>();
        String[] nombresL = nombres.split(" ");
        String primerNombre = nombresL.length > 0 ? nombresL[0].trim() : "";
        String segundoNombre = nombresL.length > 1 ? nombresL[1].trim() : "";
        mapaNombres.put("primerNombre",primerNombre);
        mapaNombres.put("segundoNombre",segundoNombre);
        return mapaNombres;
    }

    public Map<String, String> obtenerApellidos(String apellidos) {
        HashMap<String,String> mapaApellidos = new HashMap<>();
        String[] apellidosL = apellidos.trim().split(" ");
        String apellidoPaterno = apellidosL.length > 0 ? apellidosL[0].trim() : "";
        String apellidoMaterno = apellidosL.length > 1 ? apellidosL[1].trim() : "";
        mapaApellidos.put("apellidoPaterno",apellidoPaterno);
        mapaApellidos.put("apellidoMaterno",apellidoMaterno);
        return mapaApellidos;
    }

    public int obtenerDiasRestantesCumple(String fechaN) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaNacimiento = LocalDate.parse ( fechaN, formatter );
        ZoneId  idZona = ZoneId.systemDefault();
        LocalDate fechaH = LocalDate.now ( idZona );
        int year = fechaH.getYear ();
        MonthDay monthDayOfBirth = MonthDay.from ( fechaNacimiento );
        LocalDate proximoCumple = monthDayOfBirth.atYear ( year );

        if ( proximoCumple.isBefore ( fechaH ) ) {
            proximoCumple = proximoCumple.plusYears ( 1 );
        }
        int diasRestantes = (int)ChronoUnit.DAYS.between(fechaH, proximoCumple);
        return diasRestantes;
    }

    public int ObtenerEdad(String fechaN) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaNacimiento = LocalDate.parse ( fechaN, formatter );
        ZoneId  idZona = ZoneId.systemDefault();
        LocalDate fechaH = LocalDate.now ( idZona );
        return Period.between(fechaNacimiento, fechaH).getYears();
    }

    public Persona crearPersona(Map<String, String> mapaNombres, Map<String, String> mapaApellidos,
                                 int edad, String fechaN) {
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
