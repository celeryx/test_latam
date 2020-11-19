package com.test.latam.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private Integer status;
    private String error;
    private String message;
    private String path;
}
