package com.study.spring.samples;

import com.study.spring.context.AnnotationApplicationContext;
import com.study.spring.context.ApplicationContext;

public class ApplicationContextTest {
    public static void main(String[] args) throws Throwable {
        ApplicationContext context = new AnnotationApplicationContext("com.study.spring.samples");
        //--- IOC容器准备好了，可以用了，来获取Bean吧！！

        ABean abean = (ABean) context.getBean("aBean");
        abean.doSomthing();

    }
}
