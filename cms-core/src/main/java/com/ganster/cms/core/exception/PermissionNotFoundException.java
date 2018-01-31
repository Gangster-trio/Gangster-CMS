package com.ganster.cms.core.exception;

public class PermissionNotFoundException extends Exception{
    public PermissionNotFoundException(){
        super();
    }

    public PermissionNotFoundException(String msg){
        super(msg);
    }
}
