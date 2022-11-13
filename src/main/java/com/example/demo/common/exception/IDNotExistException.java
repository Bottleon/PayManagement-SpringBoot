package com.example.demo.common.exception;

public class IDNotExistException extends RuntimeException{
    public IDNotExistException(String msg){
        super(msg);
    }
}
