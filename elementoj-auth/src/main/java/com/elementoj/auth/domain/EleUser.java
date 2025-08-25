package com.elementoj.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName(value = "user", autoResultMap = true)
public class EleUser extends BaseEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("nick_name")
    private String nickName;

//    @TableField(exist = false)
//    private String email;
//
//    @TableField(exist = false)
//    private List<EleAuthority> authorities;

//    @Override
//    public Collection<EleAuthority> getAuthorities() {
//        if (authorities == null) {
//            return new ArrayList<>();
//        }
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return userName;
//    }

}
