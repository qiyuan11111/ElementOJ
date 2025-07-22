package com.elementoj.api.solution.domain;

import com.elementoj.api.problem.domain.EleProblem;
import com.elementoj.api.system.exception.EleUserExceptionCode;
import com.elementoj.common.core.web.domain.EleException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
public class EleSubmissionConfig {

    public EleLanguageConfig languageConfig;

    public Long maxCpuTime;

    public Long maxMemory;

    public Long maxOutputSize;
}
