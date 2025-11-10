package com.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Profesor {

    @NotNull
    private Integer id;

    @NotNull
    private Integer numeroEmpleado;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @NotNull
    @Positive
    private Integer horasClase;

}
