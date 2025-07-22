package com.study.spring.samples;

public class Singleton {

    private static volatile Singleton instance;

    public static Singleton getInstance(){
        //DCL
        if(instance == null){

        }
        return null;
    }
}
