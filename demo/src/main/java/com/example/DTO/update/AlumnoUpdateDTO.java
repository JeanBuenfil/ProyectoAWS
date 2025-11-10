package com.example.DTO.update;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlumnoUpdateDTO {

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüñÑ\\s]+$")
    private String nombres;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüñÑ\\s]+$")
    private String apellidos;

    @Pattern(regexp = "^A\\d+$")
    private String matricula;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "100.0")
    private Double promedio;
}
