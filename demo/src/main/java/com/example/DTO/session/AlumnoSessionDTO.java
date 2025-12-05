package com.example.DTO.session;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlumnoSessionDTO {
    
    @NotBlank
    private String id;

    @NotNull
    private Long fecha;

    @NotNull
    private Integer alumnoId;

    @NotNull
    private Boolean active;

    @NotBlank
    @Size(min = 128, max = 128)
    private String sessionString;
}
