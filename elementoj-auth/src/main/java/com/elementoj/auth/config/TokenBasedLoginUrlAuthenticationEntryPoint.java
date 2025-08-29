package com.elementoj.auth.config;

import cn.hutool.core.util.IdUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.io.IOException;

public class TokenBasedLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    public TokenBasedLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 生成随机 token
        String authToken = IdUtil.fastUUID();
        System.out.println(authToken);
        // 存储 token 到 session
        request.getSession().setAttribute("authToken", authToken);
        // 重定向到 /login-view，附加 token 参数
        String redirectUrl = buildRedirectUrlToLoginPage(request, response, authException) + "?authToken=" + authToken;
        response.sendRedirect(redirectUrl);
    }
}
