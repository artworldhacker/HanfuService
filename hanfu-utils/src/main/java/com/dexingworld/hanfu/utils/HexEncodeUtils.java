package com.dexingworld.hanfu.utils;

/**
 * Created by wangpeng on 2016/12/15.
 */
public class HexEncodeUtils {

    public static void main(String[] args) {
        String str = formatObj2HexJson("123456");
        System.out.println(str);
        System.out.println(parseHexJson2Obj(str,String.class));
    }


    /**
     * format object to hex-json
     * @param obj
     * @return result
     */
    public static String formatObj2HexJson(Object obj){
        // obj to json
        String json = GsonUtils.writeValueAsString(obj);
        int len = ByteHexConverter.getByteLen(json);

        // json to byte[]
        ByteWriteFactory byteWriteFactory = new ByteWriteFactory(4 + len);
        byteWriteFactory.writeInt(len);
        byteWriteFactory.writeString(json, len);
        byte[] bytes = byteWriteFactory.getBytes();

        // byte to hex
        String hex = ByteHexConverter.byte2hex(bytes);
        return hex;
    }

    /**
     * parse hex-json to object
     * @param hex
     * @param clazz
     * @return result
     */
    public static <T> T parseHexJson2Obj(String hex, Class<T> clazz){
        // hex to byte[]
        byte[] bytes = ByteHexConverter.hex2Byte(hex);

        // byte[] to json
        ByteReadFactory byteReadFactory = new ByteReadFactory(bytes);
        String json = byteReadFactory.readString(byteReadFactory.readInt());

        // json to obj
        T obj = GsonUtils.readValue(json, clazz);
        return obj;
    }
}
