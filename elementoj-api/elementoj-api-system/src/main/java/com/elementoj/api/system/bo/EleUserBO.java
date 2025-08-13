package com.elementoj.api.system.bo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EleUserBO {

    private String userName;

    private String password;
}
