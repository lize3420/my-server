package com.coderask.server.sms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AliSendSMSResponse {
    @JsonProperty("RequestId")
    private String requestId;
    @JsonProperty("BizId")
    private String bizId;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Message")
    private String message;


    @JsonIgnore
    public boolean isSuccess(){
        return "OK".equals(code);
    }
}
