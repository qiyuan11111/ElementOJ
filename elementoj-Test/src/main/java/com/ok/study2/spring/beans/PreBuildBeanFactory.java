package com.study2.spring.beans;

import com.study.spring.beans.BeanDefinition;
import com.study.spring.beans.DefaultBeanFactory;

import java.util.Map;

public class PreBuildBeanFactory extends DefaultBeanFactory {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PreBuildBeanFactory.class);

    public void preInstantiateSingletons() throws Exception {
        synchronized (this.beanDefintionMap) {
            for (Map.Entry<String, com.study.spring.beans.BeanDefinition> entry : this.beanDefintionMap.entrySet()) {
                String name = entry.getKey();
                BeanDefinition bd = entry.getValue();
                if (bd.isSingleton()) {
                    this.getBean(name);
                    if (log.isDebugEnabled()) {
                        log.debug("preInstantiate: name=" + name + " " + bd);
                    }
                }
            }
        }
    }
}
