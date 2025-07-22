package com.study.spring.aop;

import com.study.spring.aop.advisor.Advisor;
import com.study.spring.aop.advisor.PointcutAdvisor;
import com.study.spring.aop.pointcut.Pointcut;
import com.study.spring.beans.BeanFactory;
import com.study.spring.beans.BeanFactoryAware;
import com.study.spring.beans.BeanPostProcessor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.*;

/*
v2：增加实现BeanFactoryAware,增加实现判断Bean是否需要被AOP切面增强的逻辑
v3: 增加代理实现逻辑
*/
public class AdvisorAutoProxyCreator_v3 implements BeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;
    //所有的Advisors
    private List<Advisor> advisors;
    //标识是否获取过了所有的Advisors
    private volatile boolean gettedAllAdvisors = false;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Throwable {

		/*逻辑
		1 判断Bean是否需要增强
		2 创建代理来实现增强
		*/

        //1 判断Bean是否需要增强
        List<Advisor> matchAdvisors = getMatchedAdvisors(bean, beanName);

		// 2如有切面切中，创建代理来实现增强
		if (CollectionUtils.isNotEmpty(matchAdvisors)) {
			bean = this.createProxy(bean, beanName, matchAdvisors);
		}

        return bean;
    }

    private List<Advisor> getMatchedAdvisors(Object bean, String beanName) throws Throwable {
        //第一次执行该方法，先从BeanFactory中得到用户配置的所有切面Advisor
    	if (!gettedAllAdvisors) {
            synchronized (this) {
                if (!gettedAllAdvisors) {
                    advisors = this.beanFactory.getBeansOfTypeList(Advisor.class);
                    gettedAllAdvisors = true;
                }
            }
        }

    	//如果没有配置切面
        if (CollectionUtils.isEmpty(this.advisors)) {
            return null;
        }

        //有配置切面
        // 得到Bean的类、所有的方法
        Class<?> beanClass = bean.getClass();
        List<Method> allMethods = this.getAllMethodForClass(beanClass);

        // 存放匹配的Advisor的list
        List<Advisor> matchAdvisors = new ArrayList<>();
        // 遍历Advisor来找匹配的
        for (Advisor ad : this.advisors) {
            if (ad instanceof PointcutAdvisor) {
                if (isPointcutMatchBean((PointcutAdvisor) ad, beanClass, allMethods)) {
                    matchAdvisors.add(ad);
                }
            }
        }
        return matchAdvisors;
    }

    private List<Method> getAllMethodForClass(Class<?> beanClass) {
    	//注意需要获得本类以及所实现的接口的方法
        List<Method> allMethods = new LinkedList<>();
        Set<Class<?>> classes = new LinkedHashSet<>(ClassUtils.getAllInterfacesForClassAsSet(beanClass));
        classes.add(beanClass);
        for (Class<?> clazz : classes) {
            Method[] methods = ReflectionUtils.getAllDeclaredMethods(clazz);
            for (Method m : methods) {
                allMethods.add(m);
            }
        }

        return allMethods;
    }

    private boolean isPointcutMatchBean(PointcutAdvisor pa, Class<?> beanClass, List<Method> methods) {
        Pointcut p = pa.getPointcut();

        // 首先判断类是否匹配
        if (!p.matchsClass(beanClass)) {
            return false;
        }

        // 再判断是否有方法匹配
        for (Method method : methods) {
            if (p.matchsMethod(method, beanClass)) {
                return true;
            }
        }
        return false;
    }

	private Object createProxy(Object bean, String beanName, List<Advisor> matchAdvisors) throws Throwable {
		// 通过AopProxyFactory工厂去完成选择、和创建代理对象的工作。
		return AopProxyFactory.getDefaultAopProxyFactory().createAopProxy(bean, beanName, matchAdvisors, beanFactory)
				.getProxy();
	}

    @Override
    public void setBeanFactory(BeanFactory bf) {
        this.beanFactory = bf;
    }
}
