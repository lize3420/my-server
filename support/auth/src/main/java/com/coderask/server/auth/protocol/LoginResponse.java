package com.coderask.server.auth.protocol;

import com.coderask.server.auth.model.LoginUser;
import com.coderask.server.common.response.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginResponse extends Response {
    private String token;
    private LoginUser user;

}
