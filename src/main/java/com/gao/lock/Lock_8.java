package com.gao.lock;


import java.util.concurrent.TimeUnit;

class Phone{
    public synchronized void sendSMS() throws Exception{
        //停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("---------sendSMS");
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println("---------sendEmail");
    }

    public void getHello(){
        System.out.println("---------getHello");
    }

}

/**
 * 8锁
 * 1、标注访问，先打印短信还是邮件
 *  ---------sendSMS
 *  ---------sendEmail
 * 2、停4秒在短信方法内，先打印短信还是邮件
 *  ---------sendSMS
 *  ---------sendEmail
 * 3、新增普通的hello方法，是先打印短信还是hello
 *  3.1、停了4秒
 *      ---------getHello
 *      ---------sendSMS
 *  3.2、没停
 *      ---------sendSMS
 *      ---------getHello
 * 4、现在有两部手机，先打印短信还是邮件
 *  4.1、没停
 *      ---------sendSMS
 *      ---------sendEmail
 *  4.2、停了4秒
 *      ---------sendEmail
 *      ---------sendSMS
 * 5、两个静态同步方法，1部手机，先打印短信还是邮件
 *      ---------sendSMS
 *      ---------sendEmail
 * 6、两个静态同步方法，2部手机，先打印短信还是邮件
 *      ---------sendSMS
 *      ---------sendEmail
 * 7、1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 *   7.1、没停
 *      ---------sendSMS
 *      ---------sendEmail
 *  7.2、停了4秒
 *      ---------sendEmail
 *      ---------sendSMS
 * 8、1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
 *  8.1、没停
 *      ---------sendSMS
 *      ---------sendEmail
 *  8.2、停了4秒
 *      ---------sendEmail
 *      ---------sendSMS
 */

public class Lock_8 {

    public static void main(String[] args) throws InterruptedException {

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try{
                phone.sendSMS();
            }catch(Exception e){
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            try{
//                phone.sendEmail();
//                phone.getHello();
                phone2.sendEmail();
            }catch(Exception e){
                e.printStackTrace();
            }
        }, "BB").start();


    }

}
