package com.elementoj.auth.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.elementoj.api.system.domain.EleClient;
import com.elementoj.api.system.domain.EleUser;
import com.elementoj.api.system.exception.EleUserException;
import com.elementoj.auth.service.EleUserService;
import com.elementoj.common.core.web.domain.AjaxResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.stream.Stream;

import static com.elementoj.api.system.exception.EleUserExceptionCode.*;

@Controller
public class EleUserController {

    @Resource
    EleUserService eleUserService;

//    @Resource
//    ProviderSettings  tokenEndpoint;
//
//    @Resource
//    private PasswordEncoder encoder;

//    @Resource
//    EleUserClient userClient;15169772562

    @PostMapping("/register")
    @ResponseBody
//    @SentinelResource(value = "register", fallback = "registerFallback", fallbackClass = EleUserControllerFallback.class)
    public AjaxResult register(EleUser user, @RequestParam("repassword") String repassword) {
        if(ObjectUtil.isNull(user)){
            throw new EleUserException("找不到注册的用户信息！", ELE_USER_NOT_EXIST_USER);
        }
        if(!StrUtil.equals(user.getPassword(), repassword))
            throw new EleUserException("两次密码不一致", ELE_USER_INCONSISTENT_PASSWORDS);

        eleUserService.register(user);
        return AjaxResult.success();
    }

    @GetMapping("/login-view")
    public String login() {
        return "login-view";  // 返回login.html视图
    }

//    @PostMapping("/login")
////    @SentinelResource(value = "login", fallback = "loginFallback", fallbackClass = EleUserControllerFallback.class)
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
    public AjaxResult getUserInfoByAccessToken(@RequestParam("type")String type){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return AjaxResult.success("hello");
    }

    //    @PostMapping("/login")
//    @SentinelResource(value = "login", fallback = "loginFallback", fallbackClass = EleUserControllerFallback.class)
//    public AjaxResult login(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
//        System.out.println(principal);
//        System.out.println(parameters);
//        OAuth2AccessToken resultToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
//        return AjaxResult.success(resultToken);
//    }

//    @PostMapping(value = "/login")
//    @SentinelResource(value = "login", fallback = "loginFallback", fallbackClass = EleUserControllerFallback.class)
//    public AjaxResult login(EleClient user) throws HttpRequestMethodNotSupportedException {
//        System.out.println(user);
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getClientId(), user.getClientSecret());
////        System.out.println(token);
//        Map<String, String> map = new HashMap<>();
//        map.put("username", user.getUsername());
//        map.put("password", user.getPassword());
//        map.put("grant_type", user.getGrantType());
//
//        OAuth2AccessToken resultToken = tokenEndpoint.postAccessToken(token, map).getBody();
////        LoginResp resp = new LoginResp();
////        resp.setAccessToken(resultToken.getValue())
////                .setTokenType(resultToken.getTokenType())
////                .setRefreshToken(resultToken.getRefreshToken().getValue())
////                .setExpiresIn(resultToken.getExpiresIn())
////                .setScope(resultToken.getScope())
////                .setJti((String) resultToken.getAdditionalInformation().get("jti"));
//        return AjaxResult.success(resultToken);
//    }

//    @PostMapping("/test")
//    public AjaxResult test(EleClient client) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("test" + authentication);
//        userClient.test1(client);
//        return AjaxResult.success();
//    }

//    @PostMapping("/test1")
//    public AjaxResult test1(EleClient client) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("test1" + authentication);
//        return AjaxResult.success();
//    }
}




