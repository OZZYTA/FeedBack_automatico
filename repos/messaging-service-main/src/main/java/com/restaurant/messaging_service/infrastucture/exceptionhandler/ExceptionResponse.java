package com.restaurant.messaging_service.infrastucture.exceptionhandler;


import com.restaurant.messaging_service.utils.Constants;

public enum ExceptionResponse {
    INVALID_JSON_FORMAT(Constants.EXCEPTION_INVALID_JSON_FORMAT),
    INVALID_CODE(Constants.EXCEPTION_INVALID_CODE),
    ORDER_IS_NOT_READY(Constants.EXCEPTION_ORDER_IS_NOT_READY),
 ;

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}