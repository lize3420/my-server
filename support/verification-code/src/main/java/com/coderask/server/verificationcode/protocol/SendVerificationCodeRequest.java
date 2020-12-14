package com.coderask.server.verificationcode.protocol;

import com.coderask.server.common.model.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SendVerificationCodeRequest extends Request {
    private String target;
    private String type;
}
