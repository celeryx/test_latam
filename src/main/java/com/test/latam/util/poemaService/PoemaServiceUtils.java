package com.test.latam.util.poemaService;

import com.test.latam.domain.response.PoemaRS;
import lombok.extern.java.Log;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Log
@Component
public class PoemaServiceUtils {

    public Map<String, String> obtenerNombres(String nombres) {
        HashMap<String,String> mapaNombres = new HashMap<>();
        String[] nombresL = nombres.split(" ");
        log.info(nombresL[0]);
        log.info(nombresL[1]);
        mapaNombres.put("primerNombre",nombresL[0].trim());
        mapaNombres.put("segundoNombre",nombresL[1].trim());
        return mapaNombres;
    }

    public Map<String, String> obtenerApellidos(String apellidos) {
        HashMap<String,String> mapaApellidos = new HashMap<>();
        String[] apellidosL = apellidos.trim().split(" ");
        mapaApellidos.put("apellidoPaterno",apellidosL[0].trim());
        mapaApellidos.put("apellidoMaterno",apellidosL[1].trim());
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

    public int ObtenerEdad() {
        return 2;
    }
}
