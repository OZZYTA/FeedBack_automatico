package com.restaurant.messaging_service.application.handler;

import com.restaurant.messaging_service.application.dto.NotifyClientRequestDto;
import com.restaurant.messaging_service.application.mapper.NotifyClientRequestMapper;
import com.restaurant.messaging_service.domain.api.IMessagingServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessagingHandler implements IMessagingHandler{

    private final IMessagingServicePort messagingServicePort;
    private final NotifyClientRequestMapper notifyClientRequestMapper;

    @Override
    public void notifyClient(NotifyClientRequestDto notifyClientRequestDto) {

        messagingServicePort.notifyClient(notifyClientRequestMapper.toNotifyClient(notifyClientRequestDto));
    }

    @Override
    public void verifyCode(Long orderId, String code) {
        messagingServicePort.verifyCode(orderId,code);
    }
}
