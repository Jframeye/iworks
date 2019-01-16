package com.xiaoye.iworks.utils.handler;

/**
 * @Desc 集合查找接口
 * @Author yehl
 * @Date 2018年1月17日
 */
public interface ListToMapHandler<K, T> {

	public K getKey(T t);
}
