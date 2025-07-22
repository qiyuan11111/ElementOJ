package com.elementoj.api.solution.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.elementoj.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("problem")
public class EleSolution extends BaseEntity {
    @TableId(value = "solution_id", type = IdType.AUTO)
    private Long solutionId;

    @TableField("problem_id")
    private Long problemId;

    @TableField("user_id")
    private Long userId;

    @TableField("lang")
    private Integer lang;

    @TableField("code")
    private String code;

    @TableField("result")
    private Integer result;

    @TableField("time")
    private Long time;

    @TableField("memory")
    private Long memory;
}
