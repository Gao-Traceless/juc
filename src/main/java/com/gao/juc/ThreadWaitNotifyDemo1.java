package com.gao.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadWaitNotifyDemo1 {

    public static void main(String[] args) throws Exception{

        AirConditioner1 airConditioner = new AirConditioner1();

        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();


    }

}

//资源类
class AirConditioner1{

    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException{

        lock.lock();

        try {
            //1、判断
            while (number != 0){

                condition.await();
            }

            //2、干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            //3、通知
            condition.signalAll();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException{

        lock.lock();

        try {
            //1、判断
            while (number == 0){

                condition.await();
            }

            //2、干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);

            //3、通知
            condition.signalAll();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

}