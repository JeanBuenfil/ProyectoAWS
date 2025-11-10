package com.example.DTO.update;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfesorUpdateDTO {
    
    private Integer numeroEmpleado;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüñÑ\\s]+$")
    private String nombres;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüñÑ\\s]+$")
    private String apellidos;

    @Positive
    private Integer horasClase;
}
