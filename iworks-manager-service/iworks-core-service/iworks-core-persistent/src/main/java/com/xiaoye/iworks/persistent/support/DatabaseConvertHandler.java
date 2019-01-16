/**
 * 
 */
package com.xiaoye.iworks.persistent.support;

import com.google.common.collect.Maps;
import com.xiaoye.iworks.utils.ReflectionUtils;
import com.xiaoye.iworks.utils.StringUtils;
import com.xiaoye.iworks.persistent.annotation.Column;
import com.xiaoye.iworks.persistent.annotation.Key;
import com.xiaoye.iworks.persistent.annotation.Table;
import com.xiaoye.iworks.persistent.entity.Entity;
import com.xiaoye.iworks.persistent.exception.DatabaseException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 数据模型转换类
 * @author yehl
 * @date 2018年6月23日
 */
public class DatabaseConvertHandler {

	/**
	 * 功能描述: 数据库实体类处理（后期加上本地缓存）
	 * @param:
	 * @return:
	 * @auther: yehl
	 * @date: 2018/12/22 13:13
	 */
	public static DatabaseMeta convert(Entity entity) {
		Class<? extends Entity> clazz = entity.getClass();
		if (clazz == Entity.class) {
			throw new DatabaseException("the database model [%s] can not be com.xiaoye.dubbo.persistent.entity.Entity.");
		}
		Table table = clazz.getAnnotation(Table.class);
		if(table == null) {
			throw new DatabaseException(String.format("the database model [%s] has no com.xiaoye.dubbo.persistent.annotation.Table annotation.", clazz.getName()));
		}
		DatabaseMeta meta = new DatabaseMeta(table.database(), table.name());
		List<Field> fields = ReflectionUtils.findAllFields(clazz, true);
		String primaryKey = null;
		Map<String, String> columnFieldMap = Maps.newLinkedHashMap();
		Map<String, String> columnValueMap = Maps.newLinkedHashMap();
		for (Field field : fields) {
			Key key = field.getAnnotation(Key.class);
			if(key != null) {
				if(primaryKey != null) {
					throw new DatabaseException("the primary key error, primary key must be only one.");
				}
				String columnName = key.name();
				if(StringUtils.isBlank(columnName)) {
					columnName = StringUtils.camel2Underline(field.getName());
				}
				columnFieldMap.put(String.format("`%s`", columnName), field.getName());
				String value = StringUtils.getStringValue(ReflectionUtils.getField(field, entity));
				if (StringUtils.isNotEmpty(value)) {
					columnValueMap.put(String.format("`%s`", columnName), value);
				}
				primaryKey = String.format("`%s`", columnName);
				meta.setPrimaryKey(primaryKey);
			} else {
				Column column = field.getAnnotation(Column.class);
				if(column != null) {
					String columnName = column.name();
					if (StringUtils.isBlank(columnName)) {
						columnName = StringUtils.camel2Underline(field.getName());
					}
					columnFieldMap.put(String.format("`%s`", columnName), field.getName());
					String value = StringUtils.getStringValue(ReflectionUtils.getField(field, entity));
					if (StringUtils.isNotEmpty(value)) {
						columnValueMap.put(String.format("`%s`", columnName), value);
					}
				}
			}
		}
		meta.setColumnFieldMap(columnFieldMap);
		meta.setColumnValueMap(columnValueMap);
		return meta;
	}

	/**
	 * 功能描述: 数据库实体类处理（后期加上本地缓存）
	 * @param:
	 * @return:
	 * @auther: yehl
	 * @date: 2018/12/22 13:13
	 */
	public static DatabaseMeta convert(Class<? extends Entity> clazz) {
		if (clazz == Entity.class) {
			throw new DatabaseException("the database model [%s] can not be com.xiaoye.dubbo.persistent.entity.Entity.");
		}
		Table table = clazz.getAnnotation(Table.class);
		if(table == null) {
			throw new RuntimeException(String.format("the database model [%s] has no com.xiaoye.dubbo.persistent.annotation.Table annotation.", clazz.getName()));
		}
		DatabaseMeta meta = new DatabaseMeta(table.database(), table.name());
		List<Field> fields = ReflectionUtils.findAllFields(clazz, true);
		String primaryKey = null;
		Map<String, String> columnFieldMap = Maps.newConcurrentMap();
		for (Field field : fields) {
			Key key = field.getAnnotation(Key.class);
			if(key != null) {
				// 判断主键是递增主键
				if(primaryKey != null) {
					throw new DatabaseException("the primary key error, primary key must be only one.");
				}
				String columnName = key.name();
				if(StringUtils.isBlank(columnName)) {
					columnName = StringUtils.camel2Underline(field.getName());
				}
				columnFieldMap.put(String.format("`%s`", columnName), field.getName());
				primaryKey = String.format("`%s`", columnName);
				meta.setPrimaryKey(primaryKey);
			} else {
				Column column = field.getAnnotation(Column.class);
				if(column != null) {
					String columnName = column.name();
					if (StringUtils.isEmpty(columnName)) {
						columnName = StringUtils.camel2Underline(field.getName());
					}
					columnFieldMap.put(String.format("`%s`", columnName), field.getName());
				}
			}
		}
		meta.setColumnFieldMap(columnFieldMap);
		return meta;
	}
}
