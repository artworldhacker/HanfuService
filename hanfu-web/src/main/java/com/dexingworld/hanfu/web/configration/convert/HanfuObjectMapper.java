package com.dexingworld.hanfu.web.configration.convert;

import com.dexingworld.hanfu.web.configration.serialize.DateYMDJsonSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by wangpeng on 2016/10/8.
 */
public class HanfuObjectMapper extends ObjectMapper {


    public HanfuObjectMapper() {
        super();
        this.getSerializerProvider().setNullValueSerializer(new NullSerializer());
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        SimpleModule module = new SimpleModule();
        module.addSerializer(new DateYMDJsonSerializer());
        module.addSerializer(new NullSerializer());
    }


    //null的JSON序列
    private class NullSerializer extends JsonSerializer<Object> {
        public void serialize(Object value, JsonGenerator jgen,
                              SerializerProvider provider) throws IOException,
                JsonProcessingException {
            jgen.writeString("");
        }
    }


}
