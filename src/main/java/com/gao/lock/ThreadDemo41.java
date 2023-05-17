package com.gao.lock;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class ThreadDemo41 {

    public static void main(String[] args) {

        //演示hashset
//        Set<String> set = new HashSet<>();
        //通过CopyOnWriteArraySet解决HashSet线程不安全问题
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                //向集合添加内容
                set.add(UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(set);
            },String.valueOf(i)).start();
        }


    }

}
