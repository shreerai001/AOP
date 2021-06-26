package com.example.aop.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionStats {

    UNKNOWN_ERROR(500, "Unknown error...");


    private int statusCode;

    private String message;


}
