package com.yu.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class demo03 {


    public static void main(String[] args) {
        Date3 date = new Date3();

        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                date.printA();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                date.printB();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                date.printC();
            }
        },"C").start();

    }

}

class Date3{

    private Lock lock = new ReentrantLock();
    //要顺序执行就建立三个监视器
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    //设置一个标志位
    private int flag = 1;

   public void printA(){
       lock.lock();
       try {
            //业务， 判断 --> 执行 -->通知
            while (flag != 1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>AAAAAAAAA");
            flag = 2;
            condition2.signal();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           lock.unlock();
       }

   }
   public void printB(){

       lock.lock();
       try {
           while (flag != 2){
               condition2.await();
           }
           System.out.println(Thread.currentThread().getName() + "=>BBBBBBBBBB");
           flag = 3;
           condition3.signal();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           lock.unlock();
       }


   }
   public void printC(){
       lock.lock();
       try {
           while (flag != 3){
               condition3.await();
           }
           System.out.println(Thread.currentThread().getName() + "=>CCCCCCCCCCC");
           flag = 1;
           condition1.signal();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           lock.unlock();
       }

   }

}
