package com.elementoj.api.problem.service.client;

import com.elementoj.common.core.web.domain.AjaxResult;
import com.elementoj.common.openfeign.config.RequestInterceptorConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "elementoj-auth", configuration = RequestInterceptorConfig.class)
public interface EleProblemClient {
    @PostMapping("/data")
    AjaxResult getProblemDataById(Long pid);
}
