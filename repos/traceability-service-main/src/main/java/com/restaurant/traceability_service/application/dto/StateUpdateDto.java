package com.restaurant.traceability_service.application.dto;

public class StateUpdateDto {
    private Long orderId;
    private String currentState;
    private String nextState;

    public StateUpdateDto(Long orderId, String currentState, String nextState) {
        this.orderId = orderId;
        this.currentState = currentState;
        this.nextState = nextState;
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
}
