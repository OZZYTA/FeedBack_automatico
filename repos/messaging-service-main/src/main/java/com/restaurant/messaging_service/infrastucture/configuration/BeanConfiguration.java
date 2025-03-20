package com.restaurant.messaging_service.infrastucture.configuration;


import com.restaurant.messaging_service.domain.api.IMessagingServicePort;
import com.restaurant.messaging_service.domain.spi.ICodePersistencePort;
import com.restaurant.messaging_service.domain.spi.IMessagingPersistencePort;
import com.restaurant.messaging_service.domain.usecase.MessagingUseCase;
import com.restaurant.messaging_service.infrastucture.output.messaging.adapter.TwilioAdapter;
import com.restaurant.messaging_service.infrastucture.output.jpa.adapter.CodeAdapter;
import com.restaurant.messaging_service.infrastucture.output.jpa.repository.ICodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICodeRepository codeRepository;

    @Bean
    public ICodePersistencePort codePersistencePort(){
        return new CodeAdapter(codeRepository);
    }

    @Bean
    public IMessagingPersistencePort messagingPersistencePort(){
        return new TwilioAdapter();
    }

    @Bean
    public IMessagingServicePort messagingServicePort(){
        return new MessagingUseCase(codePersistencePort(),messagingPersistencePort());
    }

}
