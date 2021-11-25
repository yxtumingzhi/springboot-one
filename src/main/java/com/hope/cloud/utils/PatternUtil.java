package com.hope.cloud.utils;

import java.util.regex.Pattern;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-19 15:25
 */
public class PatternUtil {

    private static final Pattern PATTERN_MOBILE = Pattern.compile("^(1(([3-9][0-9])))\\d{8}$");
    private static final Pattern PATTERN_EMAIL = Pattern.compile("^[\\w-]{1,40}(\\.[\\w-]{1,20}){0,6}@[\\w-]{1,40}(\\.[\\w-]{1,20}){1,6}$");

    public static boolean isMobile(String mobile) {
        return mobile != null && PATTERN_MOBILE.matcher(mobile).matches();
    }

    public static boolean isEmail(String email) {
        return email != null && email.contains("@") && PATTERN_EMAIL.matcher(email).matches();
    }
}
