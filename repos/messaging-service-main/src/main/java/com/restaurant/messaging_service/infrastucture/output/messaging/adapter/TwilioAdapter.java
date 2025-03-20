package com.restaurant.messaging_service.infrastucture.output.messaging.adapter;

import com.restaurant.messaging_service.domain.spi.IMessagingPersistencePort;
import com.restaurant.messaging_service.utils.TwilioConstants;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;

public class TwilioAdapter implements IMessagingPersistencePort {

    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;

    @Override
    public void notifyClient(String phoneNumber, String code) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new com.twilio.type.PhoneNumber(phoneNumber),
                        new com.twilio.type.PhoneNumber(TwilioConstants.PHONE),
                        TwilioConstants.NOTIFY_BODY_MESSAGE + code)
                .create();


    }
}
