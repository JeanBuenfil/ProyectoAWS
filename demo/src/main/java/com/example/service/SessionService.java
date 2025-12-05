package com.example.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import org.springframework.stereotype.Service;

import com.example.DTO.session.AlumnoSessionDTO;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

@Service
public class SessionService {
    private DynamoDbClient dynamoDbClient;
    private String tabla = "sesiones-alumnos";
    private SecureRandom secureRandom = new SecureRandom();

    public SessionService(DynamoDbClient dynamoDbClient){
        this.dynamoDbClient = dynamoDbClient;
    }

    private String generateSessionString(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stringBuilder = new StringBuilder(128);
        for (int i = 0; i < 128; i++) {
            stringBuilder.append(chars.charAt(secureRandom.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }

    public AlumnoSessionDTO crearSesion(Integer id){
        String uuid = UUID.randomUUID().toString();
        long timeStamp = System.currentTimeMillis()/1000;
        String sessionString = generateSessionString();

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(uuid).build());
        item.put("fecha", AttributeValue.builder().n(String.valueOf(timeStamp)).build());
        item.put("alumnoId", AttributeValue.builder().n(String.valueOf(id)).build());
        item.put("active", AttributeValue.builder().bool(true).build());
        item.put("sessionString", AttributeValue.builder().s(sessionString).build());

        PutItemRequest request = PutItemRequest.builder()
        .tableName(tabla)
        .item(item)
        .build();

        dynamoDbClient.putItem(request);

        AlumnoSessionDTO session = new AlumnoSessionDTO();
        session.setId(uuid);
        session.setFecha(timeStamp);
        session.setAlumnoId(id);
        session.setActive(true);
        session.setSessionString(sessionString);

        return session;
    }


    public AlumnoSessionDTO verificarSesion(String sessionString, int id){
        AlumnoSessionDTO session = encontrarSesionporSessionString(sessionString);
        if (session == null) {
       throw new IllegalArgumentException(("Sesión no encontrada"));
    }

    if (!session.getAlumnoId().equals(id)) {
        throw new IllegalArgumentException(("Alumno incorrecto"));
    }

    if (!session.getActive()) {
        throw new IllegalArgumentException("Sesión expirada o cerrada");
    }
        return session;
    }

    private AlumnoSessionDTO encontrarSesionporSessionString(String sessionString){

        ScanRequest scan = ScanRequest.builder()
        .tableName(tabla)
        .filterExpression("sessionString = :ss")
        .expressionAttributeValues(Map.of(":ss", AttributeValue.builder().s(sessionString).build()))
        .build();

        ScanResponse scanResponse = dynamoDbClient.scan(scan);

        if(scanResponse.count() == 0) return null;
         Map<String, AttributeValue> item = scanResponse.items().get(0);

        AlumnoSessionDTO session = new AlumnoSessionDTO();
        session.setId(item.get("id").s());
        session.setFecha(Long.parseLong(item.get("fecha").n()));
        session.setAlumnoId(Integer.parseInt(item.get("alumnoId").n()));
        session.setActive(item.get("active").bool());
        session.setSessionString(item.get("sessionString").s());

        return session;
    }

    public void terminarSesion(String sessionString){
        AlumnoSessionDTO session = encontrarSesionporSessionString(sessionString);
        if (session == null) {
            throw new IllegalArgumentException("Sesión no encontrada");
        }

    
        UpdateItemRequest updateRequest = UpdateItemRequest.builder()
        .tableName(tabla)
        .key(Map.of("id", AttributeValue.builder().s(session.getId()).build()))
        .updateExpression("SET active = :a")
        .expressionAttributeValues(Map.of(
                ":a", AttributeValue.builder().bool(false).build()
        ))
        .build();

        dynamoDbClient.updateItem(updateRequest);
    }
}
