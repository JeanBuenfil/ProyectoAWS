package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.DTO.body.AlumnoBodyDTO;
import com.example.DTO.update.AlumnoUpdateDTO;
import com.example.model.Alumno;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AlumnoService {

    private final List<Alumno> alumnos = new ArrayList<>();

    public Alumno crearAlumno(AlumnoBodyDTO alumnoDTO){
        Alumno alumno = crearAlumnoDesdeDTO(alumnoDTO);
        alumnos.add(alumno);
        return alumno;
    }

    private Alumno crearAlumnoDesdeDTO(AlumnoBodyDTO alumnoDTO){
        return new Alumno(
            alumnoDTO.getId(),
            alumnoDTO.getNombres(),
            alumnoDTO.getApellidos(),
            alumnoDTO.getMatricula(),
            alumnoDTO.getPromedio()
        );
    }

    public List<Alumno> encontrarAlumnos(){
        return this.alumnos;
    }

    public Alumno encontrarAlumnoPorId(int id){
        Alumno alumno = this.encontrarId(id).orElse(null);
        return alumno;
    }

    private Optional<Alumno> encontrarId(int id){
        return alumnos.stream()
        .filter(a -> a.getId() == id).findFirst();
    }

    public Alumno actualizarAlumno(int id, AlumnoUpdateDTO alumnoUpdateDTO){
        Alumno alumno = encontrarAlumnoPorId(id);
        this.actualizarAlumnoDesdeDTO(alumno, alumnoUpdateDTO);
        return alumno;

    }

    private void actualizarAlumnoDesdeDTO(Alumno alumno, AlumnoUpdateDTO alumnoUpdateDTO){
        if(alumnoUpdateDTO.getNombres() != null){
            alumno.setNombres(alumnoUpdateDTO.getNombres());
        }  

        if(alumnoUpdateDTO.getApellidos() != null){
            alumno.setApellidos(alumnoUpdateDTO.getApellidos());
        }  
        if(alumnoUpdateDTO.getMatricula() != null){
            alumno.setMatricula(alumnoUpdateDTO.getMatricula());
        }  
        if(alumnoUpdateDTO.getPromedio() != null ){
            alumno.setPromedio(alumnoUpdateDTO.getPromedio());
        }  
        

    }

    public void eliminarAlumno(int id){
        Alumno alumno = this.encontrarId(id).orElseThrow(() -> new EntityNotFoundException());
        alumnos.remove(alumno);
    }
    
}
