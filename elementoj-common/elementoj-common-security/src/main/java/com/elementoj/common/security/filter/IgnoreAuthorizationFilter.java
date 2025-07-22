package com.elementoj.common.security.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.elementoj.common.security.config.HeaderMapRequestWrapper;
import com.elementoj.common.security.domain.PublicPathRegister;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class IgnoreAuthorizationFilter extends OncePerRequestFilter {

    private PublicPathRegister publicPathRegister;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);
        if (ObjectUtil.isNull(publicPathRegister) || ArrayUtil.isEmpty(publicPathRegister.getPublicPathList())) return;

        Arrays.stream(publicPathRegister.getPublicPathList())
                .filter(StrUtil::isNotBlank) // 过滤空白路径
                .map(AntPathRequestMatcher::new) // 转换为 AntPathRequestMatcher
                .filter(matcher -> matcher.matches(request)) // 过滤匹配的请求
                .findFirst() // 找到第一个匹配的路径
                .ifPresent(matcher -> SecurityContextHolder.clearContext());

        filterChain.doFilter(requestWrapper, response);
    }
}
