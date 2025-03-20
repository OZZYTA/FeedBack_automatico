package com.restaurant.traceability_service.domain.usecase;

import com.restaurant.traceability_service.domain.model.EmployeeEfficiency;
import com.restaurant.traceability_service.domain.model.OrderEfficiency;
import com.restaurant.traceability_service.domain.model.StateUpdate;
import com.restaurant.traceability_service.domain.model.Traceability;
import com.restaurant.traceability_service.domain.spi.ITraceabilityPersistencePort;
import com.restaurant.traceability_service.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TraceabilityUseCaseTest {

    @Mock
    private ITraceabilityPersistencePort traceabilityPersistencePort;

    @InjectMocks
    private TraceabilityUseCase traceabilityUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void creteOrderTraceability_WhenOrderExists_ShouldUpdateChefInformation() {
        Traceability traceability = new Traceability();
        traceability.setOrderId(1L);

        when(traceabilityPersistencePort.existsByOrderId(traceability.getOrderId())).thenReturn(true);

        traceabilityUseCase.creteOrderTraceability(traceability);

        verify(traceabilityPersistencePort, times(1)).updateChefInformation(traceability);
        verify(traceabilityPersistencePort, never()).creteOrderTraceability(any());
    }

    @Test
    void creteOrderTraceability_WhenOrderDoesNotExist_ShouldCreateNewTraceability() {
        Traceability traceability = new Traceability();
        traceability.setOrderId(2L);

        when(traceabilityPersistencePort.existsByOrderId(traceability.getOrderId())).thenReturn(false);

        traceabilityUseCase.creteOrderTraceability(traceability);

        verify(traceabilityPersistencePort, times(1)).creteOrderTraceability(traceability);
    }

    @Test
    void stateUpdate_WhenNextStateIsCanceled_ShouldCallOrderCanceled() {
        StateUpdate stateUpdate = new StateUpdate();
        stateUpdate.setNextState(Constants.CANCELED);
        stateUpdate.setOrderId(1L);

        traceabilityUseCase.stateUpdate(stateUpdate);

        verify(traceabilityPersistencePort, times(1)).orderCanceled(stateUpdate.getOrderId());
        verify(traceabilityPersistencePort, times(1)).updateState(stateUpdate);
    }

    @Test
    void stateUpdate_WhenNextStateIsDelivered_ShouldCallOrderDelivered() {
        StateUpdate stateUpdate = new StateUpdate();
        stateUpdate.setNextState(Constants.DELIVERED);
        stateUpdate.setOrderId(2L);

        traceabilityUseCase.stateUpdate(stateUpdate);

        verify(traceabilityPersistencePort, times(1)).orderDelivered(stateUpdate.getOrderId());
        verify(traceabilityPersistencePort, times(1)).updateState(stateUpdate);
    }

    @Test
    void stateUpdate_WhenNextStateIsNotSpecial_ShouldOnlyUpdateState() {
        StateUpdate stateUpdate = new StateUpdate();
        stateUpdate.setNextState("In Preparation");
        stateUpdate.setOrderId(3L);

        traceabilityUseCase.stateUpdate(stateUpdate);

        verify(traceabilityPersistencePort, never()).orderCanceled(anyLong());
        verify(traceabilityPersistencePort, never()).orderDelivered(anyLong());
        verify(traceabilityPersistencePort, times(1)).updateState(stateUpdate);
    }

    @Test
    void getClientLog_ShouldReturnTraceabilityList() {

        Long clientId = 1L;
        List<Traceability> expectedLogs = Arrays.asList(new Traceability(), new Traceability());

        when(traceabilityPersistencePort.getClientLog(clientId)).thenReturn(expectedLogs);
        List<Traceability> result = traceabilityUseCase.getClientLog(clientId);

        assertEquals(expectedLogs, result);
        verify(traceabilityPersistencePort, times(1)).getClientLog(clientId);
    }

    @Test
    void getOrderEfficiency_ShouldReturnListOfOrderEfficiencies() {

        Traceability traceability1 = new Traceability();
        traceability1.setOrderId(17L);
        traceability1.setInitialDate(java.time.LocalDateTime.now().minusMinutes(955));
        traceability1.setEndDate(java.time.LocalDateTime.now());

        Traceability traceability2 = new Traceability();
        traceability2.setOrderId(18L);
        traceability2.setInitialDate(java.time.LocalDateTime.now().minusMinutes(0));
        traceability2.setEndDate(java.time.LocalDateTime.now());

        when(traceabilityPersistencePort.getAll()).thenReturn(Arrays.asList(traceability1, traceability2));

        List<OrderEfficiency> result = traceabilityUseCase.getOrderEfficiency();

        assertEquals(2, result.size());

        assertEquals(955, result.get(0).getTimeInMinutes());
        assertEquals(0, result.get(1).getTimeInMinutes());

        verify(traceabilityPersistencePort, times(1)).getAll();
    }

    @Test
    void getEmployeeEfficiency_ShouldReturnSortedEmployeeEfficiencyList() {
        Traceability traceability1 = new Traceability();
        traceability1.setEmployeeId(1L);
        traceability1.setEmployeeEmail("empleado1@ejemplo.com");
        traceability1.setInitialDate(LocalDateTime.now().minusMinutes(120));
        traceability1.setEndDate(LocalDateTime.now());

        Traceability traceability2 = new Traceability();
        traceability2.setEmployeeId(1L);
        traceability2.setEmployeeEmail("empleado1@ejemplo.com");
        traceability2.setInitialDate(LocalDateTime.now().minusMinutes(100));
        traceability2.setEndDate(LocalDateTime.now());

        Traceability traceability3 = new Traceability();
        traceability3.setEmployeeId(2L);
        traceability3.setEmployeeEmail("empleado2@ejemplo.com");
        traceability3.setInitialDate(LocalDateTime.now().minusMinutes(80));
        traceability3.setEndDate(LocalDateTime.now());

        List<Traceability> traceabilities = Arrays.asList(traceability1, traceability2, traceability3);

        when(traceabilityPersistencePort.getAll()).thenReturn(traceabilities);

        List<EmployeeEfficiency> result = traceabilityUseCase.getEmployeeEfficiency();

        assertEquals(2, result.size());

        assertTrue(result.get(0).getAverageTimeInMinutes() <= result.get(1).getAverageTimeInMinutes());

        EmployeeEfficiency employee1 = result.get(0);
        assertEquals(2L, employee1.getEmployeeId());
        assertEquals("empleado2@ejemplo.com", employee1.getEmployeeEmail());
        assertEquals(80, employee1.getAverageTimeInMinutes(), 0.1);

        EmployeeEfficiency employee2 = result.get(1);
        assertEquals(1L, employee2.getEmployeeId());
        assertEquals("empleado1@ejemplo.com", employee2.getEmployeeEmail());
        assertEquals(110, employee2.getAverageTimeInMinutes(), 0.1);
    }

    @Test
    void getEmployeeEfficiency_WhenNoTraceabilitiesExist_ShouldReturnEmptyList() {
        when(traceabilityPersistencePort.getAll()).thenReturn(Arrays.asList());
        List<EmployeeEfficiency> result = traceabilityUseCase.getEmployeeEfficiency();
        assertTrue(result.isEmpty());
    }


}