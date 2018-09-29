package com.xiaoye.iworks.core.persistent;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis扫描路径配置
 * @author yehl
 * @date 2018年6月23日
 */
@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class MybatisMapperScanner {
	
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //获取之前注入的beanName为sqlSessionFactory的对象
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //指定mapper的路径
        mapperScannerConfigurer.setBasePackage("com.xiaoye.microservices.*.dao.mybatis.mapper");
        return mapperScannerConfigurer;
    }
}