package com.restaurant.messaging_service.application.dto;

public class NotifyClientRequestDto {

    private String phoneNumber;
    private Long orderId;

    public NotifyClientRequestDto(String phoneNumber, Long orderId) {
        this.phoneNumber = phoneNumber;
        this.orderId = orderId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
