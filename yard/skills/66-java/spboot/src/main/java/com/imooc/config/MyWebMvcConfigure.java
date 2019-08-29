package com.imooc.config;

import com.imooc.controller.interceptor.handlers.OneInterceptor;
import com.imooc.controller.interceptor.handlers.TwoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyWebMvcConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 单一hook
         */
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/hook/one/**");
        registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/hook/two/**");

        /**
         * 拦截器，按顺序hook
         */
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/hook/one-two/**");
        registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/hook/one-two/**");

        registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/hook/two-one/**");
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/hook/two-one/**");
    }
}
