package com.restaurant.traceability_service.utils;

public class Constants {
    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Utility class should not be instantiated";

    private Constants () {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final String RESPONSE_MESSAGE_KEY = "Message";

    public static final String PENDING = "Pending";
    public static final String IN_PREPARATION = "In Preparation";
    public static final String READY = "Ready";
    public static final String CANCELED = "Canceled";
    public static final String DELIVERED = "Delivered";

}
