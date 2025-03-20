package com.restaurant.messaging_service.domain.api;

import com.restaurant.messaging_service.domain.model.NotifyClient;

public interface IMessagingServicePort {
    void notifyClient(NotifyClient notifyClient);

    void verifyCode(Long orderId, String code);
}
