package com.gao.sync;

//第一步：创建资源类，定义属性和操作方法
class Share{
    //初始值
    private int number = 0;
    //+1的方法
    public synchronized void incr() throws InterruptedException {
        //判断 干活  通知
        /*if (number != 0){ //判断number值是否是0，如果不是0，等待
            //
            this.wait();//在哪里睡就在哪里醒，if醒后会往下继续执行,导致虚假唤醒问题
        }*/
        while (number != 0){
            this.wait();
        }
        //如果number值是0，就+1操作
        number++;
        System.out.println(Thread.currentThread().getName() + " :: " + number);
        //通知其他线程
        this.notifyAll();
    }

    //-1的方法
    public synchronized void decr() throws InterruptedException {
        //判断
        /*if (number != 1){
            this.wait(); //在哪里睡就在哪里醒，if醒后会往下继续执行
        }*/
        while (number != 1){
            this.wait();
        }
        //如果number值是1,就-1操作
        number--;
        System.out.println(Thread.currentThread().getName() + " :: " + number);
        //通知其他线程
        this.notifyAll();
    }

}

public class ThreanDemo1 {

    public static void main(String[] args) {

        Share share = new Share();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();

    }

}
