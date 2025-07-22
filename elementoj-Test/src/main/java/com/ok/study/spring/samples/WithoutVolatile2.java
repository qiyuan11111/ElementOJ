package com.study.spring.samples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WithoutVolatile2 {
    private volatile static List<Integer> list;

    static {
        list  = new ArrayList<Integer>(20);
        list.add(0);
        list.add(0);
    }


    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.set(0,1);
            System.out.println("set 1");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.set(1,1);
        }).start();
        CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList<Integer>();
        new Thread(()->{
            List<Integer> l;
            while ((l=list).get(0) == 0){
            }
            System.out.println("end");
            while (l.get(1) == 0){
            }

        }).start();
    }

}
