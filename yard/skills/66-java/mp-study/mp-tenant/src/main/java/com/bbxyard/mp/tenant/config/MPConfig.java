package com.bbxyard.mp.tenant.config;


import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MPConfig {

    /**
     * 多租户属于 SQL 解析部分，依赖 MP 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor pi = new PaginationInterceptor();

        /*
         * 【测试多租户】 SQL 解析处理拦截器<br>
         * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
         */
        List<ISqlParser> sqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new MyTenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            /**
             * 2019-8-1
             *
             * https://gitee.com/baomidou/mybatis-plus/issues/IZZ3M
             *
             * tenant_id in (1,2)
             *
             * @return
             */
            @Override
            public Expression getTenantId(boolean where) {
                final boolean multipleTenantIds = true;
                Expression exp = (where && multipleTenantIds)? polyTenantIdCondition() : singleTenantIdCondition();
                return exp;
            }

            @Override
            public String getTenantIdColumn() {
                return "tenant_id";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                return !"mp_tenant_user".equalsIgnoreCase(tableName);
            }

            private Expression singleTenantIdCondition() {
                return new LongValue(1); // ID自己想办法获取到
            }

            private Expression polyTenantIdCondition() {
                final InExpression inExpression = new InExpression();
                inExpression.setLeftExpression(new Column(getTenantIdColumn()));
                final ExpressionList itemsList = new ExpressionList();
                final List<Expression> inValues = new ArrayList<>(2);
                inValues.add(new LongValue(1));
                inValues.add(new LongValue(2)); // ID 自行处理
                itemsList.setExpressions(inValues);
                inExpression.setRightItemsList(itemsList);
                return inExpression;
            }
        });
        sqlParserList.add(tenantSqlParser);
        pi.setSqlParserList(sqlParserList);

        return pi;
    }

}
