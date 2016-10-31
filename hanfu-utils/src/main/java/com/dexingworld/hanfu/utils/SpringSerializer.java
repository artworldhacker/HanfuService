package com.dexingworld.hanfu.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;

/**
 * Created by wangpeng on 2016/10/31.
 */
public class SpringSerializer {

    private static Converter<Object, byte[]> serializer = new SerializingConverter();
    private static Converter<byte[], Object> deserializer = new DeserializingConverter();

    public static Object deserialize(byte[] bytes){
        return bytes != null && bytes.length != 0? deserializer.convert(bytes):null;
    }

    public static byte[] serialize(Object object) {
        return object == null?new byte[0]:(byte[])((byte[])serializer.convert(object));
    }
}
