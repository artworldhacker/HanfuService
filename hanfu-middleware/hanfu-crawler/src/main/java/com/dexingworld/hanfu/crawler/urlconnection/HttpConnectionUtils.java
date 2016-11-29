package com.dexingworld.hanfu.crawler.urlconnection;

import com.dexingworld.hanfu.crawler.enums.HttpMethodEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wangpeng on 2016/10/13.
 */
public class HttpConnectionUtils {


    private static final Logger LOGGER = LoggerFactory.getLogger(HttpConnectionUtils.class);

    public static final String CHARSAET = "UTF-8";

    public static String get(String link) {
        try {
            return  new String(getBytes(link),CHARSAET);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }


    public static byte[] getBytes(String link){
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod(HttpMethodEnum.GET.toString());
            connection.connect();
            InputStream is = connection.getInputStream();
            return StreamUtils.copyToByteArray(is);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
        return null;
    }

    public static byte[] postBytes(String link,String param){
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url .openConnection();
            connection.setRequestMethod(HttpMethodEnum.POST.toString());
            //设置参数
            connection.setDoOutput(true);   //需要输出
            connection.setDoInput(true);   //需要输入
            connection.setUseCaches(false);  //不允许缓存
            connection.setRequestMethod("POST");   //设置POST方式连接
            //设置请求属性
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            connection.setRequestProperty("Charset", "UTF-8");
            connection.connect();
            //建立输入流，向指向的URL传入参数
            DataOutputStream dos=new DataOutputStream(connection.getOutputStream());
            dos.writeBytes(param);
            dos.flush();
            dos.close();
            //获得响应状态
            int resultCode=connection.getResponseCode();
            if(HttpURLConnection.HTTP_OK==resultCode){
                InputStream is = connection.getInputStream();
                return StreamUtils.copyToByteArray(is);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
        return null;
    }

    public static String post(String link,String param){
        try {
            return  new String(postBytes(link,param),CHARSAET);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return null;
    }
}
