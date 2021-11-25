package com.hope.cloud.base.common;

import lombok.Data;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-19 16:47
 */
@Data
public class LoginSession {

    private String token;
    private Long user_id;
    private String user_name;
    private String user_avatar;

}
