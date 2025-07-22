package com.study.spring.beans;

import org.apache.commons.lang3.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory_v3 implements BeanFactory, BeanDefinitionRegistry, Closeable {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DefaultBeanFactory.class);

	protected Map<String, BeanDefinition> beanDefintionMap = new ConcurrentHashMap<>(256);

	private Map<String, Object> singletonBeanMap = new ConcurrentHashMap<>(256);

	private Map<Class<?>, Set<String>> typeMap = new ConcurrentHashMap<>(256);

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
			throws BeanDefinitionRegistException {
		Objects.requireNonNull(beanName, "注册bean需要给入beanName");
		Objects.requireNonNull(beanDefinition, "注册bean需要给入beanDefinition");

		// 校验给入的bean是否合法
		if (!beanDefinition.validate()) {
			throw new BeanDefinitionRegistException("名字为[" + beanName + "] 的bean定义不合法：" + beanDefinition);
		}

		if (this.containsBeanDefinition(beanName)) {
			throw new BeanDefinitionRegistException(
					"名字为[" + beanName + "] 的bean定义已存在:" + this.getBeanDefinition(beanName));
		}

		this.beanDefintionMap.put(beanName, beanDefinition);
	}

	public void registerTypeMap() throws Exception{
		//1 得到 type -- name 映射关系 准备工作  在所有的Bean定义信息都注册完成后执行
		for(String name : this.beanDefintionMap.keySet()){
			Class<?> type = this.getType(name);
			//映射本类
			this.registerTypeMap(name,type);

			//父类
			this.registerSuperClassTypeMap(name,type);
			//接口
			this.registerInterfaceTypeMap(name,type);
		}

	}

	private void registerInterfaceTypeMap(String name, Class<?> type) {
		Class<?>[] interfaces = type.getInterfaces();
		if (interfaces.length > 0){
			for(Class<?> interf : interfaces) {
				this.registerTypeMap(name, interf);
				//递归找父接口
				this.registerInterfaceTypeMap(name,interf);
			}
		}
	}

	private void registerSuperClassTypeMap(String name, Class<?> type) {
		Class<?> superClass = type.getSuperclass();
		if(superClass != null && !superClass.equals(Object.class)){
			this.registerTypeMap(name,superClass);
			//递归找父类
			this.registerSuperClassTypeMap(name,superClass);
			//找父类实现的接口注册
			this.registerInterfaceTypeMap(name,superClass);
		}
	}

	private void registerTypeMap(String name, Class<?> type) {
		Set<String> names2type = this.typeMap.get(type);
		if(names2type == null){
			names2type = new HashSet<>();
			this.typeMap.put(type,names2type);
		}
		names2type.add(name);
	}

	@Override
	public Class<?> getType(String name) throws Exception{
		BeanDefinition bd = this.getBeanDefinition(name);
		Class<?> type = bd.getBeanClass();
		if (type != null) {
			if (StringUtils.isBlank(bd.getFactoryMethodName())) {
				// 构造方法来构造对象的，Type就是beanClass,不需做什么。
			} else {
				// 静态工厂方法方式的，反射获得Method,再获取Method的返回值类型
				type = type.getDeclaredMethod(bd.getFactoryMethodName(),null).getReturnType();
			}
		} else {
			// 工厂bean方式来构造对象的
			// 获得工厂Bean的Class
			type = this.getType(bd.getFactoryBeanName());
			// 再获得工厂方法的返回值类型
			type = type.getDeclaredMethod(bd.getFactoryMethodName(),null).getReturnType();
		}

		return type;
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanName) {
		return this.beanDefintionMap.get(beanName);
	}

	@Override
	public boolean containsBeanDefinition(String beanName) {

		return this.beanDefintionMap.containsKey(beanName);
	}

	@Override
	public Object getBean(String name) throws Exception {
		return this.doGetBean(name);
	}

	@Override
	public <T> T getBean(Class<T> type) throws Exception {
		/*
		逻辑：
		1 获得其对应的所有的BeanDefinition
		2 如果只有一个，直接获取bean实例返回，否则
		3 遍历找出Primary的
		4 如果primary没有，或大于1个，抛出异常
		5 返回Primary的实例
		 */
		Set<String> names = this.typeMap.get(type);
		if(names != null) {
			if(names.size() == 1){
				return (T)this.getBean(names.iterator().next());
			}
			else {
				//找Primary
				BeanDefinition bd = null;
				String primaryName = null;
				StringBuilder nameStrings = new StringBuilder();
				for(String name : names){
					bd = this.getBeanDefinition(name);
					if(bd != null && bd.isPrimary()) {
						if(primaryName != null){
							String mess = type + " 类型的Bean存储多个Primary[" + primaryName + "," + name + "]";
							log.error(mess);
							throw new Exception(mess);
						}
						else {
							primaryName = name;
						}
					}
					nameStrings.append(" " + name);
				}

				if(primaryName != null){
					return (T)this.getBean(primaryName);
				}
				else {
					String mess = type + " 类型的Bean存在多个[" + nameStrings + "] 但无法确定Primary";
					log.error(mess);
					throw new Exception(mess);
				}
			}
		}
		return null;
	}

	@Override
	public <T> Map<String, T> getBeansOfType(Class<T> type) throws Exception {
		Set<String> names = this.typeMap.get(type);
		if(names != null) {
			Map<String, T> map = new HashMap<>();
			for(String name : names){
				map.put(name,(T) this.getBean(name));
			}
			return map;
		}
		return null;
	}

	protected Object doGetBean(String beanName) throws Exception {
		Objects.requireNonNull(beanName, "beanName不能为空");

		/*单例如何实现？
			单例如何存储？
			如何保证单例？
		*/
		Object instance = singletonBeanMap.get(beanName);

		if (instance != null) {
			return instance;
		}

		BeanDefinition bd = this.getBeanDefinition(beanName);
		Objects.requireNonNull(bd, "beanDefinition不能为空");

		if(bd.isSingleton()) { //如果是单例
			synchronized (this.singletonBeanMap) { //加锁
				instance = this.singletonBeanMap.get(beanName);
				if(instance == null){//第二次检查
					instance = doCreateInstance(bd);
					this.singletonBeanMap.put(beanName,instance);
				}
			}
		}
		else {
			instance = doCreateInstance(bd);
		}

		return instance;
	}

	private Object doCreateInstance(BeanDefinition bd) throws Exception {
		Class<?> type = bd.getBeanClass();
		Object instance = null;
		if (type != null) {
			if (StringUtils.isBlank(bd.getFactoryMethodName())) {
				// 构造方法来构造对象
				instance = this.createInstanceByConstructor(bd);
			} else {
				// 静态工厂方法
				instance = this.createInstanceByStaticFactoryMethod(bd);
			}
		} else {
			// 工厂bean方式来构造对象
			instance = this.createInstanceByFactoryBean(bd);
		}

		// 执行初始化方法
		this.doInit(bd, instance);

		return instance;
	}

	// 构造方法来构造对象
	private Object createInstanceByConstructor(BeanDefinition bd)
			throws InstantiationException, IllegalAccessException {
		try {
			return bd.getBeanClass().newInstance();
		} catch (SecurityException e1) {
			log.error("创建bean的实例异常,beanDefinition：" + bd, e1);
			throw e1;
		}
	}

	// 静态工厂方法
	private Object createInstanceByStaticFactoryMethod(BeanDefinition bd) throws Exception {
		Class<?> type = bd.getBeanClass();
		Method m = type.getMethod(bd.getFactoryMethodName(), null);
		return m.invoke(type, null);
	}

	// 工厂bean方式来构造对象
	private Object createInstanceByFactoryBean(BeanDefinition bd) throws Exception {

		Object factoryBean = this.doGetBean(bd.getFactoryBeanName());
		Method m = factoryBean.getClass().getMethod(bd.getFactoryMethodName(), null);
		return m.invoke(factoryBean, null);
	}

	/**
	 * 执行初始化方法
	 * 
	 * @param bd
	 * @param instance
	 * @throws Exception
	 */
	private void doInit(BeanDefinition bd, Object instance) throws Exception {
		// 执行初始化方法
		if (StringUtils.isNotBlank(bd.getInitMethodName())) {
			Method m = instance.getClass().getMethod(bd.getInitMethodName(), null);
			m.invoke(instance, null);
		}
	}

	@Override
	public void close() throws IOException {
		// 执行单例实例的销毁方法
		for (Entry<String, BeanDefinition> e : this.beanDefintionMap.entrySet()) {
			String beanName = e.getKey();
			BeanDefinition bd = e.getValue();

			if (bd.isSingleton() && StringUtils.isNotBlank(bd.getDestroyMethodName())) {
				Object instance = this.singletonBeanMap.get(beanName);
				try {
					Method m = instance.getClass().getMethod(bd.getDestroyMethodName(), null);
					m.invoke(instance, null);
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e1) {
					log.error("执行bean[" + beanName + "] " + bd + " 的 销毁方法异常！", e1);
				}
			}
		}

		//疑问：原型Bean如果指定了销毁方法，怎么办？
	}
}
