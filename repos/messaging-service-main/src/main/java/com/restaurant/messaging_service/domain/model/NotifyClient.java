package com.restaurant.messaging_service.domain.model;

public class NotifyClient {
    private String phoneNumber;
    private Long orderId;

    public NotifyClient(String phoneNumber, Long orderId) {
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
