package com.xiaoye.iworks.utils;

import org.springframework.beans.BeanUtils;

/**
 * @Desc 对象工具类
 * @Author yehl
 * @Date 2018年1月23日
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

	/**
	 * 对象数据拷贝<br>
	 * 属性类型保持一致（简单类型会自动转换）<br>
	 * null值也会复制过来，不支持不同类型自动转换，包装类除外
	 * @param f
	 * @param clazz
	 * @return
	 */
	public static <T, F> T cloneData(F f, Class<T> clazz) {
		if (f == null) {
			return null;
		}

		try {
			T t = clazz.newInstance();
			BeanUtils.copyProperties(f, t);
			return t;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
