package com.jgc.springsecurity.juc.test;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-11-29 14:40
 */
public class MemoryReorderingExample2 {

    private static int x=0,y=0;
    private static int a=0,b=0;
    private static volatile boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        int i=0;
        i++;
        Thread t1=new Thread(()->{
            int n = 0;
            while (flag) {
                a = n;
                x = n;
                a = n+1;
                x = n+2;
                n = n + 2;
                if (n == 10000000) {
                    n = 0;
                }
            }

        });
        Thread t2=new Thread(()->{
//                b=1;
//                y=1;
            long j = 0;
            while (flag) {
                j++;
//                String result="第"+j+"次("+x+","+a+")";
                if(x - a == 2){
                    System.out.println("发送了重排序 x:" + x + "   a:" + a);
                    flag = false;
                    break;
                } else {
                    if (j%1000000000 == 0) {
                        System.out.println("第" + j + "次循环");
                    }
                }
            }
        });
        t1.start();
        t2.start();
//        t1.join();
//            t2.join();
    }
}
