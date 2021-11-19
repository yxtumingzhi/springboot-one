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
package com.hope.one.service.impl;

import cn.hutool.core.lang.func.Func;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.hope.one.common.CommentUtils;
import com.hope.one.entity.BladeNotice;
import com.hope.one.mapper.BladeNoticeMapper;
import com.hope.one.service.IBladeNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 通知公告表 服务实现类
 *
 * @author tumingzhi
 * @since 2021-02-02
 */
@Slf4j
@Service
public class BladeNoticeServiceImpl implements IBladeNoticeService {

    @Resource
    private BladeNoticeMapper bladeNoticeMapper;

    @Autowired
    CommentUtils commentUtils;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public BladeNotice selectById(Long id) {
        return bladeNoticeMapper.selectById(id);
    }

    @Override
    public void test() {
//        for (int i = 0; i < 1; i++) {
//            executor.execute(() -> {
//                System.out.println(LocalDateTime.now() + "  " + Thread.currentThread().getId() + " " + Thread.currentThread().getName());
//            });
//        }
//        for (int i = 0; i < 100; i++) {
//            executor.execute(() -> {
//                long id = Thread.currentThread().getId();
//                System.out.println(LocalDateTime.now() + "  " + id + " " + Thread.currentThread().getName());
//            });
//        }
//        commentUtils.say();
//        System.out.println("System.out.println:::" + threadPoolTaskExecutor.toString());
      // System.out.println("System.out.println:::" +code);
    }

    //@Async("taskExecutor")
    @Async
    @Override
    public void testAsync() {
        System.out.println("System.out.println:::" +"code");
        System.out.println(Thread.currentThread().getName());
//        String message = LocalDateTime.now().toString();
//        log.info("do something, message={}", message);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            log.error("do something error: ", e);
//        }
//        List<BladeNotice> bladeNotices = new ArrayList<>(1000);
//        for (int i = 0; i < 5000; i++) {
//            BladeNotice bladeNotice = new BladeNotice();
//            bladeNotice.setTenantId(RandomUtil.randomNumbers(6));
//            bladeNotice.setTitle(RandomUtil.randomString(10));
//            bladeNotice.setCategory(RandomUtil.randomInt(0, 999999999));
//            bladeNotice.setReleaseTime(LocalDateTime.now().plusSeconds(RandomUtil.randomInt(0, 500)));
//            bladeNotice.setContent(RandomUtil.randomString(50));
//            bladeNotice.setCreateUser(0L);
//            bladeNotice.setCreateTime(LocalDateTime.now());
//            bladeNotice.setUpdateUser(0L);
//            bladeNotice.setUpdateTime(LocalDateTime.now());
//            bladeNotice.setStatus(0);
//            bladeNotice.setIsDeleted(0);
//            bladeNotices.add(bladeNotice);
//        }
//        bladeNoticeMapper.insertBatch(bladeNotices);

    }

    @Override
    public void fetch(Long id) {
        System.out.print(id);
        BladeNotice bladeNotice = bladeNoticeMapper.selectById(id);
        if (bladeNotice.getId() < 20) {
            System.out.println("-");
            fetch(++id);
        }
    }
}
