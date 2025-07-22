package com.elementoj.api.solution.domain;

import com.elementoj.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@Accessors(chain = true)
public class EleSubmission{
    private String submissionId;

    private EleSolution solution;

    private EleSubmissionConfig config;

    public EleSubmission() {
        this.submissionId = RandomStringUtils.randomAlphanumeric(20);
    }
}
