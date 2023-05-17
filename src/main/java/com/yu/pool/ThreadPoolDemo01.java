package com.yu.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//演示线程池三种常用分类
public class ThreadPoolDemo01 {

    public static void main(String[] args) {
        //一池N线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);

        //一池一线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();

        //一池可扩容线程
        ExecutorService threadPool = Executors.newCachedThreadPool();


        //10个顾客请求
        try{
            for (int i = 0; i < 10; i++){
                //执行
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //关闭
            threadPool.shutdown();
        }



    }

}
