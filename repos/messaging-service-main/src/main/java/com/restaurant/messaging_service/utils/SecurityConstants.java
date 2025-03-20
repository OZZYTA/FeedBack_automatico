package com.restaurant.messaging_service.utils;


import static com.restaurant.messaging_service.utils.Constants.UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED;

public class SecurityConstants {
    private SecurityConstants() {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final Integer JWT_SUBSTRING = 7;
    //Token
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER ="Bearer ";
    //Jwt Claims
    public static final String CLAIM_ROLE = "role";
    public static final String CLAIM_ID = "id";
    //Roles names
    public static final String ROLE_EMPLOYEE = "Empleado";

}
