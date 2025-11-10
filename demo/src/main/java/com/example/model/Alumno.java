package com.example.model;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Alumno {

    @NotNull
    private Integer id;

    @NotNull
    private String nombres;

    @NotNull
    private String apellidos;

    @NotNull
    private String matricula;

    @NotNull
    private Double promedio;
}
