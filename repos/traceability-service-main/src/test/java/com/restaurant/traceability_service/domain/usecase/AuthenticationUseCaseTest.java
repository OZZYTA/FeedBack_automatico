package com.restaurant.traceability_service.domain.usecase;

import com.restaurant.traceability_service.domain.spi.IAuthenticationPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationUseCaseTest {

    @Mock
    private IAuthenticationPersistencePort authenticationPersistencePort;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAuthenticatedUserId_ShouldCallAuthenticationPersistencePortGetAuthenticatedUserId() {

        Long expectedUserId = 1L;
        Mockito.when(authenticationPersistencePort.getAuthenticatedUserId()).thenReturn(expectedUserId);


        Long result = authenticationUseCase.getAuthenticatedUserId();
        
        assertEquals(expectedUserId, result);
        Mockito.verify(authenticationPersistencePort).getAuthenticatedUserId();
    }


}