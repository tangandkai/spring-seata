package com.example.mvc.advice;

import com.example.mvc.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Package: com.example.mvc.advice
 * Date: 2024/5/29
 * Time: 16:44
 * Description: 全局异常捕获处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseVO<String> handlerCommerceException(
            HttpServletRequest req, Exception ex
    ) {
        ResponseVO<String> response = new ResponseVO<>("-1", "business error",null);
        response.setData(ex.getMessage());
        log.error("commerce service has error: [{}]", ex.getMessage(), ex);
        return response;
    }
}
