package com.bbxyard.mp.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.bbxyard.mp.injector.methods.DeleteAll;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {

        List<AbstractMethod> methodList = super.getMethodList(mapperClass);

        // 添加自定义方法
        methodList.add(new DeleteAll());

        return super.getMethodList(mapperClass);
    }
}
