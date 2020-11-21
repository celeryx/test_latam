package com.test.latam.domain.request;

import com.test.latam.domain.entities.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PoemaRQ {

    @Valid
    @NotNull(message = "The author email '${validatedValue}' must be between {min} and {max} characters long")
    private Persona persona;
}
