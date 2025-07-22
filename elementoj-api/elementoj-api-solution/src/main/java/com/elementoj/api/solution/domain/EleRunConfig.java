package com.elementoj.api.solution.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class EleRunConfig {
    public String command;
    public String seccompRule;
    public List<String> env;
}
