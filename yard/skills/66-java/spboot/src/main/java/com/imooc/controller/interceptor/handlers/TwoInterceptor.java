package com.imooc.controller.interceptor.handlers;

import com.imooc.pojo.IMoocJSONResult;
import com.sun.tools.internal.ws.wsdl.document.Output;
import me.n3r.utils.JsonUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class TwoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (true) {
            System.out.println("[TWO] 被Two拦截, 并改写...");
            returnErrorResponse(response, IMoocJSONResult.errorMsg("被two拦截，并改写..."));
            return false;
        } else {
            System.out.println("[TWO] 被Two拦截, 放行...");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("[TWO] postHandle: " + "done");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("[TWO] afterCompletion: " + "done" );
    }

    public void returnErrorResponse(HttpServletResponse response, IMoocJSONResult result) throws IOException {
        OutputStream out = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.close();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
