package com.hope.cloud.base.utils;

import com.hope.cloud.base.common.LoginSession;
import com.hope.cloud.base.common.ServiceConstant;
import com.hope.one.utils.SpringUtil;
import com.hope.one.utils.StringPool;
import com.hope.one.utils.StringUtil;
import com.hope.one.utils.WebUtil;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Secure工具类
 *
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-19 15:15
 */
public class SecureUtil {

    private static final StringRedisTemplate stringRedisTemplate;

    static {
        stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
    }

    public static LoginSession getCurrentLogin() {
        return null;
    }

    public static String getToken() {
        String requestAttr = getRequestAttr();
        if (StringUtil.isBlank(requestAttr)) {
            return StringPool.EMPTY;
        }
        return null;
    }

    public static String getRequestAttr() {
        HttpServletRequest httpServletRequest = WebUtil.getRequest();
        if (httpServletRequest == null) {
            return StringPool.EMPTY;
        }
        return httpServletRequest.getHeader(ServiceConstant.HOPE_CLOUD_REQUEST_ATTR);
    }

    public static String parseRequestAttr(String token) {
        StringBuilder sb = new StringBuilder();
        sb.append("t=").append(token).append(";");
        return sb.toString();
    }
}
