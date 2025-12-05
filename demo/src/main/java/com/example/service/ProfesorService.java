package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.body.ProfesorBodyDTO;
import com.example.DTO.update.ProfesorUpdateDTO;
import com.example.model.Profesor;
import com.example.repository.ProfesorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfesorService {
    
    @Autowired
    ProfesorRepository profesorRepository;

    public Profesor crearProfesor(ProfesorBodyDTO profesorDTO){
        Profesor profesor = Profesor.builder()
        .numeroEmpleado(profesorDTO.getNumeroEmpleado())
        .nombres(profesorDTO.getNombres())
        .apellidos(profesorDTO.getApellidos())
        .horasClase(profesorDTO.getHorasClase())
        .build();
        return this.profesorRepository.save(profesor);
    }



    public List<Profesor> encontrarProfesores(){
        return this.profesorRepository.findAll();
    }

    public Profesor encontrarProfesorPorId(int id){
        return this.profesorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }



    public Profesor actualizarProfesor(int id, ProfesorUpdateDTO profesorUpdateDTO){
        Profesor profesor = encontrarProfesorPorId(id);
        this.actualizarProfesorDesdeDTO(profesor, profesorUpdateDTO);
        this.profesorRepository.save(profesor);
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
        this.profesorRepository.deleteById(id);;
    }
}
