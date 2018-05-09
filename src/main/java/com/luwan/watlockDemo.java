package com.luwan;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by luwan on 2018/5/9.
 */
class Share{
    private  int number=0;

    Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    public  void increat(){
        lock.lock();
        try {
            //1、判断
            while (number != 0) {
                c1.await();
            }
            //2、干活
            ++number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3、通知
            c2.signal();

            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
             }

        }
    public  void decreat(){
        lock.lock();
        try {
            //1、判断
            while (number == 0) {

                c2.await();

            }
            //2、干活
            --number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3、通知
            c1.signalAll();

        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}


public class watlockDemo {
    public static void main(String[] args) {
        Share s=new Share();

        new Thread(()->{
            for (int i=0;i<10;i++){
                s.increat();
            }
        },"AA"
        ).start();
   /*     new Thread(()->{
            for (int i=0;i<10;i++){
                s.increat();
            }
        },"DD"
        ).start();*/
        new Thread(()->{
            for (int i=0;i<10;i++){
                s.decreat();
            }
        },"BB"
        ).start();
    /*    new Thread(()->{
            for (int i=0;i<10;i++){
                s.decreat();
            }
        },"CC"
        ).start();*/
    }
}
