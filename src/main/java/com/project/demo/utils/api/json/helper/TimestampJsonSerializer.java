package com.project.demo.utils.api.json.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.time.FastDateFormat;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by Chiotis on 2016/11/17.
 */
public class TimestampJsonSerializer extends JsonSerializer<Timestamp> {

    public static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance(TimestampJsonDeserializer.DATE_PATTERN);


    @Override
    public void serialize(Timestamp date, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(date != null ? DATE_FORMAT.format(date) : "null");
    }
}

