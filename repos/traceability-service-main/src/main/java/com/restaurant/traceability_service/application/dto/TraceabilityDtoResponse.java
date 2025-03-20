package com.restaurant.traceability_service.application.dto;

import com.restaurant.traceability_service.domain.model.OrderCompletionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class TraceabilityDtoResponse {
    private Long orderId;
    private String clientId;
    private String clientEmail;
    private LocalDateTime initialDate;
    private LocalDateTime endDate;
    private OrderCompletionStatus completionStatus;
    private List<StateChangeDtoResponse> stateChanges;

    public TraceabilityDtoResponse(Long orderId, String clientId, String clientEmail, LocalDateTime initialDate, LocalDateTime endDate, OrderCompletionStatus completionStatus, List<StateChangeDtoResponse> stateChanges) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.clientEmail = clientEmail;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.completionStatus = completionStatus;
        this.stateChanges = stateChanges;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDateTime initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public OrderCompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(OrderCompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
    }

    public List<StateChangeDtoResponse> getStateChanges() {
        return stateChanges;
    }

    public void setStateChanges(List<StateChangeDtoResponse> stateChanges) {
        this.stateChanges = stateChanges;
    }
}
