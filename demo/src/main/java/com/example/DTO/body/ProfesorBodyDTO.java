package com.example.DTO.body;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProfesorBodyDTO {



    @NotNull
    private Integer numeroEmpleado;

    @NotBlank
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüñÑ\\s]+$")
    private String nombres;

    @NotBlank
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüñÑ\\s]+$")
    private String apellidos;

    @NotNull
    @Positive
    private Integer horasClase;
}
