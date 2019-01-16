/**
 * 
 */
package com.xiaoye.iworks.persistent.support;

import java.util.Map;

/**
 * 数据实体类元信息
 * @author yehl
 * @date 2018年6月23日
 */
public class DatabaseMeta {
	
	/** 数据库名称 **/
	private String database;

	/** 实体对应的表名称 **/
	private String tableName;
	
	/** 数据库主键(单个) **/
	private String primaryKey;

	/** 数据库字段对应实体属性名集合 **/
	private Map<String, String> columnFieldMap;

	/** 数据库字段对应实体属性值集合 **/
	private Map<String, String> columnValueMap;

	public DatabaseMeta(String database, String tableName) {
		this.database = database;
		this.tableName = tableName;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Map<String, String> getColumnFieldMap() {
		return columnFieldMap;
	}

	public void setColumnFieldMap(Map<String, String> columnFieldMap) {
		this.columnFieldMap = columnFieldMap;
	}

	public Map<String, String> getColumnValueMap() {
		return columnValueMap;
	}

	public void setColumnValueMap(Map<String, String> columnValueMap) {
		this.columnValueMap = columnValueMap;
	}
}
