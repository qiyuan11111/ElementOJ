package com.elementoj.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.elementoj.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "authority", autoResultMap = true)
public class EleAuthority extends BaseEntity{

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("authority_id")
    private String authorityId;

    @TableField("user_id")
    private String userId;

    @TableField("username")
    private String username;

    @TableField("authority")
    private String authority;
}
