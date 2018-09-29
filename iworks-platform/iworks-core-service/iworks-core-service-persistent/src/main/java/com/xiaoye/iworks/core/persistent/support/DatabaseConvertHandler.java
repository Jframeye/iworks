/**
 * 
 */
package com.xiaoye.iworks.core.persistent.support;

import com.google.common.collect.Maps;
import com.xiaoye.iworks.core.persistent.entity.Database;
import com.xiaoye.iworks.utils.ReflectionUtils;
import com.xiaoye.iworks.utils.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据模型转换类
 * @author yehl
 * @date 2018年6月23日
 */
public class DatabaseConvertHandler {

	public static DatabaseMeta convert(Database entity) {
		Class<? extends Database> clazz = entity.getClass();
		if (clazz == Database.class) {
			throw new RuntimeException("the database model [%s] can not be com.xiaoye.iworks.basic.database.entity.DO.");
		}
		Table table = clazz.getAnnotation(Table.class);
		if(table == null) {
			throw new RuntimeException(String.format("the database model [%s] has no javax.persistence.Table annotation.", clazz.getName()));
		}
		DatabaseMeta meta = new DatabaseMeta(table.schema(), table.name());
		List<Field> fields = ReflectionUtils.findAllFields(clazz, true);
		String primaryKey = null;
		Map<String, String> columnFieldMap = Maps.newConcurrentMap();
		Map<String, String> columnValueMap = Maps.newConcurrentMap();
		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			Id id = field.getAnnotation(Id.class);
			if(id != null) {
				// 判断主键是递增主键
				if(primaryKey != null) {
					throw new RuntimeException("the primary key error, primary key must be only one.");
				}
				GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
				if(generatedValue.strategy() != GenerationType.IDENTITY) {
					throw new RuntimeException("the primary key error, it should be auto createment the strategy must be GenerationType.IDENTITY.");
				}
				String columnName = null;
				if(column == null) {
					columnName = StringUtils.camel2Underline(field.getName());
				} else {
					columnName = column.name();
					if (StringUtils.isEmpty(columnName)) {
						columnName = StringUtils.camel2Underline(field.getName());
					}
					columnFieldMap.put(String.format("`%s`", columnName), field.getName());
					String value = StringUtils.getStringValue(ReflectionUtils.getField(field, entity));
					if (StringUtils.isNotEmpty(value)) {
						columnValueMap.put(String.format("`%s`", columnName), value);
					}
				}
				primaryKey = String.format("`%s`", columnName);
				meta.setPrimaryKey(primaryKey);
			} else {
				if(column != null) {
					String columnName = column.name();
					if (StringUtils.isEmpty(columnName)) {
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
	
	public static DatabaseMeta convert(Class<? extends Database> clazz) {
		if (clazz == Database.class) {
			throw new RuntimeException("the database model [%s] can not be com.xiaoye.iworks.basic.database.entity.DO.");
		}
		Table table = clazz.getAnnotation(Table.class);
		if(table == null) {
			throw new RuntimeException(String.format("the database model [%s] has no javax.persistence.Table annotation.", clazz.getName()));
		}
		DatabaseMeta meta = new DatabaseMeta(table.schema(), table.name());
		List<Field> fields = ReflectionUtils.findAllFields(clazz, true);
		String primaryKey = null;
		Map<String, String> columnFieldMap = Maps.newConcurrentMap();
		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			Id id = field.getAnnotation(Id.class);
			if(id != null) {
				// 判断主键是递增主键
				if(primaryKey != null) {
					throw new RuntimeException("the primary key error, primary key must be only one.");
				}
				GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
				if(generatedValue.strategy() != GenerationType.IDENTITY) {
					throw new RuntimeException("the primary key error, it should be auto createment the strategy must be GenerationType.IDENTITY.");
				}
				String columnName = null;
				if(column == null) {
					columnName = StringUtils.camel2Underline(field.getName());
				} else {
					columnName = column.name();
					if (StringUtils.isEmpty(columnName)) {
						columnName = StringUtils.camel2Underline(field.getName());
					}
					columnFieldMap.put(String.format("`%s`", columnName), field.getName());
				}
				primaryKey = String.format("`%s`", columnName);
				meta.setPrimaryKey(primaryKey);
			} else {
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
