package com.coderask.server.auth.protocol;

import lombok.Data;

@Data
public class LoginRequest {
    private String loginName;
    private String password;
}
