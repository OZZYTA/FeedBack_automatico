package com.restaurant.traceability_service.domain.usecase;

import com.restaurant.traceability_service.domain.api.ITraceabilityServicePort;
import com.restaurant.traceability_service.domain.model.EmployeeEfficiency;
import com.restaurant.traceability_service.domain.model.OrderEfficiency;
import com.restaurant.traceability_service.domain.model.StateUpdate;
import com.restaurant.traceability_service.domain.model.Traceability;
import com.restaurant.traceability_service.domain.spi.ITraceabilityPersistencePort;
import com.restaurant.traceability_service.utils.Constants;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TraceabilityUseCase implements ITraceabilityServicePort {

    private final ITraceabilityPersistencePort traceabilityPersistencePort;

    public TraceabilityUseCase(ITraceabilityPersistencePort traceabilityPersistencePort) {
        this.traceabilityPersistencePort = traceabilityPersistencePort;
    }

    @Override
    public void creteOrderTraceability(Traceability traceability) {

        if(traceabilityPersistencePort.existsByOrderId(traceability.getOrderId())) {
            traceabilityPersistencePort.updateChefInformation(traceability);
            return;
        }

        traceability.setInitialDate(LocalDateTime.now());

        traceabilityPersistencePort.creteOrderTraceability(traceability);
    }

    @Override
    public void stateUpdate(StateUpdate stateUpdate) {

        if(stateUpdate.getNextState().equals(Constants.CANCELED)) {
            traceabilityPersistencePort.orderCanceled(stateUpdate.getOrderId());
        }

        if (stateUpdate.getNextState().equals(Constants.DELIVERED)){
            traceabilityPersistencePort.orderDelivered(stateUpdate.getOrderId());
        }

        traceabilityPersistencePort.updateState(stateUpdate);
    }

    @Override
    public List<Traceability> getClientLog(Long clientId) {
        return traceabilityPersistencePort.getClientLog(clientId);
    }

    @Override
    public List<OrderEfficiency> getOrderEfficiency() {

        List<Traceability> traceabilities = traceabilityPersistencePort.getAll();
        List<OrderEfficiency> orderEfficiencies = new ArrayList<>();

        Duration duration;
        for (Traceability traceability : traceabilities) {
            duration = Duration.between(traceability.getInitialDate(), traceability.getEndDate());
            OrderEfficiency orderEfficiency =new OrderEfficiency(traceability.getOrderId(),duration.toMinutes());
            orderEfficiencies.add(orderEfficiency);
        }

        return orderEfficiencies;
    }

    @Override
    public List<EmployeeEfficiency> getEmployeeEfficiency() {
        List<Traceability> traceabilities = traceabilityPersistencePort.getAll();

        Map<Long, List<Traceability>> employeeTraceabilities = traceabilities.stream()
                .collect(Collectors.groupingBy(Traceability::getEmployeeId));

        List<EmployeeEfficiency> employeeEfficiencyList = new ArrayList<>();

        for (Map.Entry<Long, List<Traceability>> entry : employeeTraceabilities.entrySet()) {
            Long employeeId = entry.getKey();
            List<Traceability> employeeTraceabilityList = entry.getValue();

            employeeEfficiencyList.add(calculateEmployeeEfficiency(employeeId,employeeTraceabilityList));
        }

        return employeeEfficiencyList.stream()
                .sorted(Comparator.comparingDouble(EmployeeEfficiency::getAverageTimeInMinutes))
                .toList();
    }

    private EmployeeEfficiency calculateEmployeeEfficiency(Long employeeId,List<Traceability> employeeOrdersList){

        double totalTime = employeeOrdersList.stream()
                .mapToDouble(traceability -> Duration.between(traceability.getInitialDate(), traceability.getEndDate()).toMinutes())
                .sum();

        double averageTime = totalTime / employeeOrdersList.size();

        return new EmployeeEfficiency(
                employeeId,
                employeeOrdersList.get(0).getEmployeeEmail(),
                averageTime,
                employeeOrdersList.size()
        );
    }
}
