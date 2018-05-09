package com.luwan;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by luwan on 2018/5/9.
 */
public class CopListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList list=new CopyOnWriteArrayList();
//        ArrayList list=new ArrayList();
        for (int i=0;i<20;i++){
           new Thread(()->{
               list.add(UUID.randomUUID().toString());
               System.out.println(list);
           }).start();
        }
    }
}
