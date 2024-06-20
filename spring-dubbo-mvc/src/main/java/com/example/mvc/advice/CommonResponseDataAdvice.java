package com.example.mvc.advice;

import com.example.mvc.annotation.IgnoreResponseAdvice;
import com.example.mvc.vo.ResponseVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * Package: com.example.mvc.advice
 * Date: 2024/5/29
 * Time: 16:34
 * Description: 实现统一响应
 */
@RestControllerAdvice(value = "com.example")
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 有明确指定不需要统一响应的情况下直接返回false
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        return !Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(IgnoreResponseAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ResponseVO<Object> responseVO = new ResponseVO<>("0","success",null);
        if (null == o){
            return responseVO;
        }
        if (o instanceof ResponseVO){
            return o;
        }
        responseVO.setData(o);
        return responseVO;
    }
}
