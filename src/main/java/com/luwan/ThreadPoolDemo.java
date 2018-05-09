package com.luwan;

import javafx.concurrent.ScheduledService;
import sun.awt.shell.ShellFolder;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by luwan on 2018/5/9.
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        ScheduledFuture<Integer> result=null;
        try {
            for(int i=1;i<20;i++){
                result=service.schedule(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        System.out.print(Thread.currentThread().getName()+"\t");
                        return new Random().nextInt(10);
                    }
                },1,TimeUnit.SECONDS);

                System.out.println("-------------"+result.get());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            service.shutdown();
        }
    }
}
