package com.elementoj.api.system.dto.query;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EleUserRegisterDTO {
    private String username;

    private String password;

    private String repassword;
}
