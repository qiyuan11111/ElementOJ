package com.elementoj.module.solution.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elementoj.api.problem.domain.EleProblem;
import com.elementoj.api.solution.domain.EleLanguageConfig;
import com.elementoj.api.solution.domain.EleSolution;
import com.elementoj.api.solution.domain.EleSubmission;
import com.elementoj.api.solution.domain.EleSubmissionConfig;
import com.elementoj.api.system.exception.EleUserExceptionCode;
import com.elementoj.common.core.web.domain.EleException;
import com.elementoj.module.solution.mapper.EleSolutionMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class EleSolutionService {

    @Resource
    EleSolutionMapper eleSolutionMapper;

    @Resource
    RabbitTemplate rabbitTemplate;

    public Long postSubmission(EleSubmission submission) {
        EleSolution solution = submission.getSolution();
        eleSolutionMapper.insert(submission.getSolution());
        rabbitTemplate.convertAndSend("amq.direct", "eleQueue", submission);
        return solution.getSolutionId();
    }

    public EleSubmissionConfig buildSubmissionConfig(EleProblem problem, EleSolution solution) {
        return new EleSubmissionConfig()
                .setMaxCpuTime(Optional.ofNullable(problem.getTimeLimit())
                        .orElseThrow(() -> new EleException("题目" + problem.getProblemId() + "时间限制为空", EleUserExceptionCode.ELE_SYSTEM_ERROR)))
                .setMaxMemory(Optional.ofNullable(problem.getMemoryLimit())
                        .orElseThrow(() -> new EleException("题目" + problem.getProblemId() + "空间限制为空", EleUserExceptionCode.ELE_SYSTEM_ERROR)))
                .setLanguageConfig(EleLanguageConfig.getLangConfig(
                        Optional.ofNullable(solution.getLang())
                                .orElseThrow(() -> new EleException("提交的语言为空", EleUserExceptionCode.ELE_SYSTEM_ERROR))
                ))
                .setMaxOutputSize(1048576L);
    }

//    public EleProblem getSolutionAccountById(Long pid) {
//        return getProblemById(pid, "problem_id", "title", "time_limit", "memory_limit");
//    }

    public EleSolution getSolutionById(Long sid, String... fields) {
        return eleSolutionMapper.selectOne(
                new QueryWrapper<EleSolution>()
                        .select(fields)
                        .eq("solution_id", sid)
        );
    }
}
