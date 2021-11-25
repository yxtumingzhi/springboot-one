package com.hope.cloud.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-19 14:30
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     * us_user.login_name
     */
    private String loginName;

    /**
     * 密码
     * us_user.passwd
     */
    private String passwd;

    /**
     * 电子邮件
     * us_user.email
     */
    private String email;

    /**
     * 手机号码
     * us_user.mobile
     */
    private String mobile;

    /**
     * 手机号密文
     * us_user.mobile_encrypt
     */
    private String mobileEncrypt;

    /**
     * 是否加密过手机号，0：否，1：是
     * us_user.encrypt_status
     */
    private Byte encryptStatus;

    /**
     * 邮件绑定状态 0未绑定 1绑定
     * us_user.email_status
     */
    private Byte emailStatus;

    /**
     * 手机绑定状态 0未绑定 1绑定
     * us_user.mobile_status
     */
    private Byte mobileStatus;

    /**
     * 注册IP地址
     * us_user.register_ip
     */
    private String registerIp;

    /**
     * 用户状态 0未激活/黑名单 1激活  2-已注销
     * us_user.user_status
     */
    private Byte userStatus;

    /**
     * 最后更新时间
     * us_user.updated_time
     */
    private Long updatedTime;

    /**
     * 注册时间
     * us_user.created_time
     */
    private Long createdTime;


}
