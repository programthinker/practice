package com.example.pojo;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @ProjectName practice
 * @PackageName com.example.pojo
 * @ClassName Test
 * @Author zhanggeyang
 * @Date 2022-12-12 19:14
 * @Description
 * @Version 1.0
 */

public class Test {
    public static void main(String[] args) {

        //自定义线程池，此处使用的是定义了7个参数
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());


        try {
            long l = System.currentTimeMillis();
            //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            //此处设置的拒绝策略为AbortPolicy(),当处理线程都还没处理完第一轮所有请求的情况下，10个请求线程超过了最大线程数加阻塞队列最大值数
            //无法处理多出的请求，就会启动拒绝策略，这里会抛出异常
            for (int i = 1; i <= 10000; i++) {
                threadPool.execute(() -> {
                    System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName() + "\t 办理业务");
                });
            }
            long l1 = System.currentTimeMillis();
            System.out.println(l1-l);

            for (int i = 1; i <= 10000; i++) {

                System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread().getName() + "\t 办理业务");

            }
            System.out.println(System.currentTimeMillis() - l1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();//关闭池子
        }

    }


}
