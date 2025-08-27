package com.elementoj.api.system.bo;

import com.elementoj.common.core.web.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class EleAuthorityBO extends BaseEntity implements GrantedAuthority {

    private String authorityId;

    private String userId;

    private String username;

    private String authority;
}
