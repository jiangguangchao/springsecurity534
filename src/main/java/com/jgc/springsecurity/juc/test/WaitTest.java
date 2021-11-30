package com.jgc.springsecurity.juc.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-11-30 09:11
 */
public class WaitTest {

    private static Object obj = new Object();

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                atomicInteger.incrementAndGet();
                for(int i = 0; i < 100 ; i++) {
                    System.out.println("线程：" + Thread.currentThread().getName() + "1111111111---------" + i);
                    if (atomicInteger.get() < 2) {
                        if (i == 50) {
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
            System.out.println("线程：" + Thread.currentThread().getName() + "结束");
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                atomicInteger.incrementAndGet();
                for(int i = 0; i < 100 ; i++) {
                    System.out.println("线程：" + Thread.currentThread().getName() + "222222222--------" + i);
                    if (atomicInteger.get() < 2) {
                        if (i == 50) {
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
            System.out.println("线程：" + Thread.currentThread().getName() + "结束");
        }, "t2");

        Thread t3 = new Thread(() -> {
            synchronized (obj) {
                atomicInteger.incrementAndGet();
                for(int i = 0; i < 100 ; i++) {
                    System.out.println("线程：" + Thread.currentThread().getName() + "3333333333---------" + i);

                    if (atomicInteger.get() == 1 || atomicInteger.get() == 2) {
                        if (i == 50) {
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
            System.out.println("线程：" + Thread.currentThread().getName() + "结束");
        }, "t3");

        t1.start();
        t2.start();
//        t3.start();
//        t1.join();
//        synchronized (t1) {
////            while (t1.isAlive()) {
////                System.out.println("开始释放ti对象锁");
////                t1.wait(0);
////                System.out.println("重新得到ti对象锁");
////                System.out.println("t1 alave: " + t1.isAlive());
////            }
//            if (t1.isAlive()) {
//                t1.wait(0);
//            }
//
//            System.out.println("再次得到t1对象锁");
//        }

        System.out.println("main end : " + System.currentTimeMillis());

    }

    public synchronized void f1() throws InterruptedException {
        wait(10L);
    }
}
