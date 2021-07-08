package com.example.aop.utils;

public class MethodSignatureConst {
    private MethodSignatureConst() {
        throw new IllegalStateException("access to util class denied");
    }

    public static final String SAVE_PROGRAMMER = "com.example.aop.service.ProgrammerService.saveProgrammer()";
}
