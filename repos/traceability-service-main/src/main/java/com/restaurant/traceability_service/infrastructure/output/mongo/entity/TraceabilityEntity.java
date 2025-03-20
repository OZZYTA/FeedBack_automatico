package com.restaurant.traceability_service.infrastructure.output.mongo.entity;

import com.restaurant.traceability_service.domain.model.OrderCompletionStatus;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "traceability")
@Data
public class TraceabilityEntity {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private Long orderId;
    private String clientId;
    private String clientEmail;
    private LocalDateTime initialDate;
    private LocalDateTime endDate;
    private Long employeeId;
    private String employeeEmail;
    private OrderCompletionStatus completionStatus;
    private List<StateChangeEntity> stateChanges;
}