package com.restaurant.traceability_service.application.mapper;

import com.restaurant.traceability_service.application.dto.TraceabilityDtoResponse;
import com.restaurant.traceability_service.domain.model.Traceability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TraceabilityDtoResponseMapper {
    List<TraceabilityDtoResponse> toTraceabilityDtoResponseList(List<Traceability> traceability);

}
