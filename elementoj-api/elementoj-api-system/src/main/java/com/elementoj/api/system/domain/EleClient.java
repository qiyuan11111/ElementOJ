package com.elementoj.api.system.domain;

import com.elementoj.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.core.annotation.AliasFor;

@EqualsAndHashCode(callSuper = true)
@Data
public class EleClient extends BaseEntity {
    private static final long serialVersionUID = 1L;
    public String grantType;

    public String userName;

    protected String password;

    public String scope;

    public String clientId;

    protected String clientSecret;
}
