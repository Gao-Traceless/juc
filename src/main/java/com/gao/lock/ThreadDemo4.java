package com.gao.lock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * list集合线程不安全问题
 */
public class ThreadDemo4 {

    public static void main(String[] args) {
        //创建ArrayList集合
//        ArrayList<String> list = new ArrayList<>();
        //通过Vector解决ArrayList集合线程不安全问题
//        Vector<String> list = new Vector<>();
        //通过Collections解决ArrayList集合线程不安全问题
//        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        //通过CopyOnWriteArrayList解决ArrayList集合线程不安全问题
        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 50; i++){
            new Thread(() -> {

                //向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(list);

            },String.valueOf(i)).start();
        }


    }

}
