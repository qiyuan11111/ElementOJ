package com.study.spring.beans;

import java.util.List;
import java.util.Map;

public interface BeanFactory {
	Object getBean(String name) throws Throwable;

	<T> T getBean(Class<T> type)throws Throwable;

	<T> Map<String,T> getBeansOfType(Class<T> type)throws Throwable;

	Class<?> getType(String name) throws Throwable;

	void registerBeanPostProcessor(BeanPostProcessor bpp);

	<T> List<T> getBeansOfTypeList(Class<T> type) throws Throwable;
}
