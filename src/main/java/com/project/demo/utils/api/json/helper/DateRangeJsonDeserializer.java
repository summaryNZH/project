package com.project.demo.utils.api.json.helper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import com.project.demo.utils.api.pojo.date.DateRange;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;


/**
 * Created by Chiotis on 2016/11/17.
 */
public class DateRangeJsonDeserializer extends JsonDeserializer<DateRange> {
    @Override
    public DateRange deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        String source = p.getText();
        if (StringUtils.isBlank(source)) return null;
        if (StringUtils.equalsIgnoreCase("null", source)) return null;

        try {
            return DateRange.create(source) ;
        } catch (Throwable t) {
            throw new JsonParseException(p, "cannot parse date range string: " + source, p.getCurrentLocation(), t);
        }
    }
}