package com.luwan;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by luwan on 2018/5/9.
 */
class MyQueue {
    private Object object;



    private ReentrantReadWriteLock rewLock=new ReentrantReadWriteLock();

    public void writeObject(Object object){
        rewLock.writeLock().lock();
        try{
            this.object=object;
            System.out.println(Thread.currentThread().getName()+"\t"+object);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rewLock.writeLock().unlock();
        }
    }
    public void redeObject(){
        rewLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t"+object);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rewLock.readLock().lock();
        }
    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        MyQueue myQueu=new MyQueue();

        new Thread(()->{
            myQueu.writeObject("ClassName108");
        },"riteThred").start();
        Thread.sleep(100);
        for (int i=0;i<=100;i++){
            new Thread(()->{
                myQueu.redeObject();
            },String.valueOf(i)).start();
        }

    }
}
