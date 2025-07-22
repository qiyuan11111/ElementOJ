package com.study.spring.aop.advisor;

public interface Advisor {

	String getAdviceBeanName();

	String getExpression();
}
