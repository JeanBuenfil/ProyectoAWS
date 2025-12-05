package com.example.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.DTO.body.AlumnoBodyDTO;
import com.example.DTO.session.AlumnoSessionDTO;
import com.example.DTO.update.AlumnoUpdateDTO;
import com.example.model.Alumno;
import com.example.service.AlumnoService;
import com.example.service.SessionService;

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

    @Autowired 
    private SessionService sessionService;

    

    @PostMapping
    public ResponseEntity<Alumno> crearAlumno(@Valid @RequestBody AlumnoBodyDTO data) {
        Alumno alumno = alumnoService.crearAlumno(data);
        return new ResponseEntity<Alumno>(alumno, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/fotoPerfil")
    public ResponseEntity<?> agregarFotoAlumno(@PathVariable int id, @RequestBody MultipartFile foto) throws IOException{
        String fotoPerfilUrl = alumnoService.agregarFotoPerfil(id, foto);
        
        return ResponseEntity.ok(Map.of("fotoPerfilUrl", fotoPerfilUrl));
    }
    
    @PostMapping("/{id}/email")
    public ResponseEntity<Alumno> enviarEmail(@PathVariable int id){
        Alumno alumno = alumnoService.encontrarAlumnoPorId(id);
        alumnoService.enviarEmail(alumno);
        return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    }

    @PostMapping("/{id}/session/login")
    public ResponseEntity<?> iniciarSesion(@PathVariable int id, @RequestBody Map<String, String> data){
        String password = data.get("password");

        Alumno alumno = alumnoService.encontrarAlumnoPorId(id);
        if (!alumno.getPassword().equals(password)) {
        return ResponseEntity.status(400).body("Contraseña incorrecta");
    }
        
        AlumnoSessionDTO session = sessionService.crearSesion(alumno.getId());
        return new ResponseEntity<AlumnoSessionDTO>(session, HttpStatus.OK);
    }

    @PostMapping("/{id}/session/verify")
    public ResponseEntity<AlumnoSessionDTO> verificarSesion(@PathVariable int id, @RequestBody Map<String, String> data){
        String sessionString = data.get("sessionString");
        AlumnoSessionDTO session = sessionService.verificarSesion(sessionString, id);
        return new ResponseEntity<AlumnoSessionDTO>(session, HttpStatus.OK);
    }

    @PostMapping("/{id}/session/logout")
    public ResponseEntity<AlumnoSessionDTO> terminarSesion(@PathVariable int id, @RequestBody Map<String, String> data){
        String sessionString = data.get("sessionString");
        AlumnoSessionDTO session = sessionService.verificarSesion(sessionString, id);
        sessionService.terminarSesion(sessionString);
        return new ResponseEntity<AlumnoSessionDTO>(session, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> encontrarAlumnos() {
        List<Alumno> alumnos = alumnoService.encontrarAlumnos();
        return new ResponseEntity<List<Alumno>>(alumnos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> encontrarAlumnoPorId(@PathVariable int id) throws EntityNotFoundException{
        Alumno alumno = this.alumnoService.encontrarAlumnoPorId(id);
        return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable int id, @Valid @RequestBody AlumnoUpdateDTO alumnoUpdateDTO) {
        Alumno alumno = alumnoService.actualizarAlumno(id, alumnoUpdateDTO);
        
        return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Alumno> eliminarAlumno(@PathVariable int id){
        Alumno alumno = alumnoService.encontrarAlumnoPorId(id);
        alumnoService.eliminarAlumno(id);
        return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    }
}
