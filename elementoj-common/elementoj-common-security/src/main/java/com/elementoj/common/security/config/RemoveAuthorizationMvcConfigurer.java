package com.elementoj.common.security.config;

import com.elementoj.common.security.domain.PublicPathRegister;
import com.elementoj.common.security.filter.RemoveAuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RemoveAuthorizationMvcConfigurer{

    @Resource
    private PublicPathRegister publicPathRegister;

    @Bean
    public RemoveAuthorizationFilter removeAuthorizationFilter() {
        return new RemoveAuthorizationFilter();
    }

    @Bean
    public FilterRegistrationBean<RemoveAuthorizationFilter> headerModificationFilter() {
        FilterRegistrationBean<RemoveAuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(removeAuthorizationFilter());
        registrationBean.addUrlPatterns("/*"); // 设置过滤器的URL模式
        registrationBean.setOrder(Integer.MIN_VALUE); // 设置过滤器的执行顺序，数字越小越先执行
        return registrationBean;
    }
}
