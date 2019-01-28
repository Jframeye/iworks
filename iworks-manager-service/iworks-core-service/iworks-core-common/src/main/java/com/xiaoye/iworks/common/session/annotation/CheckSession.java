package com.xiaoye.iworks.common.session.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述: 校验
 * @auther: yehl
 * @date: 2019/1/27 15:13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSession {

    /**
     * 功能描述: 权限标识
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/28 11:53
     */
    String permission() default "";
}
