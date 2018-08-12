package com.project.demo.utils.api.json.helper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;

/**
 * Created by Chiotis on 2016/11/17.
 */
public class TimestampJsonDeserializer extends JsonDeserializer<Timestamp> {

    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Timestamp deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String date = jp.getText();
        if (date != null && !date.isEmpty()) {
            try {
                java.util.Date utilDate = DateUtils.parseDate(date, DATE_PATTERN);
                return new Timestamp(utilDate.getTime());
            } catch (ParseException e) {
                throw new JsonParseException(jp, "cannot parse date string: " + date, jp.getCurrentLocation(), e);
            }
        }
        return null;
    }
}