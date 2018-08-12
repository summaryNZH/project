package com.project.demo.utils.api.json.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project.demo.utils.api.pojo.date.ShortTime;


import java.io.IOException;


/**
 * Created by Chiotis on 2016/11/17.
 */
public class ShortTimeJsonSerializer extends JsonSerializer<ShortTime> {

    @Override
    public void serialize(ShortTime shortTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(shortTime != null ? shortTime.toString() : "null");
    }

}