package com.restaurant.traceability_service.application.dto;


public class OrderEfficiencyDtoResponse {
    private Long orderId;
    private Long timeInMinutes;

    public OrderEfficiencyDtoResponse(Long orderId, Long timeInMinutes) {
        this.orderId = orderId;
        this.timeInMinutes = timeInMinutes;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(Long timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }
}
