package com.dexingworld.hanfu.web.configration.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wonpera on 2016/9/29.
 */
public class DateYMDJsonSerializer extends JsonSerializer<Date> {

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (date != null) {
            jsonGenerator.writeString(format.format(date));
        }
    }
}
