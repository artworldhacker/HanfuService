package com.dexingworld.hanfu.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * Created by wangpeng on 2016/10/26.
 */
public class ProtoStuffSerializer {


    public static byte[] serializer(Object object,Class clazz){
        RuntimeSchema schema = RuntimeSchema.createFrom(clazz);
        return ProtostuffIOUtil.toByteArray(object, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    public static Object deSerializer(byte[] bytes,Class clazz){
        Object obj = null;
        try {
            obj = clazz.newInstance();
            RuntimeSchema schema = RuntimeSchema.createFrom(clazz);
            ProtostuffIOUtil.mergeFrom(bytes,obj,schema);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
