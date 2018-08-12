package com.project.demo.utils.api.json.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project.demo.utils.api.pojo.date.ShortDate;


import java.io.IOException;


/**
 * Created by Chiotis on 2016/11/17.
 */
public class ShortDateJsonSerializer extends JsonSerializer<ShortDate> {

    @Override
    public void serialize(ShortDate shortDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(shortDate != null ? shortDate.toString() : "null");
    }

}
