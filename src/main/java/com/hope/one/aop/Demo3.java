package com.hope.one.aop;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author tumingzhi
 */
@Component
public class Demo3 {

    public void bTask() {
        System.out.println(Thread.currentThread().getName() + ":" + " --> B任务执行一次");

        HashMap map = new HashMap();
        map.put();
        ThreadPoolTaskExecutor

    }

}
