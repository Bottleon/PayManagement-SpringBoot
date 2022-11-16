package com.example.demo.common.aop;

import com.example.demo.common.exception.IDDuplicatedException;
import com.example.demo.common.exception.IDNotExistException;
import com.example.demo.common.exception.PWMissMatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
    //error : fail request object validate test
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
        log.error("에러 : "+e.getMessage());
        return responseErrors;
    }
    //error :400번
    @ExceptionHandler({IDDuplicatedException.class, IDNotExistException.class, PWMissMatchException.class})
    public Map<String,Object> handleBadRequest(final RuntimeException re){
        Map<String,Object> responseError = new HashMap<>();
        responseError.put("error",HttpStatus.BAD_REQUEST.getReasonPhrase());
        responseError.put("status",HttpStatus.BAD_REQUEST.value());
        responseError.put("message",re.getMessage());
        log.error("에러 : "+re.getMessage());
        return responseError;
    }
    //error : 500번
    @ExceptionHandler( Exception.class )
    public Map<String,Object> handleAll(final Exception ex) {
        Map<String,Object> responseError = new HashMap<>();
        responseError.put("error",HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        responseError.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseError.put("message",ex.getMessage());
        log.error("에러 : "+ex.getMessage());
        return responseError;
    }
}
