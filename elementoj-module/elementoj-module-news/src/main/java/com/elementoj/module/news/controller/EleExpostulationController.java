package com.elementoj.module.news.controller;

import com.elementoj.common.core.web.domain.AjaxResult;
import com.elementoj.module.news.domain.EleExpostulation;
import com.elementoj.module.news.service.EleExpostulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/expostulation")
public class EleExpostulationController {
    @Resource
    EleExpostulationService eleExpostulationService;

    @PostMapping("/getRandom")
    public AjaxResult getRandom(HttpServletRequest request) {
        System.out.println(request.getHeader("Authorization"));
        EleExpostulation eleExpostulation = eleExpostulationService.getExpostulationRandom();
        return AjaxResult.success(eleExpostulation);
    }

    @PostMapping("/test/o")
    public void test(HttpServletRequest request) {
        System.out.println(request.getHeader("Authorization"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
    }

    @PostMapping("/")
    public void test2(HttpServletRequest request) {
        System.out.println(request.getHeader("Authorization"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
    }
}
