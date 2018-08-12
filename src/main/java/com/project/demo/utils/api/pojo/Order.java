package com.project.demo.utils.api.pojo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = -7919046237869173480L;

    @NotNull
    private String item;
    private boolean desc = false;

    public Order() {
    }

    public Order(String item, boolean desc) {
        this.item = item;
        this.desc = desc;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

}
