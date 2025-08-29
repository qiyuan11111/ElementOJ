package com.elementoj.auth.domain.dto;

import com.elementoj.auth.domain.EleAuthority;
import com.elementoj.common.core.web.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EleUserDTO extends BaseEntity {
    private Long id;

    private String userId;

    private String username;

    private String password;

    private String nickName;

    private String email;

    private List<EleAuthority> authorities;
}
