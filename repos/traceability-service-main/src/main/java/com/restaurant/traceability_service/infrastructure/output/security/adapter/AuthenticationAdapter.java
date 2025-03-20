package com.restaurant.traceability_service.infrastructure.output.security.adapter;

import com.restaurant.traceability_service.domain.spi.IAuthenticationPersistencePort;
import com.restaurant.traceability_service.infrastructure.output.security.entity.SecurityUser;
import com.restaurant.traceability_service.utils.SecurityConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationAdapter implements IAuthenticationPersistencePort {

    @Override
    public Long getAuthenticatedUserId() {
        return getAuthenticatedUser().getId();
    }

    private SecurityUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException(SecurityConstants.USER_NOT_AUTHENTICATED);
        }
        return (SecurityUser) authentication.getPrincipal();
    }
}
