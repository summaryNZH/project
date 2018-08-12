package com.project.demo.utils.api.json;


public class JacksonWriter implements JsonWriter {
    private static final JsonMapper mapper = MapperBuilder.create().disable(JsonFeature.AUTO_CLOSE_TARGET).build();

    @Override
    public String write(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (Throwable e) {
            return "";
        }
    }
}
