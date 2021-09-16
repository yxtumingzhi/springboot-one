package com.hope.one.common;


import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class CommentUtils {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5,
            10,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue(1000),
//            new SynchronousQueue<>(),
            new ThreadFactoryBuilder().setNamePrefix("updateTaskStatusJob ThreadPool-").build()
    );

    private static final ThreadPoolTaskExecutor task = new ThreadPoolTaskExecutor();

    public void say() {
        System.out.println("say:::" + executor.toString());
        System.out.println("say:::" + threadPoolTaskExecutor.toString());
    }

}