package com.restaurant.traceability_service.domain.usecase;


import com.restaurant.traceability_service.domain.api.IAuthenticationServicePort;
import com.restaurant.traceability_service.domain.spi.IAuthenticationPersistencePort;

public class AuthenticationUseCase implements IAuthenticationServicePort {

    private final IAuthenticationPersistencePort authenticationPersistencePort;

    public AuthenticationUseCase(IAuthenticationPersistencePort authenticationPersistencePort) {
        this.authenticationPersistencePort = authenticationPersistencePort;
    }

    @Override
    public Long getAuthenticatedUserId() {
        return authenticationPersistencePort.getAuthenticatedUserId();
    }
}
