package com.oguzhantuncer.todo.exception;

public class SystemException extends BaseException{
    public SystemException(String key) {super(key);}
    public SystemException(String key, String... args) {super(key, args);}
}
