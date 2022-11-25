package com.example.demo.common.exception;

public class MessageVarificationFailed extends RuntimeException{
    public MessageVarificationFailed(String msg){
        super(msg);
    }
}
