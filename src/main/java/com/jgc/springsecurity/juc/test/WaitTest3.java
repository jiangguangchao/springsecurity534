package com.jgc.springsecurity.juc.test;

import org.springframework.security.core.annotation.CurrentSecurityContext;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-11-30 18:00
 */

public class WaitTest3 {

    public static Object obj = new Object();

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        MyThread3 t1 = new MyThread3("t1");
        MyThread3 t2 = new MyThread3("t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

}

class MyThread3 extends Thread {
    public MyThread3(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (WaitTest3.obj) {
            WaitTest3.atomicInteger.incrementAndGet();
            try {
                for(int i = 0; i < 100 ; i++) {
                    System.out.println("线程：" + Thread.currentThread().getName() + "--" + i);
                    if (i == 50 && WaitTest3.atomicInteger.get() < 2) {
                        this.wait();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


