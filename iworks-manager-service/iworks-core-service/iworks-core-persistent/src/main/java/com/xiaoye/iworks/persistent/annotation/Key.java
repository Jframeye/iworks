package com.xiaoye.iworks.persistent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述: 主键注解
 * @auther: yehl
 * @date: 2018/12/21 20:44
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Key {

    /**
     * 功能描述: 字段名称
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2018/12/21 20:47
     */
    String name() default "";

    /**
     * 功能描述: 字段描述
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2018/12/21 20:45
     */
    String comments() default "";
}
