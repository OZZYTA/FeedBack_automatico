package com.restaurant.traceability_service.application.mapper;

import com.restaurant.traceability_service.application.dto.StateUpdateDto;
import com.restaurant.traceability_service.domain.model.StateUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StateUpdateDtoMapper {
    StateUpdate toStateUpdate(StateUpdateDto stateUpdateDto);
}
