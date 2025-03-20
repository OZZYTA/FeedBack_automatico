package com.restaurant.messaging_service.utils;

public class Constants {
    private Constants () {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }
    //Exceptions messages
    public static final String RESPONSE_MESSAGE_KEY = "Message";
    public static final String EXCEPTION_INVALID_JSON_FORMAT = "Invalid JSON format";
    public static final String EXCEPTION_INVALID_CODE = "The provided code is invalid.";
    public static final String EXCEPTION_ORDER_IS_NOT_READY = "The order is not ready for delivery.";


    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Utility class should not be instantiated";
    public static final String CHARSET = "0123456789";
    public static final int CODE_LENGTH = 4;
}
