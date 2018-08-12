package com.project.demo.utils.api.pojo.exception;

/**
 * 这个类仅作为消息传递, 不携带stack
 * Created by Chiotis on 2016/11/17.
 */
public class RuntimeErrorMessage extends RuntimeException implements CodeMessage {

    private final int code;
    private Object data;

    public RuntimeErrorMessage(int code) {
        this.code = code;
    }

    public RuntimeErrorMessage(int code, String message) {
        super(message);
        this.code = code;
    }


    @Override
    public int getStatus() {
        return code;
    }

    @Override
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public Throwable initCause(Throwable cause) {
        return this;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}