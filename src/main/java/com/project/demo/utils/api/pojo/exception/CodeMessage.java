package com.project.demo.utils.api.pojo.exception;

public interface CodeMessage {

    public static final int OK = 0;
    public static final int SYSTEM_ERROR = -1;

    int getStatus();

    String getMessage();

    Object getData();

}
