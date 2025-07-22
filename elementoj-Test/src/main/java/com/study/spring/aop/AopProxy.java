package com.study.spring.aop;

public interface AopProxy {
	/**
	 * Create a new proxy object.
	 */
	Object getProxy();

	/**
	 * Create a new proxy object.
	 */
	Object getProxy(ClassLoader classLoader);

}
