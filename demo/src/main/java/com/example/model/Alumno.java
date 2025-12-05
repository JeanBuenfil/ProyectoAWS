package com.example.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alumnos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contraseña", nullable = false)
    private String password;

    @Column(name = "nombres", nullable = false)
    private String nombres;


    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "matricula", nullable = false)
    private String matricula;


    @Column(name = "promedio", nullable = false)
    private Double promedio;

    @Column(name = "foto")
    private String fotoPerfilUrl;
}
