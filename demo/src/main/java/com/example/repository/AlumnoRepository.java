package com.example.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{
}
