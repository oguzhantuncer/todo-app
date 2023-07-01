package com.oguzhantuncer.todo.error;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class ErrorDetailDTO {

    private String key;
    private String message;
    private String errorCode;
    private String[] args = ArrayUtils.EMPTY_STRING_ARRAY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "ErrorDetailDTO{" +
                "key='" + key + '\'' +
                ", message='" + message + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }

}
