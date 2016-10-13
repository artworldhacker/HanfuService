package com.dexingworld.hanfu.utils.http.httpclient;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* Created by wangpeng on 2016/7/1.
*/
public class HttpUtils {


    private static final String ENCODE = "utf-8";

    private static final String USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4";

    private PoolingHttpClientConnectionManager connectionManager;

    private CloseableHttpClient httpClient;

    public HttpUtils() {
       init(null,null);
    }

    public HttpUtils(Proxy proxy, String userAgent) {
        init(proxy,userAgent);
    }

    public HttpUtils(Proxy proxy) {
        init(proxy,USER_AGENT);
    }

    private void init(Proxy proxy,String userAgent){
        Registry<ConnectionSocketFactory> reg = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        connectionManager = new PoolingHttpClientConnectionManager(reg);
        connectionManager.setDefaultMaxPerRoute(100);
        this.httpClient = generateClient(proxy,userAgent);
    }

    private static final int RETRY_TIME = 5;

    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";

    public CloseableHttpClient generateClient(Proxy proxy,String userAgent){
        CredentialsProvider credentialsProvider = null;
        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpClientBuilder httpClientBuilder = HttpClients.custom().setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext))
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(RETRY_TIME, true));
        if(proxy != null ){
            credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(
                    new AuthScope(proxy.getIp(),proxy.getPort()),
                    new UsernamePasswordCredentials(proxy.getUsername(),proxy.getPassword())
            );
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
        }
        if(StringUtils.isNoneBlank(userAgent)){
            httpClientBuilder.setUserAgent(userAgent);
        }else{
            httpClientBuilder.setUserAgent(DEFAULT_USER_AGENT);
        }
        /*httpClientBuilder.setConnectionManager(connectionManager);*/
        SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).setTcpNoDelay(true).build();
        httpClientBuilder.setDefaultSocketConfig(socketConfig);
        //重定向策略
        httpClientBuilder.setRedirectStrategy(new DefaultRedirectStrategy() {
            @Override
            public boolean isRedirected(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
                if(httpResponse != null){
                    int status = httpResponse.getStatusLine().getStatusCode();
                    if(status == HttpStatus.SC_MOVED_PERMANENTLY || status == HttpStatus.SC_MOVED_TEMPORARILY){
                        return true;
                    }
                }

                return false;
            }
        });
        return httpClientBuilder.build();
    }

    public HttpResponse execute(HttpUriRequest httpUriRequest){
        HttpResponse httpResponse = null;
        try {
            int i = 0;
            do {
                httpResponse = httpClient.execute(httpUriRequest);
                i++;
            }while (httpResponse == null && i< RETRY_TIME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }

    public String doExecuteCallString(Request request){
        HttpUriRequest httpUriRequest = getHttpUriRequest(request,null);
        HttpResponse httpResponse = execute(httpUriRequest);
        int status = httpResponse.getStatusLine().getStatusCode();
        if(status == HttpStatus.SC_OK){
            return getContent(null,httpResponse);
        }
        return null;
    }

    public byte[] doExecuteCallByte(Request request){
        try {
            HttpUriRequest httpUriRequest = getHttpUriRequest(request,null);
            HttpResponse httpResponse = execute(httpUriRequest);
            int status = httpResponse.getStatusLine().getStatusCode();
            if(status == HttpStatus.SC_OK){
                return  IOUtils.toByteArray(httpResponse.getEntity().getContent());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getContent(String charset,HttpResponse httpResponse){
        try {
            if(charset == null){
                byte[] contentBytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
                String htmlCharset = getHtmlCharset(httpResponse,contentBytes);
                if(StringUtils.isNoneBlank(htmlCharset)){
                    return new String(contentBytes,htmlCharset);
                }else{
                    return new String(contentBytes);
                }
            }else{
                return IOUtils.toString(httpResponse.getEntity().getContent(),charset);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static final Pattern patternForCharset = Pattern.compile("charset\\s*=\\s*['\"]*([^\\s;'\"]*)");

    public static String getCharset(String contentType) {
        Matcher matcher = patternForCharset.matcher(contentType);
        if (matcher.find()) {
            String charset = matcher.group(1);
            if (Charset.isSupported(charset)) {
                return charset;
            }
        }
        return null;
    }


    public String getHtmlCharset(HttpResponse httpResponse,byte[] contentBytes)throws IOException{
        String charset;
        String value = httpResponse.getEntity().getContentType().getValue();
        charset = getCharset(value);
        if(StringUtils.isNoneBlank(charset)){
            return charset;
        }
        Charset defualtCharset = Charset.defaultCharset();
        String content = new String(contentBytes,defualtCharset.name());
        if(StringUtils.isNoneBlank(content)){
            Document doc = Jsoup.parse(content);
            Elements links = doc.select("meta");
            for(Element link : links){
                String metaContent = link.attr("content");
                String metaCharset = link.attr("charset");
                if (metaContent.indexOf("charset") != -1) {
                    metaContent = metaContent.substring(metaContent.indexOf("charset"), metaContent.length());
                    charset = metaContent.split("=")[1];
                    break;
                }
                // 2.2、html5 <meta charset="UTF-8" />
                else if (StringUtils.isNotEmpty(metaCharset)) {
                    charset = metaCharset;
                    break;
                }
            }
        }
        return charset;

    }

    public HttpUriRequest getHttpUriRequest(Request request, HttpHost proxy){
        RequestBuilder requestBuilder = selectRequstMethod(request).setUri(request.getUrl());
        Map<String,String> headers = request.getHeaders();
        if(headers != null){//加入请求头
            for(Map.Entry<String,String> entry : headers.entrySet()){
                requestBuilder.addHeader(entry.getKey(),entry.getValue());
            }
        }

        RequestConfig.Builder requestConfigBuild = RequestConfig.custom()
//                .setConnectionRequestTimeout(site.getTimeOut())
//                .setSocketTimeout(site.getTimeOut())
//                .setConnectTimeout(site.getTimeOut())
                .setCookieSpec(CookieSpecs.BEST_MATCH);

        if(proxy != null){//设置代理
            requestConfigBuild.setProxy(proxy);
            request.setExtras(Request.PROXY, proxy);
        }
        requestBuilder.setConfig(requestConfigBuild.build());
        return requestBuilder.build();

    }

    private static final String CHARSET = "utf-8";

    public RequestBuilder selectRequstMethod(Request request){
        String method = request.getMethod();
        if(method == null || method.equalsIgnoreCase(HttpConstant.Method.GET)){
            return RequestBuilder.get();
        }else if(method.equalsIgnoreCase(HttpConstant.Method.POST)){
            RequestBuilder requestBuilder = RequestBuilder.post();
            String entityType = (String)request.getExtras().get(request.ENTITY_TYPE_STRING);
            try {
                if(request.ENTITY_TYPE_STRING.equalsIgnoreCase(entityType)){
                    HttpEntity httpEntity = new StringEntity(request.getStringEntity() , CHARSET);
                    requestBuilder.setEntity(httpEntity);
                }else{
                    List<NameValuePair> nvps = request.getNvps();
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, CHARSET);
                    requestBuilder.setEntity(entity);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return requestBuilder;
        }else if(method.equalsIgnoreCase(HttpConstant.Method.HEAD)){
            return RequestBuilder.head();
        }else if(method.equalsIgnoreCase(HttpConstant.Method.PUT)){
            return RequestBuilder.put();
        }else if(method.equalsIgnoreCase(HttpConstant.Method.DELETE)){
            return RequestBuilder.delete();
        }else if(method.equalsIgnoreCase(HttpConstant.Method.TRACE)){
            return RequestBuilder.trace();
        }else {
            throw new IllegalArgumentException("Illegal HTTP Method " + method);
        }
    }
}
