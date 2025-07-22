package com.elementoj.module.solution.controller;

import com.elementoj.api.problem.exception.EleProblemNotFound;
import com.elementoj.api.solution.domain.EleSolution;
import com.elementoj.common.core.web.domain.AjaxResult;
import com.elementoj.common.core.web.domain.EleException;

import static com.elementoj.api.system.exception.EleUserExceptionCode.*;

public class EleSolutionControllerFallback {

    public static AjaxResult submitSolutionFallback(EleSolution solution, Throwable throwable){
        if (throwable instanceof EleProblemNotFound) {
            EleException eleException = (EleException) throwable;
            return AjaxResult.error(eleException.code, eleException.getMessage());
        }
        return AjaxResult.error(ELE_SYSTEM_ERROR, "Solution Module 系统错误！");
    }
}
