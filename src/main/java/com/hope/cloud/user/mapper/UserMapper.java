package com.hope.cloud.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hope.cloud.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-19 14:32
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where login_name = #{loginName}")
    User findByLoginName(@Param("loginName") String loginName);

    @Select("select * from user where login_name = #{mobile}")
    User findByMobile(@Param("mobile") String mobile);

    @Select("select * from user where login_name = #{email}")
    User findByEmail(@Param("email") String email);
}
