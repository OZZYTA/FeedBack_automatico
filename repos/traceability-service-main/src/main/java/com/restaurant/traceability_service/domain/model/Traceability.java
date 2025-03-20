package com.restaurant.traceability_service.domain.model;


import java.time.LocalDateTime;
import java.util.List;

public class Traceability {
    private Long orderId;
    private String clientId;
    private String clientEmail;
    private LocalDateTime initialDate;
    private LocalDateTime endDate;
    private Long employeeId;
    private String employeeEmail;
    private List<StateUpdate> stateChanges;

    public Traceability(Long orderId, String clientId, String clientEmail, LocalDateTime initialDate, LocalDateTime endDate, Long employeeId, String employeeEmail, List<StateUpdate> stateChanges) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.clientEmail = clientEmail;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.employeeId = employeeId;
        this.employeeEmail = employeeEmail;
        this.stateChanges = stateChanges;
    }

    public Traceability(){

    }

    public List<StateUpdate> getStateChanges() {
        return stateChanges;
    }

    public void setStateChanges(List<StateUpdate> stateChanges) {
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
}
