package com.example.hacommauricioleon.Entities;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
//Entidad para rango de fechas a buscar
@Data
public class DateRangeRequest {
    @NotNull
    private OffsetDateTime from;
    @NotNull
    private OffsetDateTime to;
}