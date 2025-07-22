package com.elementoj.module.problem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elementoj.api.problem.domain.EleProblem;
import com.elementoj.module.problem.mapper.EleProblemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EleProblemService {
    @Resource
    EleProblemMapper eleProblemMapper;

    public List<EleProblem> getProblemList() {
        return eleProblemMapper.selectList(
                new QueryWrapper<EleProblem>()
                        .select("problem_id", "title")
        );
    }

    public EleProblem getProblemDetailById(Long pid) {
        return eleProblemMapper.getProblemById(pid, "problem_id", "title", "problem_description",
                "input_description", "output_description", "hint",
                "time_limit", "memory_limit");
    }

    public EleProblem getProblemAccountById(Long pid) {
        return eleProblemMapper.getProblemById(pid, "problem_id", "title");
    }

    public EleProblem getProblemDataById(Long pid) {
        return eleProblemMapper.getProblemById(pid, "problem_id", "title",
                "time_limit", "memory_limit");
    }
}
