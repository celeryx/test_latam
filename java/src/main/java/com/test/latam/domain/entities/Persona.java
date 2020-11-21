package com.test.latam.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Persona {

    @NotNull
    private String primerNombre;
    @NotNull
    private String segundoNombre;
    @NotNull
    private String apellidoPaterno;
    @NotNull
    private String apellidoMaterno;
    private int edad;
    @NotNull(message = "UEIWIUEUQUIWEUIWQUIEUIWQWUIEUIWQ")
    private String fechaNacimiento;
}
