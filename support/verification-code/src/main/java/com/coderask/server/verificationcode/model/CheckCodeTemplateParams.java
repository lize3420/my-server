package com.coderask.server.verificationcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckCodeTemplateParams {
    private String code;
}
