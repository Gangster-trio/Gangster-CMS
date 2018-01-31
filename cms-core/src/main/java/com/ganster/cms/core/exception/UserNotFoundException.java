package com.ganster.cms.core.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(){
        super();
    }
    public UserNotFoundException(String msg){
        super(msg);
    }
}
