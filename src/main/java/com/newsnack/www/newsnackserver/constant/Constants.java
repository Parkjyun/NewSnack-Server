package com.newsnack.www.newsnackserver.constant;

public class Constants {
    public static final String USER_ID_CLAIM_NAME = "uid";
    public static final String USER_ROLE_CLAIM_NAME = "rol";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String OAUTH_AUTHORIZATION_CODE = "X-AUTHORIZATION-CODE";

    public static final String[] AUTH_WHITELIST = {
            "/v1/auth/login",
            "/v1/auth/reissue",
            "/actuator/health",
    };

}
