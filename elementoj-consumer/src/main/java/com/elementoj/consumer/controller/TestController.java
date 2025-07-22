package com.elementoj.consumer.controller;

import com.elementoj.consumer.service.client.ConsumerClient;
import com.elementoj.consumer.service.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
    @Resource
    private UserClient userClient;

    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping("/test/{uid}")
    public String test(@PathVariable("uid") String uid) {
        System.out.println("test");
        return userClient.getUser(uid);
    }

    @RequestMapping("/test1/{uid}")
    public String tes1t(@PathVariable("uid") String uid) {
        return uid;
    }

}
