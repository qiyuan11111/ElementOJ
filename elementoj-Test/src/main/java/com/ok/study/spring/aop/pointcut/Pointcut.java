package com.study.spring.aop.pointcut;

import java.lang.reflect.Method;

public interface Pointcut {

	boolean matchsClass(Class<?> targetClass);

	boolean matchsMethod(Method method, Class<?> targetClass);
}
