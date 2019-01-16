package com.xiaoye.iworks.utils.handler;

import java.util.List;

/**
 * @Desc 集合分页处理
 * @Author yehl
 * @Date 2018年1月18日
 */
public interface ListPagerHandler<T> {

	public void control(List<T> list);
}
