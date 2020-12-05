package com.coderask.server.auth.protocol;

import lombok.Data;

@Data
public class RegisterByMobileRequest {
    private String mobile;
    private String password;
    private String code;
}
