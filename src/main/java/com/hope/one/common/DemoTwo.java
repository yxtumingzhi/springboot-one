package com.hope.one.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author tumingzhi
 */
@Component
public class DemoTwo {

//    @Scheduled(cron = "*/10 * * * * ? ")
//    public void aTask() {
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(Thread.currentThread().getName() + ":" + sdf.format(new Date()) + " --> A任务每10秒执行一次");
//        try {
//            TimeUnit.SECONDS.sleep(70);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}
