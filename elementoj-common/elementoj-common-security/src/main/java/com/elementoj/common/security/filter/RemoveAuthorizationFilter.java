package com.elementoj.common.security.filter;

import com.elementoj.common.security.config.HeaderMapRequestWrapper;
import com.elementoj.common.security.domain.PublicPathRegister;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RemoveAuthorizationFilter implements Filter {
    @Resource
    private PublicPathRegister publicPathRegister;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(httpRequest);
            for (String noTokenPath : publicPathRegister.getPublicPathList()) {
                AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(noTokenPath);
                if (antPathRequestMatcher.matches(httpRequest)) {
                    requestWrapper.removeHeader("Authorization");
                    break;
                }
            }
            filterChain.doFilter(requestWrapper, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
