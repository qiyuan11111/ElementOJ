package com.elementoj.api.system.service.client;

import com.elementoj.api.system.domain.EleClient;
import com.elementoj.common.openfeign.config.RequestInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "elementoj-module-problem", configuration = RequestInterceptorConfig.class)
public interface EleUserClient {
    @PostMapping("/oauth/token")
    Object postAccessToken(@RequestParam MultiValueMap<String, String> parameters);

    @PostMapping("/test1")
    String test1(EleClient client);
}
