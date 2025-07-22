package com.study.spring.beans;

/**
 * 用于依赖注入中描述bean依赖
 */
public class BeanReference {

	private String beanName;

	private Class<?> type;

	public BeanReference(String beanName) {
		super();
		this.beanName = beanName;
	}

	public BeanReference(Class<?> type) {
		this.type = type;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}
}
