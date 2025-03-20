package com.restaurant.traceability_service.infrastructure.output.mongo.mapper;

import com.restaurant.traceability_service.domain.model.StateUpdate;
import com.restaurant.traceability_service.infrastructure.output.mongo.entity.StateChangeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StateChangeEntityMapper {
    @Mapping(source = "newState", target = "nextState")
    @Mapping(source = "previousState", target = "currentState")
    StateUpdate stateUpdate(StateChangeEntity stateChangeEntity);

    List<StateUpdate> toStateUpdateList(List<StateChangeEntity> stateChangeEntityList);
}
