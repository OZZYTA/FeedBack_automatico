package com.restaurant.traceability_service.application.handler;


import com.restaurant.traceability_service.application.dto.*;
import com.restaurant.traceability_service.application.mapper.*;
import com.restaurant.traceability_service.domain.api.ITraceabilityServicePort;
import com.restaurant.traceability_service.domain.model.EmployeeEfficiency;
import com.restaurant.traceability_service.domain.model.OrderEfficiency;
import com.restaurant.traceability_service.domain.model.StateUpdate;
import com.restaurant.traceability_service.domain.model.Traceability;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TraceabilityHandler implements ITraceabilityHandler{

    private final ITraceabilityServicePort traceabilityServicePort;
    private final TraceabilityDtoMapper traceabilityDtoMapper;
    private final StateUpdateDtoMapper stateUpdateDtoMapper;
    private final TraceabilityDtoResponseMapper traceabilityDtoResponseMapper;
    private final OrderEfficiencyDtoMapper orderEfficiencyDtoMapper;
    private final EmployeeEfficiencyDtoMapper employeeEfficiencyDtoMapper;

    @Override
    public void creteOrderTraceability(TraceabilityDtoRequest traceabilityRequestDto) {

        Traceability traceability = traceabilityDtoMapper.toTraceability(traceabilityRequestDto);
        traceabilityServicePort.creteOrderTraceability(traceability);
    }

    @Override
    public void updateOrderState(StateUpdateDto stateUpdateDto) {
        StateUpdate stateUpdate =stateUpdateDtoMapper.toStateUpdate(stateUpdateDto);
        traceabilityServicePort.stateUpdate(stateUpdate);
    }

    @Override
    public List<TraceabilityDtoResponse> getClientLog(Long clientId) {
        List<Traceability> traceability = traceabilityServicePort.getClientLog(clientId);
        return traceabilityDtoResponseMapper.toTraceabilityDtoResponseList(traceability);
    }

    @Override
    public List<OrderEfficiencyDtoResponse> getOrderEfficiency() {
        List<OrderEfficiency> orderEfficiencyList = traceabilityServicePort.getOrderEfficiency();
        return orderEfficiencyDtoMapper.toOrderEfficiencyDtoResponse(orderEfficiencyList);
    }

    @Override
    public List<EmployeeEfficiencyDtoResponse> getEmployeeEfficiency() {
        List<EmployeeEfficiency> employeeEfficiencies = traceabilityServicePort.getEmployeeEfficiency();
        return employeeEfficiencyDtoMapper.toEmployeeEfficiencyDtoResponseList(employeeEfficiencies);

    }
}
