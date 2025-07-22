package com.study2.spring.beans;

import com.study.spring.beans.BeanDefinition;
import com.study.spring.beans.BeanDefinitionRegistException;

public interface BeanDefinitionRegistry {

	void registerBeanDefinition(String beanName, com.study.spring.beans.BeanDefinition beanDefinition) throws BeanDefinitionRegistException;

	BeanDefinition getBeanDefinition(String beanName);

	boolean containsBeanDefinition(String beanName);

}
