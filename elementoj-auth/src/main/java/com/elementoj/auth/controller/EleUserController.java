package com.elementoj.auth.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.elementoj.api.system.bo.EleUserBO;
import com.elementoj.api.system.dto.query.EleUserRegisterDTO;
import com.elementoj.api.system.exception.EleUserException;
import com.elementoj.auth.mapbean.EleUserMapBean;
import com.elementoj.auth.service.impl.EleUserService;
import com.elementoj.common.core.web.domain.AjaxResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static com.elementoj.api.system.exception.EleUserExceptionCode.ELE_USER_INCONSISTENT_PASSWORDS;
import static com.elementoj.api.system.exception.EleUserExceptionCode.ELE_USER_NOT_EXIST_USER;

@Controller
public class EleUserController {

    private final EleUserService eleUserService;
    private final EleUserMapBean eleUserMapBean;

    public EleUserController(EleUserService eleUserService, EleUserMapBean eleUserMapBean) {
        this.eleUserService = eleUserService;
        this.eleUserMapBean = eleUserMapBean;
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult register(EleUserRegisterDTO eleUserRegisterDTO) {
        if (ObjectUtil.isNull(eleUserRegisterDTO)) {
            throw new EleUserException("找不到注册的用户信息！", ELE_USER_NOT_EXIST_USER);
        }
        if (!StrUtil.equals(eleUserRegisterDTO.getPassword(), eleUserRegisterDTO.getRepassword())) {
            throw new EleUserException("两次密码不一致", ELE_USER_INCONSISTENT_PASSWORDS);
        }

        eleUserService.register(eleUserMapBean.toEleUserBO(eleUserRegisterDTO));
        new EleUserBO();
        return AjaxResult.success();
    }

    @GetMapping("/login-view")
    public String login(String authToken, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "login-view";
    }

    @GetMapping("/userinfo")
    @ResponseBody
    public Map<String, Object> userinfo(@AuthenticationPrincipal Jwt jwt) {
        Map<String, Object> userInfo = new HashMap<>();
        String userId = jwt.getSubject();
        userInfo.put("userId", userId);
        userInfo.put("sub", userId);
        return userInfo;
    }

    @GetMapping("/info")
    @ResponseBody
    public AjaxResult getUserInfoByAccessToken(@RequestParam("type") String type) {
        return AjaxResult.success("hello");
    }
}