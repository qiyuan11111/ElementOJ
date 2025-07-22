package com.study.spring.samples;

public class Class1 implements Interf3,Interf4{
    public static void main(String[] args) {
        System.out.println(Interf3.class.getInterfaces().length);
        System.out.println(Interf3.class.getSuperclass());
        System.out.println(Class1.class.getSuperclass());
        System.out.println(Class1.class.getInterfaces().length);
    }
}
