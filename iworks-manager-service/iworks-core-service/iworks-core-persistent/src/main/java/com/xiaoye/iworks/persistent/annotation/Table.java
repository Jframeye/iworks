package com.xiaoye.iworks.persistent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述: 表注解
 * @auther: yehl
 * @date: 2018/12/21 20:49
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    /**
     * 功能描述: 数据库表名称
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2018/12/21 20:47
     */
    String name();

    /**
     * 功能描述: 数据库名称
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2018/12/21 20:47
     */
    String database();

    /**
     * 功能描述: 字段描述
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2018/12/21 20:45
     */
    String comments() default "";
}
