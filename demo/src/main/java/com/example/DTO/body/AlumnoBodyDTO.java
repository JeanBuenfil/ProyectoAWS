package com.example.DTO.body;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlumnoBodyDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @NotBlank
    @Pattern(regexp = "^A\\d+$", message = "La matrícula debe empezar con 'A'")
    private String matricula;

    @Min(value = 0)
    @Max(value = 100)
    @NotNull
    private Double promedio;
}
