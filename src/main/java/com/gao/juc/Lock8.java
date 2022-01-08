package com.gao.juc;

import java.util.concurrent.TimeUnit;

/**
 * 题目：多线程8锁
 * 1、标准访问，请问先打印邮件还是短信？  ----------先打印邮件
 * 2、邮件方法暂停4秒，请问先打印邮件还是短信？  ----------先打印邮件
 * 3、新增一个普通方法，请问先打印哪一个  ----------先打印hello
 * 4、两部手机，请问先打印邮件还是短信？  ----------先打印短信
 * 5、两个静态同步方法，同一部手机，请问先打印邮件还是短信？    ----------先打印邮件
 * 6、两个静态同步方法，两部手机，请问先打印邮件还是短信？     ----------先打印邮件
 * 7、一个普通同步方法，两个静态同步方法，一部手机，请问先打印邮件还是短信？    ----------先打印短信
 * 8、一个普通同步方法，两个静态同步方法，两部手机，请问先打印邮件还是短信？    ----------先打印短信
 *
 * 笔记：
 *      一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中一个synchronized方法了，
 *      其他的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
 *      锁的是当前对象this，被锁定后，其他的线程都不能进入到当前对象的其它的synchronized方法
 *
 *      加个普通方法后发现和同步锁无关
 *      换成两个对象后，不是同一把锁了，情况立刻变化。
 *
 *      都换成静态同步方法后，情况又变化
 *      所有的非静态同步方法用的都是同一把锁--实例对象本身
 *
 *      synchronized实现同步的基础：Java中的每一个对象都可以作为锁。
 *      具体表现为一下3种形式。
 *      对于普通同步方法，锁是当前实例对象。
 *
 */
public class Lock8 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                phone.sendSMS();
//                phone.hello();
//                phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();


    }
}

class Phone{
    public static synchronized void sendEmail()throws Exception{
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---------sendEmail");
    }
    public synchronized void sendSMS()throws Exception{
        System.out.println("----------sendSMS");
    }

    public void hello(){
        System.out.println("-------hello");
    }

}