package com.study.spring.aop.advice;

import java.lang.reflect.Method;

public interface ThrowsAdvice extends Advice {

    void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable;
}
