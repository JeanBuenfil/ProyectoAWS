package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.DTO.body.ProfesorBodyDTO;
import com.example.DTO.update.ProfesorUpdateDTO;
import com.example.model.Profesor;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfesorService {
    
    private final List<Profesor> profesores = new ArrayList<>();

    public Profesor crearProfesor(ProfesorBodyDTO profesorDTO){
        Profesor profesor = crearProfesorDesdeDTO(profesorDTO);
        profesores.add(profesor);
        return profesor;
    }

    private Profesor crearProfesorDesdeDTO(ProfesorBodyDTO profesorDTO){
        return new Profesor(
            profesorDTO.getId(),
            profesorDTO.getNumeroEmpleado(),
            profesorDTO.getNombres(),
            profesorDTO.getApellidos(),
            profesorDTO.getHorasClase()
        );
    }

    public List<Profesor> encontrarProfesores(){
        return this.profesores;
    }

    public Profesor encontrarProfesorPorId(int id){
        Profesor profesor = this.encontrarId(id).orElse(null);
        return profesor;
    }

    private Optional<Profesor> encontrarId(int id){
        return profesores.stream()
        .filter(a -> a.getId() == id).findFirst();
    }

    public Profesor actualizarProfesor(int id, ProfesorUpdateDTO profesorUpdateDTO){
        Profesor profesor = encontrarProfesorPorId(id);
        this.actualizarProfesorDesdeDTO(profesor, profesorUpdateDTO);
        return profesor;

    }

    private void actualizarProfesorDesdeDTO(Profesor profesor, ProfesorUpdateDTO profesorUpdateDTO){

        if(profesorUpdateDTO.getNumeroEmpleado() != null){
            profesor.setNumeroEmpleado(profesorUpdateDTO.getNumeroEmpleado());
        }

        if(profesorUpdateDTO.getNombres() != null){
            profesor.setNombres(profesorUpdateDTO.getNombres());
        }  

        if(profesorUpdateDTO.getApellidos() != null){
            profesor.setApellidos(profesorUpdateDTO.getApellidos());
        }  
        if(profesorUpdateDTO.getHorasClase() != null){
            profesor.setHorasClase(profesorUpdateDTO.getHorasClase());
        }          

    }

    public void eliminarProfesor(int id){
        Profesor profesor = this.encontrarId(id).orElseThrow(() -> new EntityNotFoundException());
        profesores.remove(profesor);
    }
}
