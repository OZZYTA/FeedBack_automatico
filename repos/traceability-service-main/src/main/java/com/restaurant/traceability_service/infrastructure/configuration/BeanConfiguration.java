package com.restaurant.traceability_service.infrastructure.configuration;

import com.restaurant.traceability_service.domain.api.IAuthenticationServicePort;
import com.restaurant.traceability_service.domain.api.ITraceabilityServicePort;
import com.restaurant.traceability_service.domain.spi.IAuthenticationPersistencePort;
import com.restaurant.traceability_service.domain.spi.ITraceabilityPersistencePort;
import com.restaurant.traceability_service.domain.usecase.AuthenticationUseCase;
import com.restaurant.traceability_service.domain.usecase.TraceabilityUseCase;
import com.restaurant.traceability_service.infrastructure.output.mongo.adapter.TraceabilityMongoAdapter;
import com.restaurant.traceability_service.infrastructure.output.mongo.mapper.TraceabilityEntityMapper;
import com.restaurant.traceability_service.infrastructure.output.mongo.repository.TraceabilityRepository;
import com.restaurant.traceability_service.infrastructure.output.security.adapter.AuthenticationAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final TraceabilityRepository traceabilityRepository;
    private final TraceabilityEntityMapper traceabilityEntityMapper;

    @Bean
    public IAuthenticationPersistencePort authenticationPersistencePort(){
        return new AuthenticationAdapter();
    }

    @Bean
    public IAuthenticationServicePort authenticationServicePort(){
        return new AuthenticationUseCase(authenticationPersistencePort());
    }

    @Bean
    public ITraceabilityPersistencePort traceabilityPersistencePort(){
        return new TraceabilityMongoAdapter(traceabilityEntityMapper,traceabilityRepository);
    }

    @Bean
    public ITraceabilityServicePort traceabilityServicePort(){
        return new TraceabilityUseCase(traceabilityPersistencePort());
    }

}
