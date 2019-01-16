package com.xiaoye.iworks.utils.handler;

/**
 * @Desc 集合转换接口
 * @Author yehl
 * @Date 2018年1月17日
 */
public interface ListTransformHandler<F, T> {

	public T transform(F f);
}
