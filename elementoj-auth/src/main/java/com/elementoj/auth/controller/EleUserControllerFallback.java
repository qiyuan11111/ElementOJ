package com.elementoj.auth.controller;

import com.elementoj.auth.domain.EleClient;
import com.elementoj.auth.domain.EleUser;
import com.elementoj.common.core.web.domain.AjaxResult;
import com.elementoj.common.core.web.domain.EleException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.elementoj.api.system.exception.EleUserExceptionCode.*;

public class EleUserControllerFallback {
    public static AjaxResult registerFallback(EleUser user, String RePassword, Throwable throwable) {
        if (throwable instanceof EleException) {
            EleException eleException = (EleException) throwable;
            return AjaxResult.error(eleException.code, throwable.getMessage());
        }
        return AjaxResult.error(ELE_SYSTEM_ERROR, throwable.getMessage());
    }

    public static AjaxResult loginFallback(EleClient client, Throwable throwable) {
        String regex = "\\[\\d+\\] during \\[[A-Z]+\\] to( \\[[^\\]]+\\]){2}: \\[\\{\"error\":\"[^\"]+\",\"error_description\":\"([^\"]+)\"\\}\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(throwable.getMessage());
//        throwable.printStackTrace();
        if (!matcher.find()) {
            return AjaxResult.error(ELE_SYSTEM_ERROR, throwable.getMessage());
        }
        String reason = matcher.group(2);
//        System.out.println(reason);
        if (reason.equals("Bad credentials")) {
            return AjaxResult.error(ELE_USER_PASSWORD_ERROR, "用户名或密码错误");
        }
        return AjaxResult.error(ELE_SYSTEM_ERROR, reason);
    }
}
