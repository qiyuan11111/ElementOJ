package com.study.spring.context;

import com.study.spring.beans.BeanPostProcessor;
import com.study.spring.beans.PreBuildBeanFactory;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

public abstract class AbstractApplicationContext implements ApplicationContext {

	protected PreBuildBeanFactory beanFactory;

	public AbstractApplicationContext() {
		super();
		this.beanFactory = new PreBuildBeanFactory();
	}

	protected void refresh() throws Throwable {

		beanFactory.registerTypeMap();

		doRegisterBeanPostProcessor();

		beanFactory.preInstantiateSingletons();

	}

	private void doRegisterBeanPostProcessor() throws Throwable {
		// 从BeanFactory中得到所有用户配置的BeanPostProcessor类型的Bean实例，注册到BeanFactory
		List<BeanPostProcessor> beanPostProcessors = beanFactory.getBeansOfTypeList(BeanPostProcessor.class);
		if(CollectionUtils.isNotEmpty(beanPostProcessors)){
			for (BeanPostProcessor bpp : beanPostProcessors) {
				beanFactory.registerBeanPostProcessor(bpp);
			}
		}
	}

	@Override
	public Object getBean(String name) throws Throwable {
		return beanFactory.getBean(name);
	}

	@Override
	public <T> T getBean(Class<T> type) throws Throwable {
		return this.beanFactory.getBean(type);
	}

	@Override
	public <T> Map<String, T> getBeansOfType(Class<T> type) throws Throwable {
		return this.beanFactory.getBeansOfType(type);
	}

	@Override
	public Class<?> getType(String name) throws Throwable {
		return this.beanFactory.getType(name);
	}

	@Override
	public void registerBeanPostProcessor(BeanPostProcessor bpp) {
		this.beanFactory.registerBeanPostProcessor(bpp);
	}

	@Override
	public <T> List<T> getBeansOfTypeList(Class<T> type) throws Throwable {
		return this.beanFactory.getBeansOfTypeList(type);
	}

}
