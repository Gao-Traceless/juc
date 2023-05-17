package com.gao.lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//第一步 创建资源类
class ShareResqurce{
    //定义标志位
    private int flag = 1; // 1 AA 2 BB 3 CC
    //创建Lock锁
    private Lock lock = new ReentrantLock();

    //创建三个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //打印5次 参数第几轮
    public void print5(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try{
            while (flag != 1){
                //等待
                c1.await();
            }
            for (int i = 1; i <= 5; i++){
                System.out.println(Thread.currentThread().getName() + " :: " + i + " : 轮数: " + loop);
            }
            flag = 2; //修改标志位
            c2.signal();
        }finally{
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try{
            while (flag != 2){
                c2.await();
            }
            for (int i = 1; i <= 10; i++){
                System.out.println(Thread.currentThread().getName() + " :: " + i + " : 轮数: " + loop);
            }
            flag = 3;
            c3.signal();
        }finally{
            lock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try{
            while (flag != 3){
                c3.await();
            }
            for (int i = 1; i <= 15; i++){
                System.out.println(Thread.currentThread().getName() + " :: " + i + " : 轮数: " + loop);
            }
            flag = 1;
            c1.signal();
        }finally{
            lock.unlock();
        }
    }


}

public class ThreadDemo3 {

    public static void main(String[] args) {

        ShareResqurce shareResqurce = new ShareResqurce();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++){
                try {
                    shareResqurce.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++){
                try {
                    shareResqurce.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++){
                try {
                    shareResqurce.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

    }

}
