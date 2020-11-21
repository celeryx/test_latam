package com.test.latam.controllers;

import com.test.latam.domain.request.PoemaRQ;
import com.test.latam.domain.response.PoemaRS;
import com.test.latam.services.PoemaService;

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

@RestController
@Validated
public class PoemaController {

    @Autowired
    PoemaService poemaService;

    @PostMapping("/poema")
    public ResponseEntity<PoemaRS> poema(@Valid @RequestBody PoemaRQ req){
        return new ResponseEntity<>(poemaService.obtenerPoema(req), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/poema")
    public ResponseEntity<PoemaRS> poema(@RequestParam
                                         @NotNull
                                         @NotBlank(message = "El parametro nombres no debe estar vacio")
                                         @NotEmpty(message = "El parametro nombres no debe estar vacio")
                                         @Pattern(regexp = "(([A-Za-zÀ-ÖØ-öø-ÿ]*)(\\s?)([A-Za-zÀ-ÖØ-öø-ÿ]*?))$",
                                                  message = "El formato del parametro nombres es invalido " +
                                                            "\nmaximo dos nombres")
                                         String nombres,

                                         @RequestParam
                                         @NotNull
                                         @NotBlank(message = "El parametro apellidos no debe estar vacio")
                                         @NotEmpty(message = "El parametro apellidos no debe estar vacio")
                                         @Pattern(regexp = "(([A-Za-zÀ-ÖØ-öø-ÿ]*)(\\s?)([A-Za-zÀ-ÖØ-öø-ÿ]*?))$",
                                                 message = "El formato del parametro apellidos es invalido" +
                                                            "\nmaximo dos apellidos")
                                         String apellidos,

                                         @RequestParam
                                         @NotNull
                                         @NotBlank(message = "El parametro fechaNacimiento no debe estar vacio")
                                         @NotEmpty(message = "El parametro fechaNacimiento no debe estar vacio")
                                         @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$",
                                                  message = "El formato del parametro fechaNacimiento es invalido, el formato debe ser: DD-MM-AAAA")
                                         String fechaNacimiento) {

        return new ResponseEntity<>(poemaService.obtenerPoema(nombres, apellidos, fechaNacimiento), HttpStatus.OK);
    }
}
