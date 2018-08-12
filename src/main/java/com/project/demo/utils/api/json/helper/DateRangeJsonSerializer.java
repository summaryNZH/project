package com.project.demo.utils.api.json.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project.demo.utils.api.pojo.date.DateRange;


import java.io.IOException;


/**
 * Created by Chiotis on 2016/11/17.
 */
public class DateRangeJsonSerializer extends JsonSerializer<DateRange> {

    @Override
    public void serialize(DateRange value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value != null ? value.toString() : "null");
    }

}