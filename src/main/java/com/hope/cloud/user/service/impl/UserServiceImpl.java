package com.hope.cloud.user.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hope.cloud.base.common.LoginSession;
import com.hope.cloud.base.exception.ServiceException;
import com.hope.cloud.user.entity.User;
import com.hope.cloud.user.mapper.UserMapper;
import com.hope.cloud.user.request.api.PwdLoginRequest;
import com.hope.cloud.user.response.LoginResultVO;
import com.hope.cloud.user.service.IUserService;
import com.hope.cloud.utils.SecretUtil;
import com.hope.cloud.utils.CacheUtil;
import com.hope.cloud.utils.PatternUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-19 15:15
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public LoginResultVO pwdLogin(PwdLoginRequest request) {
        LoginResultVO result = new LoginResultVO();
        String loginName = request.getLoginName();
        String password = request.getPassword();

        if (StrUtil.isEmpty(password)) {
            throw new ServiceException("用户登录密码不能为空");
        }

        User dbUser;
        if (PatternUtil.isMobile(loginName)) {
            dbUser = baseMapper.findByMobile(loginName);
        } else if (PatternUtil.isEmail(loginName)) {
            dbUser = baseMapper.findByEmail(loginName);
        } else {
            dbUser = baseMapper.findByLoginName(loginName);
        }

        if (dbUser == null) {
            throw new ServiceException("您还不是我们的用户呢~");
        }

        if (SecretUtil.decryptNotEqual(dbUser.getPasswd(), password)) {
            throw new ServiceException("密码错误~");
        }

        return writeToken(result, dbUser);
    }

    private LoginResultVO writeToken(LoginResultVO result, User dbUser) {
        String token = getToken();
        String key = CacheUtil.getUserTokenKey(token);
        String session = session(token, dbUser);

        stringRedisTemplate.opsForValue().set(key, session, 24, TimeUnit.HOURS);
        result.setToken(token);

        return result;
    }

    private String session(String token, User dbUser) {
        LoginSession session = new LoginSession();
        session.setToken(token);
        session.setUser_id(dbUser.getId());
        session.setUser_name(dbUser.getLoginName());
        session.setUser_avatar("https://avatar.csdnimg.cn/1/F/A/2_weixin_46657495_1585383717.jpg");
        return JSONUtil.toJsonStr(session);
    }

    private String getToken() {
        String token = SecretUtil.generateToken();
        String exist = stringRedisTemplate.opsForValue().get(CacheUtil.getUserTokenKey(token));
        while (StrUtil.isNotBlank(exist)) {
            token = getToken();
        }
        return token;
    }
}
