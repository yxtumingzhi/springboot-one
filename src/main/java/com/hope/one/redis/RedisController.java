package com.hope.one.redis;

import com.google.common.collect.Lists;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-02 13:46
 */
@RestController
public class RedisController {


    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/getLock")
    public String getLock() throws Exception {
        System.out.println(redissonClient.getLock("redis-try-lock-key").tryLock(1, 15, TimeUnit.SECONDS));
        Thread.sleep(30000);
        return "ok";
    }

    @GetMapping("/getLock2")
    public String getLock2() throws InterruptedException {
        System.out.println(redissonClient.getLock("redis-try-lock-key").tryLock(1, 1, TimeUnit.SECONDS));
        System.out.println(redissonClient.getLock("redis-try-lock-key").tryLock(1, TimeUnit.SECONDS));
        List<Integer> data = Lists.newArrayList();
        return "ok";
    }
}
