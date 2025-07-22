package com.study.spring.aop.advisor;

import com.study.spring.aop.pointcut.Pointcut;

public interface PointcutAdvisor extends Advisor {

	Pointcut getPointcut();
}
