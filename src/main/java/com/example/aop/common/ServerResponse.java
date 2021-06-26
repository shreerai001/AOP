package com.example.aop.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
public class ServerResponse<T> {
    private int status;
    private String message;
    private T data;
}
