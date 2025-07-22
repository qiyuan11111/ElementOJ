package com.elementoj.auth.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.elementoj.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("user")
public class EleUserDetails extends BaseEntity implements UserDetails {

    @TableField("user_name")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("user_id")
    private Long userId;

    @TableField(exist = false)
    private List<EleAuthority> authorities;

    @Override
    public Collection<EleAuthority> getAuthorities() {
        if (authorities == null) {
            return new ArrayList<>();
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
