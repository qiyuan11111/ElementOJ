package com.elementoj.module.solution.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.elementoj.api.problem.domain.EleProblem;
import com.elementoj.api.solution.domain.EleSolution;
import com.elementoj.api.solution.domain.EleSubmission;
import com.elementoj.api.solution.domain.EleSubmissionConfig;
import com.elementoj.api.system.domain.EleUser;
import com.elementoj.common.core.web.domain.AjaxResult;
import com.elementoj.module.solution.service.EleProblemService;
import com.elementoj.module.solution.service.EleSolutionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/solution")
public class EleSolutionController {

    @Resource
    EleProblemService eleProblemService;

    @Resource
    EleSolutionService eleSolutionService;

    @PostMapping("/submit")
    @SentinelResource(value = "submitSolution", fallback = "submitSolutionFallback", fallbackClass = EleSolutionControllerFallback.class)
    public AjaxResult submitSolution(EleSolution solution) {
        EleUser user = EleUser.getCurrentUser();
        solution.setUserId(user.getUserId());

        EleProblem problem = eleProblemService.getProblemDataById(solution.getProblemId());
        EleSubmissionConfig config = eleSolutionService.buildSubmissionConfig(problem, solution);
        EleSubmission submission = new EleSubmission()
                .setSolution(solution)
                .setConfig(config);

        return AjaxResult.success(eleSolutionService.postSubmission(submission));
    }

    @PostMapping("/update")
    @SentinelResource(value = "submitSolution", fallback = "submitSolutionFallback", fallbackClass = EleSolutionControllerFallback.class)
    public AjaxResult updateSolutionResult(Long sid, Integer result){
        EleUser user = EleUser.getCurrentUser();
        EleSolution solution =

    }



}
