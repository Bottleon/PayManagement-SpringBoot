package com.example.demo.common.aop;

import com.example.demo.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseEntity<Map<String,Object>> handleInvalidArgument(MethodArgumentNotValidException e){
        Map<String,Object> responseErrors = new HashMap<>();
        Map<String,String> data = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error-> data.put(error.getField(),error.getDefaultMessage()));
        responseErrors.put("data",data);
        responseErrors.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        responseErrors.put("status",HttpStatus.BAD_REQUEST.value());
        responseErrors.put("message",e.getMessage());
        log.error("에러 : "+e.getMessage());
        return ResponseEntity.badRequest().body(responseErrors);
    }
    //error :400번
    @ExceptionHandler({IDDuplicatedException.class, IDNotExistException.class, PWMissMatchException.class,MessageVarificationFailed.class})
    public ResponseEntity<Map<String,Object>> handleBadRequest(final RuntimeException re){
        return getMapResponseEntity(re,HttpStatus.BAD_REQUEST);
    }
    //error : 401번
    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<Map<String,Object>> handleUnAuthorized(final RuntimeException re){
        return getMapResponseEntity(re,HttpStatus.UNAUTHORIZED);
    }



    //error : 500번
    @ExceptionHandler( { NotExistStore.class} )
    public ResponseEntity<Map<String,Object>> handleAll(final Exception ex) {
        Map<String,Object> responseError = new HashMap<>();
        responseError.put("error",HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        responseError.put("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseError.put("message",ex.getMessage());
        log.error("에러 : "+ex.getMessage());
        return ResponseEntity.internalServerError().body(responseError);
    }

    @NotNull
    private ResponseEntity<Map<String, Object>> getMapResponseEntity(RuntimeException re,HttpStatus status) {
        Map<String,Object> responseError = new HashMap<>();
        responseError.put("error", status.getReasonPhrase());
        responseError.put("status",status.value());
        responseError.put("message",re.getMessage());
        log.error("에러 : "+re.getMessage());
        return ResponseEntity.badRequest().body(responseError);
    }
}
