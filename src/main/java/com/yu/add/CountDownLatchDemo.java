package com.yu.add;

import java.util.concurrent.CountDownLatch;

//计数器
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        //总数是6  必须要执行任务的时候，再使用
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "Go out");
                countDownLatch.countDown();//数量减一
            }, String.valueOf(i)).start();

        }
        //等待计数器归零，然后再向下执行
        countDownLatch.await();

        System.out.println("完毕");
    }

}
