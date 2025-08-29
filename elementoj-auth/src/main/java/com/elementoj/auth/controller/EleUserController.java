package com.elementoj.auth.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Session;
import com.elementoj.api.system.bo.EleUserBO;
import com.elementoj.api.system.dto.query.EleUserRegisterDTO;
import com.elementoj.api.system.exception.EleUserException;
import com.elementoj.auth.mapbean.EleUserMapBean;
import com.elementoj.auth.service.impl.EleUserService;
import com.elementoj.common.core.web.domain.AjaxResult;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.elementoj.api.system.exception.EleUserExceptionCode.*;

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
//    @SentinelResource(value = "register", fallback = "registerFallback", fallbackClass = EleUserControllerFallback.class)
    public AjaxResult register(EleUserRegisterDTO eleUserRegisterDTO) {
        if (ObjectUtil.isNull(eleUserRegisterDTO)) {
            throw new EleUserException("找不到注册的用户信息！", ELE_USER_NOT_EXIST_USER);
        }
        if (!StrUtil.equals(eleUserRegisterDTO.getPassword(), eleUserRegisterDTO.getRepassword()))
            throw new EleUserException("两次密码不一致", ELE_USER_INCONSISTENT_PASSWORDS);

        eleUserService.register(eleUserMapBean.toEleUserBO(eleUserRegisterDTO));
        new EleUserBO();
        return AjaxResult.success();
    }

    @GetMapping("/login-view")  // 与SecurityConfig中配置的登录页面路径保持一致
    public String login(String authToken, Authentication authentication) {
        // 已登录用户重定向到首页
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        // 验证 authToken
//        String sessionToken = (String) session.getAttribute("authToken");
//        System.out.println(sessionToken);
//        System.out.println(authToken);
//        if (StrUtil.isBlank(sessionToken) || !StrUtil.equals(sessionToken, authToken)) {
//            return "redirect:/error?message=Invalid+or+missing+auth+token";
//        }
        // 清理 session 中的 token（一次性使用）
//        session.removeAttribute("authToken");
        return "login-view";  // 确保与实际模板文件名一致
    }


//    @PostMapping("/login")

    /// /    @SentinelResource(value = "login", fallback = "loginFallback", fallbackClass = EleUserControllerFallback.class)
//    public AjaxResult login(EleClient client,
//                            HttpServletRequest request) {
//        MultiValueMap<String, String> map = Stream.of(
//                        new AbstractMap.SimpleEntry<>("username", client.getUserName()),
//                        new AbstractMap.SimpleEntry<>("password", client.getPassword()),
//                        new AbstractMap.SimpleEntry<>("grant_type", client.getGrantType()),
//                        new AbstractMap.SimpleEntry<>("client_id", client.getClientId()),
//                        new AbstractMap.SimpleEntry<>("client_secret", client.getClientSecret()),
//                        new AbstractMap.SimpleEntry<>("scope", client.getScope())
//                )
//                .collect(LinkedMultiValueMap::new,
//                        (m, e) -> m.add(e.getKey(), e.getValue()),
//                        LinkedMultiValueMap::addAll);
//
//        return AjaxResult.success(userClient.postAccessToken(map));
//    }
    @GetMapping("/info")
    public AjaxResult getUserInfoByAccessToken(@RequestParam("type") String type) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return AjaxResult.success("hello");
    }
}
