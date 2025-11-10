package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
@SuppressWarnings("")
public class ErrorHandler {
    
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMensaje NullPointerException(){
        return new ErrorMensaje(
        "No se encontró el registro"
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMensaje handleErrorEntityNotFound(){
        return new ErrorMensaje(
        "No existe registro con ese ID"
        );
    }

    @ExceptionHandler(IdAlumnoDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMensaje handleIdAlumnoDuplicadoException(){
        return new ErrorMensaje(
            "ID duplicado"
        );
    }

    @ExceptionHandler(IdProfesorDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMensaje handleIdProfesorDuplicadoException(){
        return new ErrorMensaje(
            "ID duplicado"
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMensaje HttpMessageNotReadableException(){
        return new ErrorMensaje(
            "Se ingresó un campo no válido"
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMensaje MethodArgumentNotValidException(){
        return new ErrorMensaje(
            "Se ingresó un campo no válido o no se ingresaron todos los campos"
        );
    }
}
