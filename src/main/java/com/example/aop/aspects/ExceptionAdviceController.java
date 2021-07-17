package com.example.aop.aspects;


import com.example.aop.common.ServerResponse;
import com.example.aop.common.ServerResponseBuilder;
import com.example.aop.common.exceptions.CustomException;
import com.example.aop.common.exceptions.ExceptionStats;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdviceController {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServerResponse handleException(Exception exception) {
        if (exception instanceof CustomException) {
            var customException = (CustomException) exception;
            return ServerResponseBuilder.errorResponse(customException.getCode(), customException.getMessage());
        }
        return ServerResponseBuilder.error(ExceptionStats.UNKNOWN_ERROR);
    }


}
