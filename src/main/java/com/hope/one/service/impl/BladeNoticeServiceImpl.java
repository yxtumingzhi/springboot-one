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

import com.hope.one.entity.BladeNotice;
import com.hope.one.mapper.BladeNoticeMapper;
import com.hope.one.service.IBladeNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 通知公告表 服务实现类
 *
 * @author tumingzhi
 * @since 2021-02-02
 */
@Slf4j
@Service
public class BladeNoticeServiceImpl implements IBladeNoticeService {

    @Autowired
    private BladeNoticeMapper bladeNoticeMapper;

    @Override
    public BladeNotice selectById(Long id) {
        return bladeNoticeMapper.selectById(id);
    }


    @Override
    public void test() {

    }

    //@Async("taskExecutor")
    @Async
    @Override
    public void testAsync() {
        String message = LocalDateTime.now().toString();
        log.info("do something, message={}", message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("do something error: ", e);
        }
    }
}
