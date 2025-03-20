package com.restaurant.messaging_service.domain.spi;

public interface IMessagingPersistencePort {
    void notifyClient(String phoneNumber, String code);
}
