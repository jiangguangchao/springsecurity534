package com.jgc.springsecurity.juc.test;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-11-30 16:37
 */
public class WaitTest2 {

    public static Object obj = new Object();
    public static MyThread t1 = new MyThread("t1");

    public static void main(String[] args) throws InterruptedException {
//        MyThread t1 = new MyThread("t1");

        Thread t2 = new Thread(() -> {
            synchronized (t1) {
                for(int i = 0; i < 100 ; i++) {
                    System.out.println("线程：" + Thread.currentThread().getName() + "--" + i);
                    if (i == 50) {
                        try {
                            t1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "t2");

        synchronized (t1) {
            System.out.println("开始启动t1： " + System.currentTimeMillis());
            t2.start();
            t1.start();
            for(int i = 0; i < 100 ; i++) {
                System.out.println("线程：" + Thread.currentThread().getName() + "--" + i);
                if (i == 50) {
                    t1.wait();
                }
            }
        }
    }

}

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        synchronized (WaitTest2.t1) {
            try {
//                System.out.println("开始休眠1秒钟： " + System.currentTimeMillis());
//                Thread.sleep(1000l);
//                System.out.println("结束休眠1秒钟： " + System.currentTimeMillis());

                for(int i = 0; i < 100 ; i++) {
                    System.out.println("线程：" + Thread.currentThread().getName() + "--" + i);
//                    if (i == 50) {
//                        this.notify();
//                        this.wait();
//                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
