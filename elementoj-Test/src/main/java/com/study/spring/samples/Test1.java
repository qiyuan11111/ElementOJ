package com.study.spring.samples;

public class Test1 {
    public static void main(String[] args) {
        try {
            int a = 1/0;
        }catch (Exception e) {
            throw e;
        }finally {
            System.out.println("sssssssssssssssss");
        }
    }
}
