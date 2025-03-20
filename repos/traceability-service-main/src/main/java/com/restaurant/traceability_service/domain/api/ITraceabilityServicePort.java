package com.restaurant.traceability_service.domain.api;

import com.restaurant.traceability_service.domain.model.EmployeeEfficiency;
import com.restaurant.traceability_service.domain.model.OrderEfficiency;
import com.restaurant.traceability_service.domain.model.StateUpdate;
import com.restaurant.traceability_service.domain.model.Traceability;

import java.util.List;

public interface ITraceabilityServicePort {
    void creteOrderTraceability(Traceability traceability);
    void stateUpdate(StateUpdate stateUpdate);
    List<Traceability> getClientLog(Long clientId);
    List<OrderEfficiency> getOrderEfficiency();
    List<EmployeeEfficiency> getEmployeeEfficiency();
}
