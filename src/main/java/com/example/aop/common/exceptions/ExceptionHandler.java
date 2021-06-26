package com.example.aop.common.exceptions;


import com.example.aop.common.ServerResponse;
import com.example.aop.common.ServerResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServerResponse handleException(Exception exception) {
        if (exception instanceof CustomException) {
            var customException = (CustomException) exception;
            return ServerResponseBuilder.errorResponse(customException.getCode(), customException.getMessage());
        }
        log.error(exception.getMessage());
        return ServerResponseBuilder.error(ExceptionStats.UNKNOWN_ERROR);
    }


}
