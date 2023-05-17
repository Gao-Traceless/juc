package com.gao.hmjuc.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test01")
public class Test01 {

    public static void main(String[] args) {

        Thread t = new Thread(){

            @Override
            public void run(){
                log.debug("running");
            }
        };

        t.start();

        log.debug("running..");

    }

}
