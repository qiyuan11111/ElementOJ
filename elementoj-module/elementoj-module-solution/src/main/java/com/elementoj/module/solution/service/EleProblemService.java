package com.elementoj.module.solution.service;

import com.elementoj.api.problem.domain.EleProblem;
import com.elementoj.api.problem.exception.EleProblemNotFound;
import com.elementoj.api.problem.service.client.EleProblemClient;
import com.elementoj.common.core.web.domain.AjaxResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EleProblemService {
    @Resource
    private EleProblemClient eleProblemClient;

    public EleProblem getProblemDataById(Long pid) {
        EleProblem problem = (EleProblem) eleProblemClient.getProblemDataById(pid).get(AjaxResult.DATA_TAG);
        if (problem == null) {
            throw new EleProblemNotFound(pid);
        }
        return problem;
    }

}
