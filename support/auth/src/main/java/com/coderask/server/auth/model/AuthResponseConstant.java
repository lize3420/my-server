package com.coderask.server.auth.model;

public class AuthResponseConstant {
    /**
     * 没有此登录名
     */
    public static final String RESPONSE_CODE_NO_LOGINNAME = "LoginNameNotFound";
    /**
     * 密码错误
     */
    public static final String RESPONSE_CODE_PASSWORD_ERROR = "BadPassword";

    /**
     * 账号冻结
     */
    public static final String RESPONSE_CODE_USER_DISABLED = "UserDisabled";

    /**
     * 需要登录
     */
    public static final String RESPONSE_CODE_UNAUTHORIZED= "Unauthorized";

    /**
     * 手机号已注册
     */
    public static final String RESPONSE_CODE_MOBILE_REGISTERED = "MobileRegistered";

}
