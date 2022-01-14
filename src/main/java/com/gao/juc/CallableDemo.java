package com.gao.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * java
 * 多线程中，第3种获得多线程的方式
 *
 * 1、   get方法一般请放在最后一行
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        MyThread myThread = new MyThread();
//        Thread t1 = new Thread();
//        t1.start();

        FutureTask futureTask = new FutureTask(new MyThread());

        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();
//        System.out.println(futureTask.get());

        System.out.println(Thread.currentThread().getName()+ "*****计算完成");

        System.out.println(futureTask.get());

    }

}

//class MyThread implements Runnable{
//
//    @Override
//    public void run() {
//
//    }
//
//}

/**
 * callable接口与runnable接口的区别？
 *  (1)、是否有返回值
 *  (2)、是否抛出异常
 *  (3)、落地方法不一样，一个是run，一个是call
 */
class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("***********come in here");
        //先暂停一会线程
        try {
            TimeUnit.SECONDS.sleep(4);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return 1024;
    }

}