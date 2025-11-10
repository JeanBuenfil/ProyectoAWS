package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.body.AlumnoBodyDTO;
import com.example.DTO.update.AlumnoUpdateDTO;
import com.example.exception.IdAlumnoDuplicadoException;
import com.example.model.Alumno;
import com.example.service.AlumnoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @PostMapping
    public ResponseEntity<Alumno> crearAlumno(@Valid @RequestBody AlumnoBodyDTO data) {
        if(alumnoService.encontrarAlumnoPorId(data.getId()) != null){
            throw new IdAlumnoDuplicadoException();
        }
        Alumno alumno = alumnoService.crearAlumno(data);
        return new ResponseEntity<Alumno>(alumno, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> encontrarAlumnos() {
        List<Alumno> alumnos = alumnoService.encontrarAlumnos();
        return new ResponseEntity<List<Alumno>>(alumnos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> encontrarAlumnoConId(@PathVariable int id) {
        Alumno alumno = this.alumnoService.encontrarAlumnoPorId(id);
        if(alumno == null){
            throw new EntityNotFoundException();
        }
        return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable int id, @Valid @RequestBody AlumnoUpdateDTO alumnoUpdateDTO) {
        Alumno alumno = alumnoService.actualizarAlumno(id, alumnoUpdateDTO);
        
        return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public void eliminarAlumno(@PathVariable int id){
        alumnoService.eliminarAlumno(id);
    }
}
