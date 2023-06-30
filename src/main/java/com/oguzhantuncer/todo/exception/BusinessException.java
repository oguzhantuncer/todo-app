package com.oguzhantuncer.todo.exception;

public class BusinessException extends BaseException{
    public BusinessException(String key) {super(key);}
    public BusinessException(String key, String... args) {super(key, args);}
}
