package com.luwan;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by luwan on 2018/5/9.
 */
class ShareResouce{
    Lock lock=new ReentrantLock();
    Condition c1=lock.newCondition();
    Condition c2=lock.newCondition();
    Condition c3=lock.newCondition();
    //1-A  2-B 3-C
    private int number=1;

    public void printA(int totalLop){
        lock.lock();
        try {
            //1、判断
            while (number!=1){
                c1.await();
            }
            //2、干活
            for (int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+"AAA"+"\t"+totalLop);
            }
            //3、通知
            number=2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
    public void printB(int totalLop){
        lock.lock();
        try {
            //1、判断
            while (number!=2){
                c2.await();
            }
            //2、干活
            for (int i=1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+"BBB"+"\t"+totalLop);
            }
            //3、通知
            number=3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
    public void printC(int totalLop){
        lock.lock();
        try {
            //1、判断
            while (number!=3){
                c3.await();
            }
            //2、干活
            for (int i=1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+"CCC"+"\t"+totalLop);
            }
            //3、通知
            number=1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }


}

public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResouce shareResouce=new ShareResouce();

        new Thread(()->{
            for (int i=1;i<10;i++){
                shareResouce.printA(i);
            }
        },"AA").start();

        new Thread(()->{
            for (int i=1;i<10;i++){
                shareResouce.printB(i);
            }
        },"BB").start();
        new Thread(()->{
            for (int i=1;i<10;i++){
                shareResouce.printC(i);
            }
        },"CC").start();
    }
}
