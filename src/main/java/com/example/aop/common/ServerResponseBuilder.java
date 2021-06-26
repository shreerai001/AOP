package com.example.aop.common;


import com.example.aop.common.exceptions.ExceptionStats;

public class ServerResponseBuilder {

    private ServerResponseBuilder() {
        throw new IllegalStateException("Cannot access util class ");
    }

    public static ServerResponse errorResponse(int code, String message) {
        return ServerResponse.builder()
                .message(message)
                .status(code)
                .build();
    }

    public static ServerResponse error(ExceptionStats exceptionStats) {
        return ServerResponse.builder()
                .status(exceptionStats.getStatusCode())
                .message(exceptionStats.getMessage())
                .build();
    }
}
