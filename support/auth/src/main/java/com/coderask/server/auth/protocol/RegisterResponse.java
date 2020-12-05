package com.coderask.server.auth.protocol;

import lombok.Data;

@Data
public class RegisterResponse {
    private String mobile;
    private String password;
    private String code;
}
