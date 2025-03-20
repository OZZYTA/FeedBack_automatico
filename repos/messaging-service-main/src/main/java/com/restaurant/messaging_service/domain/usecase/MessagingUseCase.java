package com.restaurant.messaging_service.domain.usecase;

import com.restaurant.messaging_service.domain.api.IMessagingServicePort;
import com.restaurant.messaging_service.domain.exceptions.InvalidCodeException;
import com.restaurant.messaging_service.domain.exceptions.OrderIsNotReadyException;
import com.restaurant.messaging_service.domain.model.NotifyClient;
import com.restaurant.messaging_service.domain.spi.ICodePersistencePort;
import com.restaurant.messaging_service.domain.spi.IMessagingPersistencePort;
import com.restaurant.messaging_service.domain.utils.CodeGenerator;

public class MessagingUseCase implements IMessagingServicePort {

    private final ICodePersistencePort codeServicePort;
    private final IMessagingPersistencePort messagingPersistencePort;

    public MessagingUseCase(ICodePersistencePort codeServicePort, IMessagingPersistencePort messagingPersistencePort) {
        this.codeServicePort = codeServicePort;
        this.messagingPersistencePort = messagingPersistencePort;
    }

    @Override
    public void notifyClient(NotifyClient notifyClient) {

        String securityCode = CodeGenerator.generateCode();
        messagingPersistencePort.notifyClient(notifyClient.getPhoneNumber(),securityCode);
        codeServicePort.saveCode(notifyClient.getOrderId(),securityCode);
    }

    @Override
    public void verifyCode(Long orderId, String code) {

        if(!codeServicePort.orderIsReady(orderId)) {
            throw new OrderIsNotReadyException();
        }

        if(!codeServicePort.isCodeValid(orderId,code)) {
            throw new InvalidCodeException();
        }
    }
}
