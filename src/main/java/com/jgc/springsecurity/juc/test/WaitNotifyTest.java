package com.jgc.springsecurity.juc.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-11-22 11:27
 */
public class WaitNotifyTest {

    private final static Object obj = new Object();

    public static class T1 extends Thread {

        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis() + "  T1 start");
                try {
                    System.out.println(System.currentTimeMillis() + "  T1 wait");
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis() + "  T1 end");
            }
        }
    }

    public static class T2 extends Thread {

        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis() + "  T2 start");
                System.out.println(System.currentTimeMillis() + "  T2 notify");

                //
                obj.notify();
                for(int i = 0;i < 10000; i++) {
                    System.out.println(i);
                }
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis() + "  T2 end");
            }
        }
    }

    public static void main(String[] args) {
//        T1 t1 = new T1();
//        T2 t2 = new T2();
//        t1.start();
//        t2.start();

        ConcurrentHashMap cmap = new ConcurrentHashMap<>();
        cmap.put("k1", "v1");
        String str = new String("abc");
        System.out.println("hashcode:" + str.hashCode());

    }


}
