package com.hope.cloud.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hope.cloud.user.entity.User;
import com.hope.cloud.user.request.api.PwdLoginRequest;
import com.hope.cloud.user.response.LoginResultVO;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-19 15:13
 */
public interface IUserService extends IService<User> {

    /**
     * 账号密码登录
     *
     * @param request 登录参数
     * @return 登录结果
     */
    LoginResultVO pwdLogin(PwdLoginRequest request);

}
