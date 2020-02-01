package com.github.imloama.shutdown.springboot.starter.config;

public class ShutdownException extends Exception {

    public ShutdownException(){}

    public ShutdownException(String msg){
        super(msg);
    }

    public ShutdownException(String msg, Throwable cause){
        super(msg, cause);
    }


}
