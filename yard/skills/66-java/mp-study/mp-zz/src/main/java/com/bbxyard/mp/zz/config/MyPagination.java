package com.bbxyard.mp.zz.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyPagination {

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor pi = new PaginationInterceptor();
        // pi.setLimit(你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制);
        return pi;
    }

}
