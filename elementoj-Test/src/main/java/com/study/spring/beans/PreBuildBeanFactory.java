package com.study.spring.beans;

import java.util.Map;

public class PreBuildBeanFactory extends DefaultBeanFactory {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PreBuildBeanFactory.class);

    public void preInstantiateSingletons() throws Throwable {
        synchronized (this.beanDefintionMap) {
            for (Map.Entry<String, BeanDefinition> entry : this.beanDefintionMap.entrySet()) {
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
