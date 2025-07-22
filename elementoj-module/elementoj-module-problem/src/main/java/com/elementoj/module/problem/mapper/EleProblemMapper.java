package com.elementoj.module.problem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elementoj.api.problem.domain.EleProblem;
import com.elementoj.api.system.domain.EleUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

public interface EleProblemMapper extends BaseMapper<EleProblem> {
    @ResultMap("EleProblemMap")
    @Select("<script>" +
            "select " +
            "<foreach collection='fields' separator=',' item='field' index='i'>" +
            "   ${field}" +
            "</foreach>" +
            "from problem where problem_id = #{pid}" +
            "</script>")
    EleProblem getProblemById(Long pid, String... fields);
}
