package com.restaurant.messaging_service.domain.usecase;

import com.restaurant.messaging_service.domain.model.NotifyClient;
import com.restaurant.messaging_service.domain.spi.ICodePersistencePort;
import com.restaurant.messaging_service.domain.spi.IMessagingPersistencePort;
import com.restaurant.messaging_service.domain.utils.CodeGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


class MessagingUseCaseTest {

    @Mock
    private IMessagingPersistencePort messagingPersistencePort;

    @Mock
    private ICodePersistencePort codePersistencePort;

    @InjectMocks
    private MessagingUseCase messagingUseCase;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void notifyClient_ShouldSendNotificationAndSaveCode() {

        NotifyClient notifyClient = new NotifyClient("+573045721800",1L);


        String generatedCode = "1234";

        Mockito.mockStatic(CodeGenerator.class);
        Mockito.when(CodeGenerator.generateCode()).thenReturn(generatedCode);

        messagingUseCase.notifyClient(notifyClient);

        Mockito.verify(messagingPersistencePort, Mockito.times(1)).notifyClient(notifyClient.getPhoneNumber(), generatedCode);
        Mockito.verify(codePersistencePort, Mockito.times(1)).saveCode(notifyClient.getOrderId(), generatedCode);

    }
}