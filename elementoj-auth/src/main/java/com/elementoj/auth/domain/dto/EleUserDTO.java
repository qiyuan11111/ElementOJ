package com.elementoj.auth.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.elementoj.auth.domain.EleAuthority;
import com.elementoj.common.core.web.domain.BaseEntity;
import com.github.yulichang.annotation.EntityMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EleUserDTO extends BaseEntity {
    private Long id;

    private String userId;

    private String userName;

    private String password;

    private String nickName;

    private String email;

    private List<EleAuthority> authorities;
}
