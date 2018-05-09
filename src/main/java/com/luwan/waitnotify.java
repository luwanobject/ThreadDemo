package com.luwan;

/**
 * Created by luwan on 2018/5/9.
 */
class ShareDta{
    private int number=0;


    public synchronized void increat() throws InterruptedException {
        //1.判断
        if(number!=0){
            this.wait();
        }
       //2、干活
        ++number;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3、通知
        this.notifyAll();

    }

    public synchronized void decreat() throws InterruptedException {
        //1.判断
        if(number==0){
            this.wait();
        }
        //2、干活
        --number;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3、通知
        this.notifyAll();

    }
}
public class waitnotify {
    public static void main(String[] args) {
        ShareDta shareDta=new ShareDta();
        new Thread(()->{
            try {
                for (int i=0;i<10;i++) {
                    shareDta.increat();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();
        new Thread(()->{
            try {
                for (int i=0;i<10;i++){
                    shareDta.decreat();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }

}
