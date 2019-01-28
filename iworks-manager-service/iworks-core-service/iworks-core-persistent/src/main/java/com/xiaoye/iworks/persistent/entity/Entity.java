/**
 * 
**/
package com.xiaoye.iworks.persistent.entity;

import com.xiaoye.iworks.persistent.annotation.Sign;
import com.xiaoye.iworks.utils.ReflectionUtils;
import com.xiaoye.iworks.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 数据库实体类
 * @author yehl
 * @date 2018年6月23日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Entity implements Serializable {
	private static final long serialVersionUID = -6035732612851353934L;

	/** 分库下标 **/
	private String dbIndex;
	
	/** 分表下标 **/
	private String tbIndex;

	public String genSign() {
		// 获取当前对象的签名字段
		List<Field> fields = ReflectionUtils.findAllFields(this.getClass(), false);
		StringBuffer buffer = new StringBuffer();
		for (Field field: fields) {
			if(field.getAnnotation(Sign.class) != null) {
				Object value = ReflectionUtils.getField(field, this);
				if(value != null && StringUtils.isNotBlank(value.toString())) {
					buffer.append("|").append(value.toString());
				}
			}
		}
		return buffer.toString();
	}
}
