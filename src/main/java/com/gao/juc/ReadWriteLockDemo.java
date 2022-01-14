package com.gao.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类，没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源来，就不应该在有其他线程可以对该资源进行读或写
 * 小总结：
 *      读--读能共存
 *      读--写不能共存
 *      写--写不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "",tempInt + "");
            }, String.valueOf(i)).start();

        }

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
            }, String.valueOf(i)).start();

        }

    }
}

class MyCache{

    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLook = new ReentrantReadWriteLock();

    public void put(String key,Object value){

        readWriteLook.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t  -----写入数据" + key);
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "\t  -----写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLook.writeLock().unlock();
        }




    }

    public void get(String key){
        readWriteLook.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 读取数据");
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLook.readLock().unlock();
        }


    }

}