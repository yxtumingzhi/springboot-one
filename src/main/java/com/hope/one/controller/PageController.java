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
import cn.hutool.json.JSONUtil;
import com.hope.one.common.FsCallInfoResponse;
import com.hope.one.entity.BladeNotice;
import com.hope.one.mapper.BladeNoticeMapper;
import com.hope.one.req.FetchFsCallInfoRequest;
import com.hope.one.req.Request;
import com.hope.one.service.IBladeNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private RedissonClient redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/duduct_st111ock", method = RequestMethod.POST)
    @ResponseBody
    public FsCallInfoResponse deductSto111ck(@RequestBody FetchFsCallInfoRequest request) {
        String data = "{\n" +
                " \"robotId\": 4900,\n" +
                " \"taskId\": \"1234567890\",\n" +
                " \"taskName\": \"外呼任务01\",\n" +
                " \"disnumber\": \"4022000001\",\n" +
                " \"callid\": \"3E8-5B2B55D8-16ED-0\",\n" +
                " \"direction\": 1,\n" +
                " \"caller\": \"12345678901\",\n" +
                " \"called\": \"13100010001\",\n" +
                " \"startTime\": \"2020-10-10 10:00:00\",\n" +
                " \"endTime\": \"2020-10-10 10:00:00\",\n" +
                " \"duration\": 30,\n" +
                " \"reason\": 0,\n" +
                " \"hangupPart\":\"用户端\",\n" +
                " \"recordUrl\": \"https://ip:port/xxx/2020-10-10/10-00/01053270142_13800138000_20180823161604.wav\",\n" +
                " \"userdata\": \"ABC100\",\n" +
                " \"tags\": [\"是本人\",\"预约试驾\"],\n" +
                " \"entityData\": {\n" +
                " \"账单类型\": \"8月\",\n" +
                " \"月份\": \"8月\",\n" +
                " \"城市\": \"上海普陀\"\n" +
                " },\n" +
                " \"rounds\": 3,\n" +
                " \"intent\": \"意向度名称\",\n" +
                " \"text\": [{\n" +
                " \"idx\": 1,\n" +
                " \"role\": \"robot\",\n" +
                " \"msg\": \"您好，请问您是张三吗\"\n" +
                " }, {\n" +
                " \"idx\": 2,\n" +
                " \"role\": \"user\",\n" +
                " \"msg\": \"嗯\"\n" +
                " }]\n" +
                "}";
        FsCallInfoResponse response = new FsCallInfoResponse();
        List<FsCallInfoResponse.Item> itemList = new ArrayList<>(10);
        FsCallInfoResponse.Item item = new FsCallInfoResponse.Item();
        item.setId(1L);
        item.setSourcePlatform(1);
        item.setCallBackData(JSONUtil.parse(data));
        item.setCreatedTime(System.currentTimeMillis());
        item.setUpdatedTime(System.currentTimeMillis());
        itemList.add(item);
        response.setResult(itemList);
        return response;

    }


    /**
     * 模拟下单减库存的场景
     *
     * @return
     */
    @RequestMapping(value = "/duduct_stock")
    @ResponseBody
    public String deductStock() {
        String lockKey = "product_001";
        // 1.获取锁对象
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            // 2.加锁
            redissonLock.lock();  // 等价于 setIfAbsent(lockKey,"wangcp",10,TimeUnit.SECONDS);
            // 从redis 中拿当前库存的值
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            // 3.释放锁
            redissonLock.unlock();
        }
        return "end";
    }

}
