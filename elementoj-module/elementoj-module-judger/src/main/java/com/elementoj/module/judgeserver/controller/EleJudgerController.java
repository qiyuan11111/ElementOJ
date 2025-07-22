package com.elementoj.module.judgeserver.controller;

import com.elementoj.api.judger.domain.EleTestcase;
import com.elementoj.api.solution.domain.EleCompileConfig;
import com.elementoj.api.solution.domain.EleLanguageConfig;
import com.elementoj.api.solution.domain.EleRunConfig;
import com.elementoj.api.solution.domain.EleSubmission;
import com.elementoj.common.core.web.domain.AjaxResult;
import com.elementoj.module.judgeserver.service.EleJudgerService;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class EleJudgerController {

    @Resource
    EleJudgerService eleJudgerService;

    @PostMapping(value = "/judge")
    public AjaxResult judge(EleSubmission submission) {
        EleLanguageConfig languageConfig = submission.getConfig().getLanguageConfig();

        List<EleTestcase> testcases = null;

        EleRunConfig runConfig = new EleRunConfig();
        EleCompileConfig compileConfig = new EleCompileConfig();

        Triple<String, String, String> dirs = eleJudgerService.initTestcaseDir(submission.getSubmissionId(),
                submission.getSolution().getProblemId());

        String exePath = eleJudgerService.compile(submission.getSolution(), compileConfig, dirs);

    }

}
