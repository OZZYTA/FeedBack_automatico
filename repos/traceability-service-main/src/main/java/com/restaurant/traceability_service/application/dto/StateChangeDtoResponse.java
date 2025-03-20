package com.restaurant.traceability_service.application.dto;

import java.time.LocalDateTime;

public class StateChangeDtoResponse {
    private String currentState;
    private String nextState;
    private LocalDateTime date;

    public StateChangeDtoResponse(String currentState, String nextState, LocalDateTime date) {
        this.currentState = currentState;
        this.nextState = nextState;
        this.date = date;
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
