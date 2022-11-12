package com.example.demo.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,Object> handleInvalidArgument(MethodArgumentNotValidException e){
        Map<String,Object> responseErrors = new HashMap<>();
        Map<String,String> detail = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error->{
            detail.put(error.getField(),error.getDefaultMessage());
        });
        responseErrors.put("detail",detail);
        responseErrors.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        responseErrors.put("status",HttpStatus.BAD_REQUEST.value());

        return responseErrors;
    }
}
