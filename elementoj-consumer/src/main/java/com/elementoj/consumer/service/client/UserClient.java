package com.elementoj.consumer.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "elementoj-account")
public interface UserClient {
    @PostMapping("/user/{uid}")
    String getUser(@PathVariable("uid") String uid);
}
