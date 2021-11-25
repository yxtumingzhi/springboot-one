package com.hope.cloud.user.request.api;

import lombok.Data;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-19 15:18
 */
@Data
public class PwdLoginRequest {

    private String loginName;
    private String password;

}
