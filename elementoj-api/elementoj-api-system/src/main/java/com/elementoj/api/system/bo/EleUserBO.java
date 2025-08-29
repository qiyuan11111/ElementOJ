package com.elementoj.api.system.bo;

import com.elementoj.common.core.web.domain.BaseEntity;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EleUserBO extends BaseEntity implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private List<EleAuthorityBO> authorities;
}
