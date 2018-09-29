package com.xiaoye.iworks.utils.handler;

/**
 * 过滤器
 * @author yehl
 * @date 2018/9/17 17:09
 */
public interface FilterHandler<T> {

    public boolean filter(T t);
}
