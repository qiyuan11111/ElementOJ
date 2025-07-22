package com.study.spring.beans;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

public class DefaultBeanFactory_v0 implements BeanFactory,BeanDefinitionRegistry, Closeable {
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionRegistException {
       /* 问题1：如何存储BeanDefinition？
            Map<String,BeanDefinition>
          问题2：beanName重名怎么办？

          问题3：这里要做哪些事（它的代码逻辑）
       */
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return null;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }

    @Override
    public Object getBean(String name) throws Exception {
        /*思考1：在getBean方法中要做哪些事？
        */
        return null;
    }

    @Override
    public <T> T getBean(Class<T> type) throws Exception {
        return null;
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws Exception {
        return null;
    }

    @Override
    public Class<?> getType(String name) throws Exception {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
