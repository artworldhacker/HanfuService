package com.dexingworld.hanfu.web.configration.advice;

import com.dexingworld.hanfu.web.response.ResultResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by wonpera on 2016/9/29.
 */
public class LogViewResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return ResultResponse.class.isAssignableFrom(returnType.getMethod()
                .getReturnType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body != null && body instanceof ResultResponse) {
            ResultResponse result = (ResultResponse) body;
            if (!result.isStatus()
                    && request instanceof ServletServerHttpRequest) {
                ((ServletServerHttpRequest) request).getServletRequest()
                        .setAttribute("response_body_object_status",
                                result.getError());
            }
        }
        return body;
    }
}
