package com.restaurant.messaging_service.domain.utils;

import com.restaurant.messaging_service.utils.Constants;

import java.security.SecureRandom;

public class CodeGenerator {

    public static String generateCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(Constants.CODE_LENGTH);

        for (int i = 0; i < Constants.CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(Constants.CHARSET.length());
            code.append(Constants.CHARSET.charAt(randomIndex));
        }

        return code.toString();
    }
}
