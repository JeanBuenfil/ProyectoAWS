package com.example.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IdAlumnoDuplicadoException extends RuntimeException {
    private HttpStatus httpStatus;

    public IdAlumnoDuplicadoException(String mensaje, HttpStatus httpStatus){
        super(mensaje);
        this.httpStatus = httpStatus;
    }
}
