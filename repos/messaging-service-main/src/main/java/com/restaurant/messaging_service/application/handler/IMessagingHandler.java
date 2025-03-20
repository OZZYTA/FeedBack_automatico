package com.restaurant.messaging_service.application.handler;

import com.restaurant.messaging_service.application.dto.NotifyClientRequestDto;

public interface IMessagingHandler {
    void notifyClient(NotifyClientRequestDto notifyClientRequestDto);

    void verifyCode(Long orderId, String code);
}
