package com.elementoj.account.controller;

import com.alibaba.csp.sentinel.annotati.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.elementoj.common.core.web.domain.AjaxResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EleUserController {
    @PostMapping("/user/{uid}")
    @SentinelResource(value = "getid", blockHandler = "block")
    public String getUser(@PathVariable("uid") String uid) throws InterruptedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return uid;
    }

    @PostMapping("/fuck")
    public String fuck() {
        System.out.println("uid: fuck1");
        return "fuck1";
    }

    public String getId(String uid) throws InterruptedException {
//        Thread.sleep(2000);
        return uid;
    }

    public String block(String uid, BlockException e){
        return "fuck";
    }

//    @RequestMapping("/register")
//    public AjaxResult register(){
//
//    }
}
