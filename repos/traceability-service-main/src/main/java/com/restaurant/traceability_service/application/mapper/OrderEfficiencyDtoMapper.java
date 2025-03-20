package com.restaurant.traceability_service.application.mapper;

import com.restaurant.traceability_service.application.dto.OrderEfficiencyDtoResponse;
import com.restaurant.traceability_service.domain.model.OrderEfficiency;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderEfficiencyDtoMapper {
    List<OrderEfficiencyDtoResponse> toOrderEfficiencyDtoResponse(List<OrderEfficiency> orderEfficiencyList);
}
