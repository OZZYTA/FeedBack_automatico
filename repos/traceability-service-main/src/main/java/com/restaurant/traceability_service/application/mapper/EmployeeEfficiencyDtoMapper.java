package com.restaurant.traceability_service.application.mapper;

import com.restaurant.traceability_service.application.dto.EmployeeEfficiencyDtoResponse;
import com.restaurant.traceability_service.domain.model.EmployeeEfficiency;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel ="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeeEfficiencyDtoMapper {

    List<EmployeeEfficiencyDtoResponse> toEmployeeEfficiencyDtoResponseList(List<EmployeeEfficiency> employeeEfficiencyList);
}
