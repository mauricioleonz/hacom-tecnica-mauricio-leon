package com.example.hacommauricioleon.Entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
//Entidad para tipo de dato a insertar
@Data
@Document(collection = "traceMsg")
public class TraceMsg {
    @Id
    private ObjectId _id;
    private String sessionId;
    private String payload;
    private OffsetDateTime ts;
}
