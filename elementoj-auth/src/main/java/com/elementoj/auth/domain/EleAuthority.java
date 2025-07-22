package com.elementoj.auth.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.elementoj.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("authority")
public class EleAuthority extends BaseEntity implements GrantedAuthority {

    @TableField("authority")
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
