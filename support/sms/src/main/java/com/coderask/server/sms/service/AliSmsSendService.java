package com.coderask.server.sms.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.coderask.server.common.exception.BusinessException;
import com.coderask.server.common.exception.HttpException;
import com.coderask.server.sms.autoconfigure.SMSProperties;
import com.coderask.server.sms.model.AliSendSMSResponse;
import com.coderask.server.sms.model.SmsResponseConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.Map;

@RequiredArgsConstructor
public class AliSmsSendService implements SmsSendService {

    private final SMSProperties smsProperties;
    private final IAcsClient acsClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void send(String mobile, String templateId, Map<String, String> params) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("TemplateCode", templateId);
        request.putQueryParameter("SignName", smsProperties.getSignName());

        try {
            request.putQueryParameter("TemplateParam", objectMapper.writeValueAsString(params));
            CommonResponse response = acsClient.getCommonResponse(request);
            if (response.getHttpStatus() != HttpStatus.OK.value()) {
                throw HttpException.of(response.getHttpStatus());
            }
            AliSendSMSResponse resultData = objectMapper.readValue(response.getData(), AliSendSMSResponse.class);
            if (!resultData.isSuccess()) {
                throw BusinessException.of(resultData.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.of(e, SmsResponseConstant.RESPONSE_CODE_SEND_SMS_FAIL);
        }
    }
}
