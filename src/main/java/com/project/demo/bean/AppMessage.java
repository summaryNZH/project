package com.project.demo.bean;

import java.util.Date;

/**
 * Created by NingZhenHang 2018/8/12.
 */
public class AppMessage {
    private String id;

    private String message;

    private Date senddate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }
}