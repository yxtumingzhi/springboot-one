/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hope.one.controller;


import cn.hutool.core.util.RandomUtil;
import com.hope.one.entity.BladeNotice;
import com.hope.one.mapper.BladeNoticeMapper;
import com.hope.one.req.Request;
import com.hope.one.service.IBladeNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 通知公告表 控制器
 *
 * @author tumingzhi
 * @since 2021-02-02
 */
@Slf4j
@Controller
public class PageController {

    @Autowired
    private IBladeNoticeService bladeNoticeService;
    @Autowired
    private BladeNoticeMapper bladeNoticeMapper;

    @RequestMapping("/hello")
    public String testcode(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("test", "afdsaf");
        Cookie cookie2 = new Cookie("test2", "afdsaf2");
        response.addCookie(cookie);
        response.addCookie(cookie2);
        return "hello";
    }


}
