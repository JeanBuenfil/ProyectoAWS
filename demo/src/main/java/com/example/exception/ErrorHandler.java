package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
@SuppressWarnings("")
public class ErrorHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleErrorEntityNotFound(){
        return "No existe registro con ese ID";
    }

    @ExceptionHandler(IdAlumnoDuplicadoException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handleIdAlumnoDuplicadoException(){
        return "ID duplicado";
    }

    @ExceptionHandler(IdProfesorDuplicadoException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handleIdProfesorDuplicadoException(){
        return "ID duplicado";
    }
}
