package com.study.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


//取消List的volatile并增加额外volatile字段
public class WithoutVolatile {

    private final int a = 1;

    public static final int cc = 221;

    public int dd = 331;

    public static void main(String[] args) {
        String a = new String("a") + new String("a");
        a.intern();

        String b = new String("aa");
        System.out.println(b.intern() == a);
    }

}


