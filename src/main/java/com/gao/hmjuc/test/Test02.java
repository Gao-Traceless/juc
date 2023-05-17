package com.gao.hmjuc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test02")
public class Test02 {

    public static void main(String[] args) {

        Runnable r = new Runnable(){

            @Override
            public void run() {
                log.debug("running---");
            }
        };

        Thread t = new Thread(r, "r1");
        t.start();


        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                log.debug("running.....");
                Thread.sleep(1000);
                return null;
            }
        });




    }

}
