package com.yu.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //new Thread(new Runnable()).start();
        //new Thread(new FutureTask<V>()).start();
        //new Thread(new FutureTask<V>(Callable)).start();
        //new Thread().start();//怎么启动Callable

        MyThread2 thread = new MyThread2();
        FutureTask futureTask = new FutureTask(thread);

        new Thread(futureTask,"A").start();
        //这里结果会被缓存提高效率所以只会打印一个call
        new Thread(futureTask,"B").start();
        //get方法可能会产生阻塞，因为要等待返回值如果返回的是一个需要耗时的结果就会产生等待阻塞，把他放到最后，或者使用异步通信来处理
        Object o = (Integer)futureTask.get();
        System.out.println(o);

    }

}

class MyThread implements Runnable{

    @Override
    public void run() {

    }

}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("call");
        return 456789;
    }
}