package com.restaurant.traceability_service.application.mapper;

import com.restaurant.traceability_service.application.dto.TraceabilityDtoRequest;
import com.restaurant.traceability_service.domain.model.Traceability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TraceabilityDtoMapper {
    Traceability toTraceability(TraceabilityDtoRequest traceabilityDtoRequest);

}
