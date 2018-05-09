package com.luwan;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by luwan on 2018/5/9.
 */
class outSay{

    Lock lock=new ReentrantLock();
    Condition c1=lock.newCondition();
    Condition c2=lock.newCondition();
    Condition c3=lock.newCondition();
    private int num=0;
    public void printAA(){
        lock.lock();
        try {
            //1、判断
            while (num!=0){
                c1.await();
            }
            //2、干活
            for (int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+"AA");
            }
            //3、通知
            c2.signal();
            num=1;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printBB(){
        lock.lock();
        try {
            //1、判断
            while (num!=1){
                c2.await();
            }
            //2、干活
            for (int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+"BB");
            }
            //3、通知
            c3.signal();
            num=2;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printCC(){
        lock.lock();
        try {
            //1、判断
            while (num!=2){
                c3.await();
            }
            //2、干活
            for (int i=0;i<15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+"CC");
            }
            //3、通知
            c1.signal();
            num=0;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
public class ThreadDemo4 {
    public static void main(String[] args) {
        outSay outSay=new outSay();

        new Thread(()->{
            for (int i=0;i<20;i++){
                outSay.printAA();
            }

        },"AA").start();

        new Thread(()->{
            for (int i=0;i<20;i++){
                outSay.printBB();
            }

        },"BB").start();

        new Thread(()->{
            for (int i=0;i<20;i++){
                outSay.printCC();
                System.out.println();
            }

        },"CC").start();
    }
}
