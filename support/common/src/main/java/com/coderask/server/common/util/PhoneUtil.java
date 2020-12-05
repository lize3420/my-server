package com.coderask.server.common.util;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class PhoneUtil {

    //校验手机是否合规 2020年最全的国内手机号格式
//    private static final String REGEX_MOBILE = "((\\+86|0086)?\\s*)((134[0-8]\\d{7})|(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|18[0-9]|19(1|[8-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))";
    private static final String REGEX_MOBILE = "^((\\+86|0086)?\\s*)[1][3,4,5,6,7,8,9]\\d{9}$";
    /**
     * 校验手机号
     *
     * @param phone 手机号
     * @return boolean true:是  false:否
     */
    public static boolean isMobile(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        return Pattern.matches(REGEX_MOBILE, phone);
    }

    public static String parseChineseMobile(String mobile){
        if(StringUtils.isEmpty(mobile)){
            return null;
        }
        if(mobile.startsWith("+86")){
            return mobile;
        }else if(mobile.startsWith("0086")){
            return mobile.replaceFirst("0086","+86");
        }
        return "+86" + mobile;
    }

}
