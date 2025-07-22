package com.elementoj.api.system.exception;

import com.elementoj.common.core.web.domain.EleException;

public class EleUserException extends EleException {

    public EleUserException(String message, int code) {
        super(message, code);
    }

    public EleUserException(Throwable cause, int code) {
        super(cause, code);
    }

    public EleUserException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }
}
