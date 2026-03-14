package com.elementoj.module.news.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.elementoj.api.system.bo.EleUserBO;
import com.elementoj.api.system.dto.query.EleUserRegisterDTO;
import com.elementoj.api.system.exception.EleUserException;
import com.elementoj.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.elementoj.api.system.exception.EleUserExceptionCode.ELE_USER_INCONSISTENT_PASSWORDS;
import static com.elementoj.api.system.exception.EleUserExceptionCode.ELE_USER_NOT_EXIST_USER;

@RestController("/news")
public class EleTestController {
    @PostMapping("/test")
//    @SentinelResource(value = "register", fallback = "registerFallback", fallbackClass = EleUserControllerFallback.class)
    public AjaxResult register() {
        return AjaxResult.success("testest");
    }
}
