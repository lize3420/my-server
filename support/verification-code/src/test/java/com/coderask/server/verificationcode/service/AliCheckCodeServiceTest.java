package com.coderask.server.verificationcode.service;


import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.coderask.server.common.response.Response;
import com.coderask.server.verificationcode.dao.VerificationCodeRecordRepository;
import com.coderask.server.verificationcode.entity.VerificationCodeRecord;
import com.coderask.server.verificationcode.model.AliSendSMSResponse;
import com.coderask.server.verificationcode.model.CheckCodeTypes;
import com.coderask.server.verificationcode.autoconfigure.VerificationCodeProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AliCheckCodeServiceTest {

    @Mock
    private IAcsClient iAcsClient;
    @Mock
    private VerificationCodeRecordRepository checkCodeRecordRepository;
    @Mock
    private VerificationCodeProperties smsProperties;
    @Mock
    private VerificationCodeBuilder checkCodeUtil;

    @InjectMocks
    private VerificationCodeService aliCheckCodeService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testSendSuccess() throws ClientException, JsonProcessingException {
        CommonResponse response = new CommonResponse();
        response.setHttpStatus(HttpStatus.SC_OK);
        AliSendSMSResponse r = new AliSendSMSResponse();
        r.setCode("OK");
        r.setMessage("OK");
        r.setBizId("123");
        r.setRequestId("123");
        response.setData(objectMapper.writeValueAsString(r));
        when(iAcsClient.getCommonResponse(ArgumentMatchers.any())).thenReturn(response);
        when(smsProperties.getCheckCodeTemplate()).thenReturn(Map.of(CheckCodeTypes.register, "1234567"));
        when(checkCodeUtil.randomCheckCode()).thenReturn("123456");

        Response result = aliCheckCodeService.send("13466718118", CheckCodeTypes.register);

        verify(iAcsClient).getCommonResponse(any());
        assertThat(result.isSuccess()).isTrue();

    }

    @Test
    public void testErrorSmsType() throws ClientException {
        when(smsProperties.getCheckCodeTemplate()).thenReturn(Map.of());
        Response result = aliCheckCodeService.send("13466718118", CheckCodeTypes.register);
        verify(iAcsClient,never()).getCommonResponse(any());
        assertThat(result.isSuccess()).isFalse();

    }

    @Test
    public void testRepositorySaveOnSendSuccess() throws Exception {
        var response = successCommonResponse();
        when(iAcsClient.getCommonResponse(ArgumentMatchers.any())).thenReturn(response);
        when(smsProperties.getCheckCodeTemplate()).thenReturn(Map.of(CheckCodeTypes.register, "1234567"));
        when(checkCodeUtil.randomCheckCode()).thenReturn("123456");

        aliCheckCodeService.send("13466718118", CheckCodeTypes.register);

        var argument = ArgumentCaptor.forClass(VerificationCodeRecord.class);
        verify(checkCodeRecordRepository).save(argument.capture());
        var params = argument.getValue();
        assertThat(params.getMobile()).isEqualTo("13466718118");
        assertThat(params.getCode()).isEqualTo("123456");
        assertThat(params.getType()).isEqualTo(CheckCodeTypes.register);
        assertThat(params.isSuccess()).isTrue();
    }

    @Test
    public void testRepositorySaveOnSendFail() throws Exception {
        var response = failCommonResponse();
        when(iAcsClient.getCommonResponse(ArgumentMatchers.any())).thenReturn(response);
        when(smsProperties.getCheckCodeTemplate()).thenReturn(Map.of(CheckCodeTypes.register, "1234567"));
        when(checkCodeUtil.randomCheckCode()).thenReturn("123456");

        var sendResult = aliCheckCodeService.send("13466718118", CheckCodeTypes.register);

        assertThat(sendResult.isSuccess()).isFalse();
        verify(checkCodeRecordRepository,never()).save(any());
    }

    @Test
    public void testValidCode() throws Exception {
        when(checkCodeRecordRepository.findByMobileAndTypeAndCodeAndExpiredTimeBefore(eq("13466718118"),eq(CheckCodeTypes.register),eq("123456"),anyLong())).thenReturn(Optional.of(new VerificationCodeRecord()));
        var result = aliCheckCodeService.validCheckCode("13466718118", CheckCodeTypes.register,"123456");
        assertThat(result).isTrue();
    }

    @Test
    public void testValidCodeFail() throws Exception {
        when(checkCodeRecordRepository.findByMobileAndTypeAndCodeAndExpiredTimeBefore(eq("13466718118"),eq(CheckCodeTypes.register),eq("123456"),anyLong())).thenReturn(Optional.empty());
        var result = aliCheckCodeService.validCheckCode("13466718118", CheckCodeTypes.register,"123456");
        assertThat(result).isFalse();
    }


    private CommonResponse successCommonResponse() throws JsonProcessingException {
        CommonResponse response = new CommonResponse();
        response.setHttpStatus(HttpStatus.SC_OK);
        AliSendSMSResponse r = new AliSendSMSResponse();
        r.setCode("OK");
        r.setMessage("OK");
        r.setBizId("123");
        r.setRequestId("123");
        response.setData(objectMapper.writeValueAsString(r));
        return response;
    }

    private CommonResponse failCommonResponse() throws JsonProcessingException {
        CommonResponse response = new CommonResponse();
        response.setHttpStatus(HttpStatus.SC_OK);
        AliSendSMSResponse r = new AliSendSMSResponse();
        r.setCode("FAIL");
        r.setMessage("FAIL");
        r.setBizId("123");
        r.setRequestId("123");
        response.setData(objectMapper.writeValueAsString(r));
        return response;
    }
}
