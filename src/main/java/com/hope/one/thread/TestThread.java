package com.hope.one.thread;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class RunnableThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "." + i);
        }
    }
}

public class TestThread {
    public static void main(String[] args) {
        RunnableThread runnableThread = new RunnableThread();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
        (
                3,
                5,
                2000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>()
        );
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.execute(runnableThread);
        }
        List<Runnable> runnables = threadPoolExecutor.shutdownNow();
        System.out.println(runnables.size());
        System.out.println("是否停下" + threadPoolExecutor.isShutdown());
        System.out.println("是否终止" + threadPoolExecutor.isTerminated());
        System.out.println("获取CPU个数" + Runtime.getRuntime().availableProcessors());
        while (threadPoolExecutor.isTerminated()) {
            break;
        }


    }
}