package com.example.DTO.update;


import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfesorUpdateDTO {
    
    private Integer numeroEmpleado;

    private String nombres;

    private String apellidos;

    @Positive
    private Integer horasClase;
}
