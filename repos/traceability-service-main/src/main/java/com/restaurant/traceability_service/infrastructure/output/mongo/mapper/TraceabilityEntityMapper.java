package com.restaurant.traceability_service.infrastructure.output.mongo.mapper;

import com.restaurant.traceability_service.domain.model.Traceability;
import com.restaurant.traceability_service.infrastructure.output.mongo.entity.TraceabilityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {StateChangeEntityMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TraceabilityEntityMapper {
    TraceabilityEntity toTraceabilityEntity(Traceability traceability);
    List<Traceability> toTraceabilityList(List<TraceabilityEntity> traceabilityEntities);
}
