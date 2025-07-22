package com.study.spring.aop;

import com.study.spring.beans.BeanPostProcessor;

public class AdvisorAutoProxyCreator_v1 implements BeanPostProcessor {
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws Throwable {

		/*逻辑
		1 判断Bean是否需要增强
		2 创建代理来实现增强
		*/

		return bean;
	}
}
