package com.example.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IdProfesorDuplicadoException extends RuntimeException {
    private HttpStatus httpStatus;

    public IdProfesorDuplicadoException(String mensaje, HttpStatus httpStatus){
        super(mensaje);
        this.httpStatus = httpStatus;
    }

}
