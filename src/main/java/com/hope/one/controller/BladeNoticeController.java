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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.ObjectName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;

/**
 * 通知公告表 控制器
 *
 * @author tumingzhi
 * @since 2021-02-02
 */
@RestController
@Slf4j
public class BladeNoticeController {

    @Autowired
    private IBladeNoticeService bladeNoticeService;
    @Autowired
    private BladeNoticeMapper bladeNoticeMapper;

    @PostMapping("/testcode")
    public String testcode(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            bladeNoticeService.testAsync();
        }
        return "adfa";
    }


    //@Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/redis-one/{id}", method = RequestMethod.GET)
    public BladeNotice redis112(@PathVariable("id") Long id) {
        log.info(id + "");
        BladeNotice notice1 = bladeNoticeService.selectById(id);
        BladeNotice notice2 = bladeNoticeService.selectById(id);
        log.info(Objects.equals(notice1, notice2) + "");

        bladeNoticeMapper.update(1L, "12312312");
        BladeNotice notice3 = bladeNoticeService.selectById(id);
        BladeNotice notice4 = bladeNoticeService.selectById(id);

        return notice1;
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/save")
    public String save() throws InterruptedException {
        BladeNotice bladeNotice = new BladeNotice();
        bladeNotice.setCategory(RandomUtil.randomInt());
        bladeNotice.setTitle("测试时戳问题");
        bladeNotice.setContent(RandomUtil.randomString(20));
        bladeNotice.setCreateTime(LocalDateTime.of(2021, 7, 12, 10, 40, 12, 100000000));
        bladeNotice.setReleaseTime(LocalDateTime.of(2021, 7, 12, 10, 40, 12, 569000000));
        bladeNoticeMapper.insert(bladeNotice);
        return "adfa";
    }


    @GetMapping("getBladeNotice/{id}")
    public BladeNotice getBladeNotice(@PathVariable("id") Long id) {
        BladeNotice bladeNotice = bladeNoticeService.selectById(id);
        System.out.println(bladeNotice.getReleaseTime());
        System.out.println(bladeNotice.getCreateTime());
        return bladeNotice;
    }

    @GetMapping("getBladeNotice111")
    public void getBladeNotice111() {
        bladeNoticeService.test();
    }
}
