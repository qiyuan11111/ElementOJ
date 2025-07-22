package com.study2.spring.beans;

import java.util.Map;

public interface BeanFactory {

	Object getBean(String name) throws Exception;

	<T> T getBean(Class<T> type)throws Exception;

	<T> Map<String,T> getBeansOfType(Class<T> type)throws Exception;

	Class<?> getType(String name) throws Exception;
}
