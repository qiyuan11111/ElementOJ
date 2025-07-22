package com.study.spring.context;

import com.study.spring.beans.BeanDefinitionRegistry;

public class AnnotationApplicationContext extends AbstractApplicationContext {

	public AnnotationApplicationContext(String... basePackages) throws Throwable {
		super();
		// 找到所有的被 @Componment 修饰的Java类的BeanDefinition
		new ClassPathBeanDefinitionScanner((BeanDefinitionRegistry) this.beanFactory).scan(basePackages);
		super.refresh();
	}
}
