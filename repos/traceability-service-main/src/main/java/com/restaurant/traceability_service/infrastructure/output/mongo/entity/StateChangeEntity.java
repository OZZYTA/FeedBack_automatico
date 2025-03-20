package com.restaurant.traceability_service.infrastructure.output.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateChangeEntity {
    private String previousState;
    private String newState;
    private LocalDateTime date;
}
