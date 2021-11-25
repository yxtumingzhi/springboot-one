package com.hope.one.cloud;

import com.hope.cloud.user.entity.User;
import com.hope.cloud.user.mapper.UserMapper;
import com.hope.cloud.user.request.api.PwdLoginRequest;
import com.hope.cloud.user.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-19 14:33
 */
@SuppressWarnings("all")
@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;


    @Test
    void login() {
        PwdLoginRequest request = new PwdLoginRequest();
        request.setLoginName("tumingzhi");
        request.setPassword("12345");
        userService.pwdLogin(request);
    }


    @Test
    void demo() {
        User user = new User();
        user.setId(null);
        user.setLoginName("tumingzhi");
        user.setPasswd("123");
        user.setEmail("tumingzhi@163.com");
        user.setMobile("15093556413");
        user.setMobileEncrypt("15093556413");
        user.setEncryptStatus((byte) 0);
        user.setEmailStatus((byte) 0);
        user.setMobileStatus((byte) 0);
        user.setRegisterIp("");
        user.setUserStatus((byte) 0);
        user.setUpdatedTime(System.currentTimeMillis());
        user.setCreatedTime(System.currentTimeMillis());
        userMapper.insert(user);
    }

}
