package com.elementoj.api.judger.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EleTestcase {
    private Integer id;

    private String input;

    private String output;
}
