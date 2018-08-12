package com.project.demo.utils.api.json.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.project.demo.utils.api.pojo.date.ShortDate;


import java.io.IOException;


/**
 * Created by Chiotis on 2016/11/17.
 */
public class ShortDateJsonDeserializer extends JsonDeserializer<ShortDate> {
    @Override
    public ShortDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String time = jsonParser.getText();
        if (time != null && !time.isEmpty()) {
            return ShortDate.create(time);
        }
        return null;
    }
}