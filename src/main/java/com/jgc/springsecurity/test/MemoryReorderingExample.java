package com.jgc.springsecurity.test;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-11-29 14:40
 */
public class MemoryReorderingExample {

    private static int x=0,y=0;
    private static int a=0,b=0;
    public static void main(String[] args) throws InterruptedException {
        int i=0;
        while(true){
            i++;
            x=0;y=0;
            a=0;b=0;
            Thread t1=new Thread(()->{
                a=1;
                x=b;
            });
            Thread t2=new Thread(()->{
                b=1;
                y=a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            String result="第"+i+"次("+x+","+y+")";
            if(x==0&&y==0){
                System.out.println(result);
                break;
            } else {
                if (i%100000 == 0) {
                    System.out.println("第" + i + "次循环");
                }
            }
        }
    }
}
