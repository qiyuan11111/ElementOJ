package com.elementoj.api.problem.exception;

import com.elementoj.common.core.web.domain.EleException;

public class EleProblemException extends EleException {

    public EleProblemException(String message, int code) {
        super(message, code);
    }

    public EleProblemException(Throwable cause, int code) {
        super(cause, code);
    }

    public EleProblemException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }
}
