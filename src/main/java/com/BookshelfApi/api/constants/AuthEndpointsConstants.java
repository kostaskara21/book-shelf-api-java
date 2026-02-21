package com.BookshelfApi.api.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthEndpointsConstants {

    public static final String BASIC_URL = "api/auth";
    public static final String REGISTER = "register";
    public static final String AUTHENTICATE = "authenticate";
}
