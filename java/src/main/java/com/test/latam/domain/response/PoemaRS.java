package com.test.latam.domain.response;

import com.test.latam.domain.entities.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PoemaRS {

    private Persona persona;
    private int diasRestantesCumpleanos;
    private String poema;
    private String mensajeFelicitacion;
}
