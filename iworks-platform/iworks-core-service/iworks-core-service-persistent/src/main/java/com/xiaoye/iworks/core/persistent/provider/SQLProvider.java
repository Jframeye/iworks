/**
 * 
 */
package com.xiaoye.iworks.core.persistent.provider;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xiaoye.iworks.core.persistent.entity.Database;
import com.xiaoye.iworks.core.persistent.entity.DatabaseCriteria;
import com.xiaoye.iworks.core.persistent.support.DatabaseConvertHandler;
import com.xiaoye.iworks.core.persistent.support.DatabaseMeta;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.DateTimeUtils;
import com.xiaoye.iworks.utils.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * 通用脚本提供类,适用于单表操作
 * @author yehl
 * @date 2018年6月23日
 */
public class SQLProvider<E extends Database, C extends DatabaseCriteria> {

	/**
	 * 通用查询脚本
	 * @param c
	 * @return
	 */
	public String selectByCriteria(C c) {
		if(!DatabaseCriteria.class.isAssignableFrom(c.getClass())) {
			throw new RuntimeException("参数类型异常.参数必须是继承com.xiaoye.iworks.core.persistent.entity.DatabaseCriteria");
		}
		SQL sql = new SQL();
		DatabaseMeta meta = DatabaseConvertHandler.convert(c.geEntityClass());
		if(c.isDistinct()) {
			sql.SELECT_DISTINCT("*");
		} else {
			sql.SELECT("*");
		}
		sql.FROM(convert(meta.getDatabase(), meta.getTableName(), c.getDbIndex(), c.getTbIndex()));
		conditionalAssembly(c, sql);
		if(StringUtils.isNotBlank(c.getOrderByClause())) {
			sql.ORDER_BY(c.getOrderByClause());
		}
		if (!c.isPagination()) {
			return sql.toString();
		}
		StringBuffer buffer = new StringBuffer(sql.toString());
		buffer.append(" limit ").append(c.getOffset()).append(", ").append(c.getLimit());
		System.out.println(sql.toString());
		return sql.toString();
	}
	
	/**
	 * 通用统计脚本
	 * @param c
	 * @return
	 */
	public String countByCriteria(C c) {
		if(!DatabaseCriteria.class.isAssignableFrom(c.getClass())) {
			throw new RuntimeException("参数类型异常.参数必须是继承com.xiaoye.iworks.core.persistent.entity.DatabaseCriteria");
		}
		SQL sql = new SQL();
		DatabaseMeta meta = DatabaseConvertHandler.convert(c.geEntityClass());
		sql.SELECT("COUNT(1)").FROM(convert(meta.getDatabase(), meta.getTableName(), c.getDbIndex(), c.getTbIndex()));
		conditionalAssembly(c, sql);
		return sql.toString().replace("OR ()", "");
	}
	
	/** 
	 * 组装条件查询语句
	 * @param 
	 * @return 
	 * @author yehl
	 * @date 2018/9/28 9:34
	 */
	private void where(SQL sql, DatabaseCriteria.Criteria criteria) {
		if(criteria.isValid()) {
			StringBuffer buffer = new StringBuffer();
			criteria.getCriterions().stream().forEach(criterion -> {
				buffer.append(" AND ");
				if(criterion.isNoValue()) {
					buffer.append(criterion.getCondition());
				}
				if(criterion.isSingleValue()) {
					buffer.append(String.format("%s %s", criterion.getCondition(), convertStrValue(criterion.getValue())));
				}
				if(criterion.isBetweenValue()) {
					buffer.append(String.format("%s %s AND %s", criterion.getCondition(), convertStrValue(criterion.getValue()), convertStrValue(criterion.getSecondValue())));
				}
				if(criterion.isListValue()) {
					List<?> list = (List<?>) criterion.getValue();
					StringBuffer listBuffer = new StringBuffer();
					list.stream().forEach(value -> {
						listBuffer.append(",").append(String.format("%s", convertStrValue(value)));
					});
					buffer.append(criterion.getCondition()).append(String.format(" (%s)", listBuffer.substring(1)));					
				}
			});
			sql.WHERE(buffer.substring(5));
		}
	}
	
	/**
	 * 通用插入脚本(所有字段都插入)
	 * @param e
	 * @return
	 */
	public String insert(E e) {
		SQL sql = new SQL();
		DatabaseMeta meta = DatabaseConvertHandler.convert(e);
		String primaryKey = meta.getPrimaryKey();
		sql.INSERT_INTO(convert(meta.getDatabase(), meta.getTableName(), e.getDbIndex(), e.getTbIndex()));
		Map<String, String> columnFieldMap = meta.getColumnFieldMap();
		for (String column : columnFieldMap.keySet()) {
			if(!column.equals(primaryKey)) {
				sql.VALUES(column, String.format("#{%s}", columnFieldMap.get(column)));
			}
		}
		return sql.toString();
	}
	
	/**
	 * 通用插入脚本(字段值为空的不处理)
	 * @param e
	 * @return
	 */
	public String insertSelective(E e) {
		SQL sql = new SQL();
		DatabaseMeta meta = DatabaseConvertHandler.convert(e);
		sql.INSERT_INTO(convert(meta.getDatabase(), meta.getTableName(), e.getDbIndex(), e.getTbIndex()));
		Map<String, String> columnValueMap = meta.getColumnValueMap();
		Map<String, String> columnFieldMap = meta.getColumnFieldMap();
		for (String column : columnValueMap.keySet()) {
			sql.VALUES(column, String.format("#{%s}", columnFieldMap.get(column)));
		}
		return sql.toString();
	}
	
	/**
	 * 通用更新脚本(所有字段都更新)
	 * @param e
	 * @return
	 */
	public String update(E e) {
		SQL sql = new SQL();
		DatabaseMeta meta = DatabaseConvertHandler.convert(e);
		String primaryKey = meta.getPrimaryKey();
		if(StringUtils.isBlank(primaryKey)) {
			throw new RuntimeException("there has no primary key.");
		}
		sql.UPDATE(convert(meta.getDatabase(), meta.getTableName(), e.getDbIndex(), e.getTbIndex()));
		Map<String, String> columnFieldMap = meta.getColumnFieldMap();
		Map<String, String> columnValueMap = meta.getColumnValueMap();
		if(columnValueMap.get(meta.getPrimaryKey()) == null) {
			throw new RuntimeException("the primary key has no value");
		}
		for (String column : columnFieldMap.keySet()) {
			if(!primaryKey.equalsIgnoreCase(column)) {
				sql.SET(String.format("%s = #{%s}", column, columnFieldMap.get(column)));
			}
		}
		sql.WHERE(String.format("%s = #{%s}", primaryKey, columnFieldMap.get(primaryKey)));
		return sql.toString();
	}

	/**
	 * 通用更新脚本(字段值为空的不处理)(需要存在主键)
	 * @param e
	 * @return
	 */
	public String updateSelective(E e) {
		SQL sql = new SQL();
		DatabaseMeta meta = DatabaseConvertHandler.convert(e);
		String primaryKey = meta.getPrimaryKey();
		if(StringUtils.isBlank(primaryKey)) {
			throw new RuntimeException("there has no primary key.");
		}
		sql.UPDATE(convert(meta.getDatabase(), meta.getTableName(), e.getDbIndex(), e.getTbIndex()));
		Map<String, String> columnFieldMap = meta.getColumnFieldMap();
		Map<String, String> columnValueMap = meta.getColumnValueMap();
		if(columnValueMap.get(meta.getPrimaryKey()) == null) {
			throw new RuntimeException("the primary key has no value");
		}
		for (String column : columnValueMap.keySet()) {
			if(!primaryKey.equalsIgnoreCase(column)) {
				sql.SET(String.format("%s = #{%s}", column, columnFieldMap.get(column)));
			}
		}
		sql.WHERE(String.format("%s = #{%s}", primaryKey, columnFieldMap.get(primaryKey)));
		return sql.toString();
	}

	/**
	 * 通用条件更新脚本
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateByCriteria(Map<String, Object> parameter) {
		Object entity = parameter.get("entity");
		Object criterias = parameter.get("criteria");
		if(entity == null || !Database.class.isAssignableFrom(entity.getClass())) {
			throw new RuntimeException("参数类型异常.参数1必须使用@Param(\"entity\")标记且继承com.xiaoye.iworks.core.persistent.entity.Database");
		}
		if(criterias == null || !DatabaseCriteria.class.isAssignableFrom(criterias.getClass())) {
			throw new RuntimeException("参数类型异常.参数2必须使用@Param(\"criteria\")标记且继承com.xiaoye.iworks.core.persistent.entity.DatabaseCriteria");
		}
		E e = (E) entity;
		C c = (C) criterias;
		SQL sql = new SQL();
		DatabaseMeta meta = DatabaseConvertHandler.convert(e);
		sql.UPDATE(convert(meta.getDatabase(), meta.getTableName(), e.getDbIndex(), e.getTbIndex()));
		Map<String, String> columnFieldMap = meta.getColumnFieldMap();
		Map<String, String> columnValueMap = meta.getColumnValueMap();
		for (String column : columnValueMap.keySet()) {
			sql.SET(String.format("%s = #{%s}", column, columnFieldMap.get(column)));
		}
		conditionalAssembly(c, sql);
		return sql.toString().replace("OR ()", "");
	}
	
	/**
	 * 通用删除脚本
	 * @param c
	 * @return
	 */
	public String deleteByCriteria(C c) {
		if(!DatabaseCriteria.class.isAssignableFrom(c.getClass())) {
			throw new RuntimeException("参数类型异常.参数必须是继承com.xiaoye.iworks.core.persistent.entity.DatabaseCriteria");
		}
		SQL sql = new SQL();
		DatabaseMeta meta = DatabaseConvertHandler.convert(c.geEntityClass());
		sql.DELETE_FROM(convert(meta.getDatabase(), meta.getTableName(), c.getDbIndex(), c.getTbIndex()));
		conditionalAssembly(c, sql);
		return sql.toString().replace("OR ()", "");
	}

	/** 
	 * 组装条件查询语句
	 * @param 
	 * @return 
	 * @author yehl
	 * @date 2018/9/28 9:30
	 */
	private void conditionalAssembly(C c, SQL sql) {
		sql.WHERE("lstate = 1");
		if(CollectionUtils.isNotEmpty(c.getCriterias())) {
			int size = c.getCriterias().size();
			int index = 0;
			for (DatabaseCriteria.Criteria criteria: c.getCriterias()) {
				where(sql, criteria);
				index++;
				if(index < size) {
					sql.OR();
				}
			}
		}
	}

	private String convertStrValue(Object value) {
		if(value instanceof String) {
			return String.format("'%s'", value);
		} else if(value instanceof Date) {
			return String.format("'%s'", DateTimeUtils.formatDateToString((Date) value));
		} else {
			return String.format("%s", value);
		}
	}

	/**
	 * 查询库表
	 * @param database 数据库名称
	 * @param tableName 数据库表名称
	 * @param dbIndex 数据库下标
	 * @param tbIndex 数据库表下标
	 * @return
	 */
	private String convert(String database, String tableName, Integer dbIndex, Integer tbIndex) {
		StringBuffer buffer = new StringBuffer();
		if(database != null && database.trim().length() > 0) {
			buffer.append(database);
		}
		if(dbIndex != null) {
			buffer.append("_" + dbIndex);
		}
		if(buffer.length() > 0) {
			buffer.insert(0, "`").append("`");
		}
		if(buffer.length() != 0) {
			buffer.append(".");
		}
		buffer.append("`").append(tableName);
		if(tbIndex != null) {
			buffer.append("_" + tbIndex);
		}
		buffer.append("`");
		return buffer.toString();
	}
}
