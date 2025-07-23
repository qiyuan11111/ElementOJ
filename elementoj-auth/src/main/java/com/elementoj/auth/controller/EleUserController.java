package com.elementoj.auth.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.elementoj.api.system.domain.EleClient;
import com.elementoj.api.system.domain.EleUser;
import com.elementoj.api.system.exception.EleUserException;
import com.elementoj.api.system.service.client.EleUserClient;
import com.elementoj.auth.domain.EleUserDetails;
import com.elementoj.auth.service.EleUserService;
import com.elementoj.common.core.web.domain.AjaxResult;
import com.elementoj.common.core.web.utils.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.AbstractMap;
import java.util.stream.Stream;

import static com.elementoj.api.system.exception.EleUserExceptionCode.*;

@RestController
public class EleUserController {

    @Resource
    EleUserService eleUserService;

//    @Resource
//    TokenEndpoint tokenEndpoint;
//
//    @Resource
//    private PasswordEncoder encoder;

    @Resource
    EleUserClient userClient;

    @PostMapping("/register")
    @SentinelResource(value = "register", fallback = "registerFallback", fallbackClass = EleUserControllerFallback.class)
    public AjaxResult register(EleUser user, @RequestParam("repassword") String repassword) {
        if(!StringUtils.equals(user.getPassword(), repassword))
            throw new EleUserException("两次密码不一致", ELE_USER_INCONSISTENT_PASSWORDS);

        eleUserService.register(user);
        return AjaxResult.success();
    }

    @PostMapping("/login")
    @SentinelResource(value = "login", fallback = "loginFallback", fallbackClass = EleUserControllerFallback.class)
    public AjaxResult login(EleClient client,
                            HttpServletRequest request) {
        MultiValueMap<String, String> map = Stream.of(
                        new AbstractMap.SimpleEntry<>("username", client.getUserName()),
                        new AbstractMap.SimpleEntry<>("password", client.getPassword()),
                        new AbstractMap.SimpleEntry<>("grant_type", client.getGrantType()),
                        new AbstractMap.SimpleEntry<>("client_id", client.getClientId()),
                        new AbstractMap.SimpleEntry<>("client_secret", client.getClientSecret()),
                        new AbstractMap.SimpleEntry<>("scope", client.getScope())
                )
                .collect(LinkedMultiValueMap::new,
                        (m, e) -> m.add(e.getKey(), e.getValue()),
                        LinkedMultiValueMap::addAll);

        return AjaxResult.success(userClient.postAccessToken(map));
    }

    @PostMapping("/info")
    public AjaxResult getUserInfoByAccessToken(@RequestParam("type")String type){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return AjaxResult.success(eleUserService.getUserByToken(authentication));
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

    @PostMapping("/test")
    public AjaxResult test(EleClient client) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("test" + authentication);
        userClient.test1(client);
        return AjaxResult.success();
    }

    @PostMapping("/test1")
    public AjaxResult test1(EleClient client) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("test1" + authentication);
        return AjaxResult.success();
    }
}




