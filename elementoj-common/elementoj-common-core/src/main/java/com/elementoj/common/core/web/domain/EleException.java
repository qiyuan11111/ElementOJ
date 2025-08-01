package com.elementoj.common.core.web.domain;

public class EleException extends RuntimeException{
    public int code;

    public static final int ELE_SUCCESS = 1000;

    public EleException(String message, int code) {
        super(message);
        this.code = code;
    }

    public EleException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public EleException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }
}
