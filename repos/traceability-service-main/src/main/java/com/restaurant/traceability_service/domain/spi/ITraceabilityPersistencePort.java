package com.restaurant.traceability_service.domain.spi;

import com.restaurant.traceability_service.domain.model.StateUpdate;
import com.restaurant.traceability_service.domain.model.Traceability;

import java.util.List;

public interface ITraceabilityPersistencePort {
    void creteOrderTraceability(Traceability traceability);
    void orderCanceled(Long orderId);
    void orderDelivered(Long orderId);
    void updateState(StateUpdate stateUpdate);
    boolean existsByOrderId(Long orderId);
    void updateChefInformation(Traceability traceability);
    List<Traceability> getClientLog(Long clientId);
    List<Traceability> getAll();
}
