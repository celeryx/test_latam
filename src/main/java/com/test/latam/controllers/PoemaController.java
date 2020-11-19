package com.test.latam.controllers;

import com.test.latam.domain.entities.Poema;
import com.test.latam.domain.request.PoemaRQ;
import com.test.latam.domain.response.PoemaRS;
import com.test.latam.services.PoemaService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Log
@RestController
@Validated
public class PoemaController {

    @Autowired
    PoemaService poemaService;

    @PostMapping("/poema")
    public ResponseEntity<PoemaRS> poema(@Valid @RequestBody PoemaRQ req){
        return new ResponseEntity<>(poemaService.obtenerPoema(req), HttpStatus.ACCEPTED);
    }

    @GetMapping("/poema")
    public ResponseEntity<PoemaRS> poema(@RequestParam
                                         @NotNull
                                         @NotBlank(message = "No debe estar vacio")
                                         @NotEmpty(message = "El nombre no debe estar vacio")
                                         @Pattern(regexp = "[A-Za-zÀ-ÖØ-öø-ÿ]",
                                                  message = "El formato del nombre es invalido")
                                         String nombres,

                                         @RequestParam
                                         @NotNull
                                         @NotBlank(message = "No debe estar vacio")
                                         @NotEmpty(message = "El apellido no debe estar vacio")
                                         @Pattern(regexp = "[A-Za-zÀ-ÖØ-öø-ÿ]",
                                                 message = "El formato del apellido es invalido")
                                         String apellidos,

                                         @RequestParam
                                         @NotNull
                                         @NotBlank(message = "No debe estar vacio")
                                         @NotEmpty(message = "La fecha no debe estar vacia")
                                         @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$",
                                                  message = "El formato de la fecha es invalido, el formato es: DD-MM-AAAA")
                                         String fechaNacimiento) {

        return new ResponseEntity<>(poemaService.obtenerPoema(nombres, apellidos, fechaNacimiento), HttpStatus.ACCEPTED);
    }
}
