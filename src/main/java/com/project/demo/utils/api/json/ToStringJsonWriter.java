package com.project.demo.utils.api.json;

public class ToStringJsonWriter implements JsonWriter {
    @Override
    public String write(Object value) {
        if (value == null)
            return "";
        return value.toString();
    }
}
