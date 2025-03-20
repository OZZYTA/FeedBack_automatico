package com.restaurant.traceability_service.application.handler;

import com.restaurant.traceability_service.domain.api.IAuthenticationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationHandler implements IAuthenticationHandler {

    private final IAuthenticationServicePort authenticationServicePort;

    @Override
    public Long getAuthenticatedUserId() {
        return authenticationServicePort.getAuthenticatedUserId();
    }
}
