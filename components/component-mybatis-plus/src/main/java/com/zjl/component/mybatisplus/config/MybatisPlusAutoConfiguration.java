package com.zjl.component.mybatisplus.config;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.context.annotation.Bean;

/**
 * @author miemie
 * @since 2018-08-10
 */
public class MybatisPlusAutoConfiguration {

    @Bean
    public MyLogicSqlInjector myLogicSqlInjector() {
        return new MyLogicSqlInjector();
    }

    /**
     * 在当前项目中使用的版本是3.1.0, 没有自定义id生成器！！因为在各处写死的配置，所以无法动态处理，在
     * 3.3.0中，提供动态配置，详见官网
     *
     * 核心逻辑在于com.baomidou.mybatisplus.core.MybatisConfiguration#MybatisConfiguration()
     * 设置了MybatisXMLLanguageDriver
     * @return
     */
    @Bean
    public IdentifierGenerator identifierGenerator() {
        // 这里就可以自定制算法，采用雪花算法，workerId可取自mysql
        return new DefaultIdentifierGenerator();
    }
}
