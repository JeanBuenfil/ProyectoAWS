package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.body.ProfesorBodyDTO;
import com.example.DTO.update.ProfesorUpdateDTO;
import com.example.model.Profesor;
import com.example.service.ProfesorService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @PostMapping
    public ResponseEntity<Profesor> crearProfesor(@Valid @RequestBody ProfesorBodyDTO data) {
        Profesor profesor = profesorService.crearProfesor(data);
        return new ResponseEntity<Profesor>(profesor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> encontrarProfesores() {
        List<Profesor> profesores = profesorService.encontrarProfesores();
        return new ResponseEntity<List<Profesor>>(profesores,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> encontrarProfesorConId(@PathVariable int id) {
        Profesor profesor = this.profesorService.encontrarProfesorPorId(id);
        if(profesor == null){
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizarProfesor(@PathVariable int id, @Valid @RequestBody ProfesorUpdateDTO profesorUpdateDTO) {
        Profesor profesor = profesorService.actualizarProfesor(id, profesorUpdateDTO);
        
        return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Profesor> eliminarProfesor(@PathVariable int id){
        Profesor profesor = profesorService.encontrarProfesorPorId(id);
        profesorService.eliminarProfesor(id);
        return new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
    }
}
