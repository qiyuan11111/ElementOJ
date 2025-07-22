package com.elementoj.api.solution.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EleCompileConfig {
    public String srcName;

    public String exeName;

    public Long maxCpuTime;

    public Long maxMemory;

    public Long maxRealTime;

    public String command;
}
