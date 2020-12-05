package com.coderask.server.verificationcode.service;

import com.coderask.server.common.response.Response;
import com.coderask.server.verificationcode.autoconfigure.VerificationCodeProperties;
import com.coderask.server.verificationcode.model.ErrInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VerificationCodeService implements IVerificationCodeService {

    private final List<IVerificationCodeSender> verificationCodeSenders;
    private final StringRedisTemplate stringRedisTemplate;
    private final VerificationCodeBuilder verificationCodeBuilder;
    private final VerificationCodeProperties verificationCodeProperties;


    @Override
    public Response send(String target, String type) {
        var senders = verificationCodeSenders.stream().filter(it -> it.support(target)).collect(Collectors.toList());
        if (senders.size() == 0) {
            return Response.ofFailCode(ErrInfo.notSupportTargetType);
        }
        var redisKey = buildKey(target, type);
        String code = this.stringRedisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isEmpty(code)) {
            code = verificationCodeBuilder.randomCode();
        }
        var sendResult = senders.get(0).send(target, type, code);
        if (sendResult.isSuccess()) {
            stringRedisTemplate.opsForValue().set(redisKey, code, Duration.ofMillis(verificationCodeProperties.getCheckCodeExpiredTime()));
        }
        return sendResult;
    }

    @Override
    public boolean validVerificationCode(String target, String type, String verificationCode) {
        var code = stringRedisTemplate.opsForValue().get(buildKey(target, type));
        return code != null && code.equals(verificationCode);
    }

    String buildKey(String target, String type) {
        return String.format("verificationCode-%1s-%2s", target, type);
    }

}
