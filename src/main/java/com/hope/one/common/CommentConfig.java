package com.hope.one.common;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class CommentConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        //不設置阻塞隊列長度，默認是21亿
        //executor.setQueueCapacity(10000);
        executor.setKeepAliveSeconds(10);
        executor.setThreadNamePrefix("tumingzhi-async-executor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Bean
    public RedissonClient redisson() {
        // 单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.19.129:36379").setDatabase(0);
        return Redisson.create(config);
    }
}