package com.coderask.server.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SessionRequest extends Request{
    private String token;
}
