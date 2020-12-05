package com.coderask.server.sms.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckCodeTemplateParams {
    private String code;
}
