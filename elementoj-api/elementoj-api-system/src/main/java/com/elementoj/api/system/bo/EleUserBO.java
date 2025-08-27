package com.elementoj.api.system.bo;

import com.elementoj.common.core.web.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class EleUserBO extends BaseEntity implements UserDetails {

    private String username;

    private String password;

    private List<EleAuthorityBO> authorities;
}
