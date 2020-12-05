package com.coderask.server.sms.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.coderask.server.common.response.Response;
import com.coderask.server.sms.dao.CheckCodeRecordRepository;
import com.coderask.server.sms.entity.CheckCodeRecord;
import com.coderask.server.sms.model.AliSendSMSResponse;
import com.coderask.server.sms.model.CheckCodeTemplateParams;
import com.coderask.server.sms.autoconfigure.SMSProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AliCheckCodeService implements ICheckCodeService {

    @Autowired
    private IAcsClient acsClient;

    @Autowired
    private SMSProperties smsProperties;

    @Autowired
    private CheckCodeRecordRepository checkCodeRecordRepository;

    @Autowired
    private CheckCodeUtil checkCodeUtil;

    @Override
    public Response send(String mobile, String type) {
        String template = smsProperties.getCheckCodeTemplate().get(type);
        if (StringUtils.isEmpty(template)) {
            return Response.ofFail("无效验证码类型");
        }

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", smsProperties.getAliRegionId());
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("TemplateCode", template);
        request.putQueryParameter("SignName", smsProperties.getSignName());

        try {
            ObjectMapper mapper = new ObjectMapper();
            Optional<CheckCodeRecord> oldRecord = checkCodeRecordRepository.findByMobileAndTypeAndExpiredTimeBefore(mobile, type, System.currentTimeMillis());
            CheckCodeTemplateParams params = new CheckCodeTemplateParams(oldRecord.isPresent() ? oldRecord.get().getCode() : checkCodeUtil.randomCheckCode());
            request.putQueryParameter("TemplateParam", mapper.writeValueAsString(params));
            CommonResponse response = acsClient.getCommonResponse(request);
            AliSendSMSResponse resultData = mapper.readValue(response.getData(), AliSendSMSResponse.class);
            Response result = null;
            if (resultData.isSuccess()) {
                CheckCodeRecord record = new CheckCodeRecord();
                record.setMobile(mobile);
                record.setType(type);
                record.setCode(params.getCode());
                record.setCreateTime(LocalDateTime.now());
                record.setExpiredTime(record.getCreateTime().plusMinutes(smsProperties.getCheckCodeExpiredTime()));
                record.setSuccess(true);
                result = Response.ofSuccess();
                checkCodeRecordRepository.save(record);
            } else {
                result = Response.ofFail("验证码发送失败:" + resultData.getCode());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ofFail(e.getMessage());
        }
    }

    @Override
    public boolean validCheckCode(String mobile, String type, String checkCode) {
        var record = checkCodeRecordRepository.findByMobileAndTypeAndCodeAndExpiredTimeBefore(mobile, type, checkCode, System.currentTimeMillis());
        return record.isPresent();
    }
}
