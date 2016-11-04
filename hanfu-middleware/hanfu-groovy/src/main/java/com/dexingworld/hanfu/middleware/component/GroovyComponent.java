package com.dexingworld.hanfu.middleware.component;

import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.middleware.groovy.GroovyObj;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangpeng on 2016/10/19.
 */
@Component
public class GroovyComponent {

    public static final Logger LOG = LoggerFactory.getLogger(GroovyComponent.class);

    private static GroovyClassLoader groovyClassLoader;

    public static boolean isInit = false;

    private static final String ENCODING = "UTF-8";

    private static final ConcurrentHashMap<String,GroovyObj> groovyObjects = new ConcurrentHashMap<>();

    public void init(){
        groovyClassLoader = new GroovyClassLoader(GroovyComponent.class.getClassLoader());
        try {
            PathMatchingResourcePatternResolver resolver = new  PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:groovy/*.groovy");
            if(resources ==null || resources.length == 0 ){
                throw new Exception("未找到groovy资源文件");
            }
            for (Resource resource : resources){
                String text = new String(IOUtils.toByteArray(resource.getInputStream()),ENCODING);
                Class clazz = groovyClassLoader.parseClass(text,resource.getFilename());
                GroovyObject scriptInstance = (GroovyObject)clazz.newInstance();
                GroovyObj groovyObj = new GroovyObj();
                groovyObj.setGroovyObject(scriptInstance);
                groovyObj.setVersion("0.0.0");
                groovyObjects.put(resource.getFilename().split("\\.")[0],groovyObj);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
        }
        isInit = true;
    }


    /**
     * 调用groovy代码
     * @param code
     * @param method
     * @param params
     * @return
     */
    public ResultResponse invokeGroovyMethod(String code,String method,Map<String,Object> params)  {
        ResultResponse resultResponse = new ResultResponse();
        if(StringUtils.isEmpty(code)){
            return resultResponse.makeFailure("调用的groovy文件名不能为空!");
        }

        if(StringUtils.isEmpty(method)){
            return resultResponse.makeFailure("调用的groovy的方法不能为空!");
        }
        GroovyObj groovyObj = groovyObjects.get(code);
        if(groovyObj==null||groovyObj.getGroovyObject()==null){
            return resultResponse.makeFailure("为匹配到对应的groovy");
        }
        Map<String,Object> resultMap = null;
        try {
            GroovyObject groovyObject = groovyObj.getGroovyObject();
            resultMap = (Map<String, Object>) groovyObject.invokeMethod(method,params);
        } catch (Exception e) {
            return resultResponse.makeFailure(e.getMessage());
        }
        resultResponse.setResult(resultMap);
        return resultResponse.makeSuccessful();
    }


}
