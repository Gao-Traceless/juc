package com.gao.pool;

import java.util.concurrent.*;

public class MyThreadPoolDemo {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());//获取CPU核数

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            //模拟有10个顾客过来银行办理业务，目前池子里面有5个工作人员提供服务
            for (int i = 1; i <= 10; i++) {
                //暂停毫秒
//                try {TimeUnit.SECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }

    private static void initPool() {
        //        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个受理线程，类似一个银行有5个柜台
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个受理线程，类似一个银行有1个柜台
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池n个受理线程，类似一个银行有n个柜台

        try {
            //模拟有10个顾客过来银行办理业务，目前池子里面有5个工作人员提供服务
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
            //暂停毫秒
            try {TimeUnit.SECONDS.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

}
