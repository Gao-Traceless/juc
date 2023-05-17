package com.yu.demo;

public class demo01 {

    public static void main(String[] args) {

        Date1 date1 = new Date1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date1.addition();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    date1.subtraction();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

    }

}

class Date1{
    private int number = 0;
    //+1
    public synchronized void addition() throws InterruptedException {
        if (number != 0){ //if只适合两个线程的多线程会出现虚假唤醒
            //等待
            this.wait();
        }

        number++;
        System.out.println(Thread.currentThread().getName() + "======>" +number);
        //通知
        this.notifyAll();


    }

    public synchronized void subtraction() throws InterruptedException {
        if (number != 1){
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "======>" + number);

        //通知
        this.notifyAll();

    }


}
