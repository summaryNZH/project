package com.project.demo.utils.api.json.helper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;

/**
 * Created by Chiotis on 2016/11/17.
 */
public class SqlTimeJsonDeserializer extends JsonDeserializer<Time> {

    public static final String DATE_PATTERN = "HH:mm:ss";

    @Override
    public Time deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String date = jp.getText();
        if (date != null && !date.isEmpty()) {
            try {
                java.util.Date utilDate = DateUtils.parseDate(date, DATE_PATTERN);
                return new Time(utilDate.getTime());
            } catch (ParseException e) {
                throw new JsonParseException(jp, "cannot parse date string: " + date, jp.getCurrentLocation(), e);
            }
        }
        return null;
    }
}