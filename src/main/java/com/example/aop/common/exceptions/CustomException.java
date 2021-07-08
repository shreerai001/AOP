package com.example.aop.common.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 541412398708972109L;
    private final int code;

    public CustomException(ExceptionStats exceptionStats) {
        super(exceptionStats.getMessage());
        this.code = exceptionStats.getStatusCode();
    }

    public CustomException(String message, int code) {
        super(message);
        this.code = code;
    }
}
