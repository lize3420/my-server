package com.coderask.server.auth.protocol;

import com.coderask.server.common.model.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginRequest extends Request {
    private String loginName;
    private String password;
}
