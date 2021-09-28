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


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.hope.one.common.Singleton;
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

import javax.annotation.Resource;
import javax.management.ObjectName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 通知公告表 控制器
 *
 * @author tumingzhi
 * @since 2021-02-02
 */
@SuppressWarnings("all")
@RestController
@Slf4j
public class BladeNoticeController {
    @Resource
    private PrometheusCustomMonitor monitor;

    @RequestMapping("/order")
    public String order() throws Exception {
        // 统计下单次数
        monitor.getOrderCount().increment();
        Random random = new Random();
        int amount = random.nextInt(100);
        // 统计金额
        monitor.getAmountSum().record(amount);
        return "下单成功, 金额: " + amount;
    }


    //    @Autowired
//    private IBladeNoticeService bladeNoticeService;
    @Autowired
    private BladeNoticeMapper bladeNoticeMapper;

    @PostMapping("/testcode")
    public String testcode() throws InterruptedException, ClassNotFoundException {
//        for (int i = 0; i < 100; i++) {
//            bladeNoticeService.testAsync();
//        }
//        return "adfa";
//        Singleton singleton = Singleton.getInstance();
//        System.out.println(singleton + " " + singleton.hashCode());
//        Singleton singleton1 = Singleton.getInstance();
//        System.out.println(singleton1 + " " + singleton1.hashCode());
//
//        Singleton singleton2 = Singleton.getInstance();
//        System.out.println(singleton2 + " " + singleton2.hashCode());
//
//        Class sing = Class.forName("com.hope.one.common.Singleton");
//        System.out.println(sing.hashCode());
//

//        List<Singleton> list = new ArrayList();
//        list.add(new Singleton("adc"));
//        list.add(new Singleton("wer"));
//        list.add(new Singleton(""));
//        list.add(new Singleton("sdf"));
//        list.add(new Singleton(null));
//
//        System.out.println(list.stream().map(Singleton::getCode).collect(Collectors.joining(",")));
        // bladeNoticeService.fetch(1L);
        return "adfa";
    }


    @PostMapping("/testcode1")
    public String testcode1(@RequestBody HashMap map) throws InterruptedException, ClassNotFoundException {
//        for (int i = 0; i < 100; i++) {
//            bladeNoticeService.testAsync();
//        }
//        return "adfa";
//        Singleton singleton = Singleton.getInstance();
//        System.out.println(singleton + " " + singleton.hashCode());
//        Singleton singleton1 = Singleton.getInstance();
//        System.out.println(singleton1 + " " + singleton1.hashCode());
//
//        Singleton singleton2 = Singleton.getInstance();
//        System.out.println(singleton2 + " " + singleton2.hashCode());
//
//        Class sing = Class.forName("com.hope.one.common.Singleton");
//        System.out.println(sing.hashCode());
//
        Long id = Long.valueOf(map.get("id").toString());
        if (id < 100) {
            return "success";
        }
        return "error";
    }


    //@Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/redis-one/{id}", method = RequestMethod.GET)
    public BladeNotice redis112(@PathVariable("id") Long id) {
        log.info(id + "");
//        BladeNotice notice1 = bladeNoticeService.selectById(id);
//        BladeNotice notice2 = bladeNoticeService.selectById(id);
//        log.info(Objects.equals(notice1, notice2) + "");
//
//        bladeNoticeMapper.update(1L, "12312312");
//        BladeNotice notice3 = bladeNoticeService.selectById(id);
//        BladeNotice notice4 = bladeNoticeService.selectById(id);

        return null;
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
        List<String> strs = Lists.newArrayList("1","2","3");
        strs.stream().collect(Collectors.joining(","));
        return "adfa";
    }


    @GetMapping("getBladeNotice/{id}")
    public BladeNotice getBladeNotice(@PathVariable("id") Long id) {
        //BladeNotice bladeNotice = bladeNoticeService.selectById(id);
        // System.out.println(bladeNotice.getReleaseTime());
        //System.out.println(bladeNotice.getCreateTime());
        return null;
    }

    @GetMapping("getBladeNotice111")
    public void getBladeNotice111() {
        //bladeNoticeService.test();
    }
}
