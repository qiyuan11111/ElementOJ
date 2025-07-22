package com.study.spring.context;

import com.study.spring.beans.BeanDefinitionRegistry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClassPathBeanDefinitionScanner_v0 {

	private static Log logger = LogFactory.getLog(ClassPathBeanDefinitionScanner_v0.class);

	private BeanDefinitionRegistry registry;

	public ClassPathBeanDefinitionScanner_v0(BeanDefinitionRegistry registry) {
		super();
		this.registry = registry;
	}

	public void scan(String... basePackages) throws Throwable {
		if (basePackages != null && basePackages.length > 0) {
			for (String p : basePackages) {
				/*
				 1 递归扫描包目录下的.class文件
				 2 组合包路径+class文件名 得到全限定类名
				 3 ClassLoad.load("类名") 得到 Class 对象
				 4 解析Class上的注解，获得Bean定义信息，注册Bean定义
				 */
			}
		}
	}
}
