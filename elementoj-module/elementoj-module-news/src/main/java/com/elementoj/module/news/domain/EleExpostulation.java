package com.elementoj.module.news.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.elementoj.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("expostulation")
public class EleExpostulation extends BaseEntity {

    @TableField("expostulation_id")
    private Long expostulationId;

    @TableField("expostulation")
    private String expostulation;

}
