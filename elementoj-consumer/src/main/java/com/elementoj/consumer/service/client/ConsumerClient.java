package com.elementoj.consumer.service.client;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("elementoj-test")
public interface ConsumerClient {
    @PostMapping("/user/{uid}")
    String getUser(@PathVariable("uid") String uid);
}
