package com.luwan;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by luwan on 2018/5/9.
 */
class  Mythred2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {

        Thread.sleep(2000);
        return 200;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> ft=new FutureTask<Integer>(new Mythred2());
        new Thread(ft,"tt").start();



        System.out.println(Thread.currentThread().getName()+"**********我是上课主线程");

        Integer integer = ft.get();

        System.out.println("**********Result"+integer);

    }
}
