package com.gao.callable;

//比较callable和Runnable两个接口的区别

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//实现Runnable接口
class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}

//实现Callable接口
class MyThread2 implements Callable{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "进入到Callable里面来");
        return 200;
    }
}

public class Demo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Runnable接口创建线程
        new Thread(new MyThread1(), "AA").start();

        //Callable接口创建
        //FutureTask
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());

        //lam表达式
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "进入到Callable里面来");
            return 1024;
        });

        //创建一个线程
        new Thread(futureTask2,"lucy").start();
        new Thread(futureTask2,"lucy").start();//结果会被缓存，所以只会打印一遍1024
        new Thread(futureTask1,"mary").start();

        /*while (!futureTask2.isDone()){
            System.out.println("wait......");
        }*/

        //调用FutureTask的get方法,获取返回结果，这个get方法可能会产生阻塞，如果call()方法是一个耗时的方法就有可能产生阻塞现象-----解决方式，使用异步的通信的方式来处理
        System.out.println(futureTask2.get());
        System.out.println(futureTask1.get());

        System.out.println(Thread.currentThread().getName() + " 结束");

        //FutureTask原理 未来任务
        /**
         * 1、老师上课，口渴了，去买水不合适，讲课线程继续。
         *  单开启线程找班上班长帮我买水
         *  把水买回来，需要时候直接get
         *
         * 2、4个同学，1同学1+2...5  ，   2同学 10+11+12...50，   3同学 60+61+62 ， 4同学 100+200
         *      第2个同学计算量比较大
         *      FutureTask单独开启一个线程给2同学，先汇总1 3 4，最后等2同学计算完成，统一汇总
         * 3、考试，做会做的题目，最后看不会做的题目
         *
         * 汇总一次
         */



    }

}
