package com.restaurant.traceability_service.application.handler;

import com.restaurant.traceability_service.application.dto.*;

import java.util.List;

public interface ITraceabilityHandler {
    void creteOrderTraceability(TraceabilityDtoRequest traceabilityDtoRequest);
    void updateOrderState(StateUpdateDto stateUpdateDto);
    List<TraceabilityDtoResponse> getClientLog(Long clientId);
    List<OrderEfficiencyDtoResponse> getOrderEfficiency();
    List<EmployeeEfficiencyDtoResponse> getEmployeeEfficiency();
}
