package com.example.service;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.DTO.body.AlumnoBodyDTO;
import com.example.DTO.update.AlumnoUpdateDTO;
import com.example.model.Alumno;
import com.example.repository.AlumnoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private SnsService snsService;


    public Alumno crearAlumno(AlumnoBodyDTO alumnoDTO){
        Alumno alumno = Alumno.builder()
        .nombres(alumnoDTO.getNombres())
        .apellidos(alumnoDTO.getApellidos())
        .password(alumnoDTO.getPassword())
        .matricula(alumnoDTO.getMatricula())
        .promedio(alumnoDTO.getPromedio())
        .build();
        return this.alumnoRepository.save(alumno);
    }

    public List<Alumno> encontrarAlumnos(){
        return this.alumnoRepository.findAll();
    }

    public Alumno encontrarAlumnoPorId(int id){
        return this.alumnoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    public Alumno actualizarAlumno(int id, AlumnoUpdateDTO alumnoUpdateDTO){
        Alumno alumnoToUpdate = encontrarAlumnoPorId(id);
        if(alumnoUpdateDTO.getNombres()!=null){
            alumnoToUpdate.setNombres(alumnoUpdateDTO.getNombres());
        }
        if(alumnoUpdateDTO.getApellidos()!=null){
            alumnoToUpdate.setApellidos(alumnoUpdateDTO.getApellidos());
        }
        if(alumnoUpdateDTO.getMatricula()!=null){
            alumnoToUpdate.setMatricula(alumnoUpdateDTO.getMatricula());
        }
        if(alumnoUpdateDTO.getPromedio()!=null){
            alumnoToUpdate.setPromedio(alumnoUpdateDTO.getPromedio());
        }
        return this.alumnoRepository.save(alumnoToUpdate);

    }

    public String agregarFotoPerfil(int id, MultipartFile fotoPerfil) throws IOException{
        Alumno alumno = encontrarAlumnoPorId(id);
        String fotoPerfilUrl = s3Service.uploadFile(id, fotoPerfil);
        alumno.setFotoPerfilUrl(fotoPerfilUrl);
        this.alumnoRepository.save(alumno);
        return fotoPerfilUrl;
    }


    public void eliminarAlumno(int id){
        this.alumnoRepository.deleteById(id);
    }

public void enviarEmail(Alumno alumno) {
    String subject = "Información de alumno";

    String mensajeHtml = """
            <html>
                <body style="font-family: Arial, sans-serif;">

                    <h3>Detalles del alumno:</h3>
                    <p><strong>Nombres:</strong> %s</p>
                    <p><strong>Apellidos:</strong> %s</p>
                    <p><strong>Promedio:</strong> %s</p>

                </body>
            </html>
            """.formatted(alumno.getNombres(), alumno.getApellidos(), alumno.getPromedio());

    String jsonMessage = """
            {
              "default": "Detalles del alumno",
              "email": "%s"
            }
            """.formatted(mensajeHtml.replace("\"", "\\\""));

    snsService.publishMessage(subject, jsonMessage);
    }

}
    

