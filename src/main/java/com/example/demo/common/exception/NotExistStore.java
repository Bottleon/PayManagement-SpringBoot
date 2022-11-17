package com.example.demo.common.exception;

import java.util.function.Supplier;

public class NotExistStore extends RuntimeException{
    public NotExistStore(String msg){
        super(msg);
    }
}
