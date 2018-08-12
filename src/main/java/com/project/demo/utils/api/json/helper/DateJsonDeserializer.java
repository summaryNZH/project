package com.project.demo.utils.api.json.helper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Chiotis on 2016/11/17.
 */

public class DateJsonDeserializer extends JsonDeserializer<Date> {

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        if (date != null && !date.isEmpty()) {
            try {
                return DateUtils.parseDate(date, DATE_PATTERN);
            } catch (ParseException e) {
                throw new JsonParseException(jsonParser, "cannot parse date string: " + date, jsonParser.getCurrentLocation(), e);
            }
        }
        return null;
    }
}
