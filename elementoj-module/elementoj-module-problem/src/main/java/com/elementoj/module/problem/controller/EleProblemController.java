package com.elementoj.module.problem.controller;

import com.elementoj.common.core.web.domain.AjaxResult;
import com.elementoj.module.problem.mapper.EleProblemMapper;
import com.elementoj.module.problem.service.EleProblemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/problem")
public class EleProblemController {
    @Resource
    EleProblemService eleProblemService;

    @Resource
    EleProblemMapper eleProblemMapper;

    @PostMapping("/list")
    public AjaxResult getProblemList() {
        return AjaxResult.success(eleProblemService.getProblemList());
    }

    @PostMapping("/get")
    public AjaxResult getProblemDetailById(Long pid) {
        return AjaxResult.success(eleProblemService.getProblemDetailById(pid));
    }

    @PostMapping("/data")
    public AjaxResult getProblemDataById(Long pid) {
        return AjaxResult.success(eleProblemService.getProblemDataById(pid));
    }

    @PostMapping("/test")
    public AjaxResult test(){
        return AjaxResult.success(eleProblemService.getProblemAccountById(1000L));
    }
}
