package com.restaurant.traceability_service.infrastructure.output.mongo.repository;

import com.restaurant.traceability_service.infrastructure.output.mongo.entity.TraceabilityEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TraceabilityRepository extends MongoRepository<TraceabilityEntity,String> {
    Optional<TraceabilityEntity> findByOrderId(Long orderId);
    boolean existsByOrderId(Long orderId);
    List<TraceabilityEntity> findByClientId(String clientId);
}
