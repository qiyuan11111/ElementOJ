package com.elementoj.api.problem.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.elementoj.api.system.domain.EleUser;
import com.elementoj.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "problem", autoResultMap = true)
public class EleProblem extends BaseEntity {
    @TableId("problem_id")
    private Long problemId;

    @TableField(exist = false)
    private EleUser user;

    @TableField("title")
    private String title;

    @TableField("problem_description")
    private String problemDescription;

    @TableField("input_description")
    private String inputDescription;

    @TableField("output_description")
    private String outputDescription;

    @TableField("is_spj")
    private Integer isSpj;

    @TableField("is_show")
    private Integer isShow;

    @TableField("hint")
    private String hint;

    @TableField("time_limit")
    private Long timeLimit;

    @TableField("memory_limit")
    private Long memoryLimit;

}
