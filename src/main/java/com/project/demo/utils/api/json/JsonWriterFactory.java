package com.project.demo.utils.api.json;

public class JsonWriterFactory {
    public static final JsonWriter WRITER;

    private JsonWriterFactory() {
    }

    static {
        WRITER = create();
    }

    private static JsonWriter create() {
        try {
            return new JacksonWriter();
        } catch (Throwable e) {
            return new ToStringJsonWriter();
        }
    }
}