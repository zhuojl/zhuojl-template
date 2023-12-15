package com.zjl.component.mybatisplus.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.zjl.component.mybatisplus.config.methods.DeleteAll;
import com.zjl.component.mybatisplus.config.methods.MyInsertAll;
import com.zjl.component.mybatisplus.config.methods.BatchInsert;

import java.util.List;

/**
 * 自定义 SqlInjector
 *
 * @author miemie
 * @since 2018-08-13
 */
public class MyLogicSqlInjector extends DefaultSqlInjector {

    /**
     * 如果只需增加方法，保留MP自带方法 可以super.getMethodList() 再add
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new DeleteAll());
        methodList.add(new MyInsertAll());
        methodList.add(new BatchInsert());
        methodList.add(new SelectById());
        return methodList;
    }
}
