package com.restaurant.traceability_service.infrastructure.output.mongo.adapter;

import com.restaurant.traceability_service.domain.model.OrderCompletionStatus;
import com.restaurant.traceability_service.domain.model.StateUpdate;
import com.restaurant.traceability_service.domain.model.Traceability;
import com.restaurant.traceability_service.domain.spi.ITraceabilityPersistencePort;
import com.restaurant.traceability_service.infrastructure.output.mongo.entity.StateChangeEntity;
import com.restaurant.traceability_service.infrastructure.output.mongo.entity.TraceabilityEntity;
import com.restaurant.traceability_service.infrastructure.output.mongo.mapper.TraceabilityEntityMapper;
import com.restaurant.traceability_service.infrastructure.output.mongo.repository.TraceabilityRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@AllArgsConstructor
public class TraceabilityMongoAdapter implements ITraceabilityPersistencePort {

    private final TraceabilityEntityMapper traceabilityEntityMapper;
    private final TraceabilityRepository traceabilityRepository;

    @Override
    public void creteOrderTraceability(Traceability traceability) {
        TraceabilityEntity traceabilityEntity = traceabilityEntityMapper.toTraceabilityEntity(traceability);

        traceabilityRepository.save(traceabilityEntity);
    }

    @Override
    public void orderCanceled(Long orderId) {
        Optional<TraceabilityEntity> traceability = traceabilityRepository.findByOrderId(orderId);

        if (traceability.isPresent()){
            TraceabilityEntity traceabilityEntity = traceability.get();
            traceabilityEntity.setEndDate(LocalDateTime.now());
            traceabilityEntity.setCompletionStatus(OrderCompletionStatus.CANCELED);

            traceabilityRepository.save(traceabilityEntity);
        }

    }

    @Override
    public void orderDelivered(Long orderId) {
        Optional<TraceabilityEntity> traceability = traceabilityRepository.findByOrderId(orderId);

        if (traceability.isPresent()){
            TraceabilityEntity traceabilityEntity = traceability.get();
            traceabilityEntity.setEndDate(LocalDateTime.now());
            traceabilityEntity.setCompletionStatus(OrderCompletionStatus.DELIVERED);

            traceabilityRepository.save(traceabilityEntity);
        }
    }

    @Override
    public void updateState(StateUpdate stateUpdate) {
        Optional<TraceabilityEntity> traceability = traceabilityRepository.findByOrderId(stateUpdate.getOrderId());

        if (traceability.isPresent()) {
            TraceabilityEntity traceabilityEntity = traceability.get();
            StateChangeEntity stateChange = new StateChangeEntity(stateUpdate.getCurrentState(), stateUpdate.getNextState(), LocalDateTime.now());
            List<StateChangeEntity> stateChangeEntityList = (traceabilityEntity.getStateChanges() != null) ? traceabilityEntity.getStateChanges() : new ArrayList<>();
            stateChangeEntityList.add(stateChange);
            traceabilityEntity.setStateChanges(stateChangeEntityList);

            traceabilityRepository.save(traceabilityEntity);
        }

    }

    @Override
    public boolean existsByOrderId(Long orderId) {

        return traceabilityRepository.existsByOrderId(orderId);
    }

    @Override
    public void updateChefInformation(Traceability traceability) {
        Optional<TraceabilityEntity> optionalTraceability = traceabilityRepository.findByOrderId(traceability.getOrderId());

        if (optionalTraceability.isPresent()) {
            TraceabilityEntity traceabilityEntity = optionalTraceability.get();

            traceabilityEntity.setEmployeeId(traceability.getEmployeeId());
            traceabilityEntity.setEmployeeEmail(traceability.getEmployeeEmail());

            traceabilityRepository.save(traceabilityEntity);
        }
    }

    @Override
    public List<Traceability> getClientLog(Long clientId) {
        List<TraceabilityEntity> traceabilityEntities = traceabilityRepository.findByClientId(clientId.toString());
        return traceabilityEntityMapper.toTraceabilityList(traceabilityEntities);

    }

    @Override
    public List<Traceability> getAll() {
        List<TraceabilityEntity> traceabilityEntities = traceabilityRepository.findAll();
        return traceabilityEntityMapper.toTraceabilityList(traceabilityEntities);
    }
}
