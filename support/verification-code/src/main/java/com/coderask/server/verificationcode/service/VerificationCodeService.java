package com.coderask.server.verificationcode.service;

import com.coderask.server.common.exception.BusinessException;
import com.coderask.server.verificationcode.autoconfigure.VerificationCodeProperties;
import com.coderask.server.verificationcode.model.VerificationCodeResponseConstant;
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
    public void send(String target, String type) {
        var senders = verificationCodeSenders.stream().filter(it -> it.support(target)).collect(Collectors.toList());
        if (senders.size() == 0) {
            throw BusinessException.of(VerificationCodeResponseConstant.notSupportTargetType);
        }
        var sender = senders.get(0);
        var redisKey = buildKey(target, type);
        String code = this.stringRedisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isEmpty(code)) {
            code = verificationCodeBuilder.randomCode();
        }
        sender.send(target, type, code);
        stringRedisTemplate.opsForValue().set(redisKey, code, Duration.ofMillis(sender.expireMinutes()));
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
