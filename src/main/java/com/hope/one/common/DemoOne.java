package com.hope.one.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tumingzhi
 */
@Component
public class DemoOne {

//    @Scheduled(cron = "*/10 * * * * ? ")
//    public void bTask() {
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(Thread.currentThread().getName() + ":" + sdf.format(new Date()) + " --> B任务每10秒执行一次");
//    }

}
