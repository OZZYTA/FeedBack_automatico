package com.restaurant.traceability_service.domain.model;

import java.time.LocalDateTime;

public class StateUpdate {
    private Long orderId;
    private String currentState;
    private String nextState;
    private LocalDateTime date;

    public StateUpdate(Long orderId, String currentState, String nextState, LocalDateTime date) {
        this.orderId = orderId;
        this.currentState = currentState;
        this.nextState = nextState;
        this.date = date;
    }

    public StateUpdate(){

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getNextState() {
        return nextState;
    }

    public void setNextState(String nextState) {
        this.nextState = nextState;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
