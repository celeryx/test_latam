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

    @CrossOrigin
    @PostMapping("/poema")
    public ResponseEntity<PoemaRS> poema(@Valid @RequestBody PoemaRQ req){
        return new ResponseEntity<>(poemaService.obtenerPoema(req), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/poema")
    public ResponseEntity<PoemaRS> poema(@RequestParam
                                         @NotNull
                                         @NotBlank
                                         @NotEmpty
                                         @Pattern(regexp = "(([A-Za-zÀ-ÖØ-öø-ÿ]*)(\\s?)([A-Za-zÀ-ÖØ-öø-ÿ]*?))$")
                                         String nombres,

                                         @RequestParam
                                         @NotNull
                                         @NotBlank
                                         @NotEmpty
                                         @Pattern(regexp = "(([A-Za-zÀ-ÖØ-öø-ÿ]*)(\\s?)([A-Za-zÀ-ÖØ-öø-ÿ]*?))$")
                                         String apellidos,

                                         @RequestParam
                                         @NotNull
                                         @NotBlank
                                         @NotEmpty
                                         @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$")
                                         String fechaNacimiento) {

        return new ResponseEntity<>(poemaService.obtenerPoema(nombres, apellidos, fechaNacimiento), HttpStatus.OK);
    }
}
