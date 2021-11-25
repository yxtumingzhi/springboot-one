package com.hope.cloud.utils;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-19 15:11
 */
public class CacheUtil {

    public static String user_token = "hope:cloud:user:token:";

    public static String getUserTokenKey(String token) {
        return user_token + token;
    }

}
