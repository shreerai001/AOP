package com.example.aop.common;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class ServerResponse<T> {
    private int status;
    private String message;
    private T data;
}
