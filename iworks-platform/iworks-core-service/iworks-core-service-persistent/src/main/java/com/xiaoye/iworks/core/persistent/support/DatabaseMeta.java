/**
 * 
 */
package com.xiaoye.iworks.core.persistent.support;

import lombok.Data;

import java.util.Map;

/**
 * 数据实体类元信息
 * @author yehl
 * @date 2018年6月23日
 */
@Data
public class DatabaseMeta {
	
	/** 数据库名称 **/
	private String database;

	/** 实体对应的表名称 **/
	private String tableName;
	
	/** 数据库主键(单个，递增主键) **/
	private String primaryKey;

	/** 数据库字段对应实体属性名集合 **/
	private Map<String, String> columnFieldMap;

	/** 数据库字段对应实体属性值集合 **/
	private Map<String, String> columnValueMap;

	public DatabaseMeta(String database, String tableName) {
		this.database = database;
		this.tableName = tableName;
	}
}
