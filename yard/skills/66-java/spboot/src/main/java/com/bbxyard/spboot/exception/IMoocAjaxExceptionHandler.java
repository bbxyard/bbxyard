package com.bbxyard.spboot.exception;


import com.bbxyard.spboot.dto.HttpRespMsg;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

// @RestControllerAdvice
public class IMoocAjaxExceptionHandler {

    // @ExceptionHandler(value = Exception.class)
    public HttpRespMsg defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        e.printStackTrace();
        return HttpRespMsg.Error(e.getMessage(), 555);
    }

}
