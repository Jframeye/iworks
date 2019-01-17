/**
 * 
 */
package com.xiaoye.iworks.persistent.provider;

import com.xiaoye.iworks.utils.DateTimeUtils;
import com.xiaoye.iworks.utils.StringUtils;
import com.xiaoye.iworks.persistent.entity.Condition;
import com.xiaoye.iworks.persistent.entity.Condition.Criteria;
import com.xiaoye.iworks.persistent.entity.Entity;
import com.xiaoye.iworks.persistent.exception.DatabaseException;
import com.xiaoye.iworks.persistent.support.DatabaseConvertHandler;
import com.xiaoye.iworks.persistent.support.DatabaseMeta;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通用脚本提供类,适用于单表操作
 * @author yehl
 * @date 2018年6月23日
 */
public class SQLProvider<E extends Entity, C extends Condition> {
	public final Log LOGGER = LogFactory.getLog(getClass());

	/**
	 * 通用查询脚本
	 * @param c
	 * @return
	 */
	public String selectByCriteria(C c) {
		if(!Condition.class.isAssignableFrom(c.getClass())) {
			throw new DatabaseException("参数类型异常.参数必须是继承com.xiaoye.iworks.persistent.entity.Condition");
		}
		SQL sql = new SQL();
		DatabaseMeta meta = DatabaseConvertHandler.convert(c.geEntityClass());
		if(c.isDistinct()) {
			sql.SELECT_DISTINCT("*");
		} else {
			sql.SELECT("*");
		}
		sql.FROM(convert(meta.getDatabase(), meta.getTableName(), c.getDbIndex(), c.getTbIndex()));
		c.getCriterias().stream().parallel().forEach((criteria) -> {
			where(sql, criteria);
			c.setCriteriaSize(c.getCriteriaSize() - 1);
			if(c.getCriteriaSize() > 0) {
				sql.OR();
			}
		});
		if(StringUtils.isNotBlank(c.getOrderByClause())) {
			sql.ORDER_BY(c.getOrderByClause());
		}
		String sqlStr = sql.toString().replaceAll("OR ()", "");
		if (!c.isPagination()) {
			LOGGER.debug(sqlStr);
			return sqlStr;
		}
		StringBuffer buffer = new StringBuffer(sqlStr);
		buffer.append(" LIMIT ").append(c.getOffset()).append(", ").append(c.getLimit());
		sqlStr = buffer.toString();
		LOGGER.debug(sqlStr);
		return sqlStr;
	}
	
	/**
	 * 通用统计脚本
	 * @param c
	 * @return
	 */
	public String countByCriteria(C c) {
		if(!Condition.class.isAssignableFrom(c.getClass())) {
			throw new DatabaseException("参数类型异常.参数必须是继承com.xiaoye.iworks.persistent.entity.Condition");
		}
		SQL sql = new SQL();
		DatabaseMeta meta = DatabaseConvertHandler.convert(c.geEntityClass());
		sql.SELECT("COUNT(1)").FROM(convert(meta.getDatabase(), meta.getTableName(), c.getDbIndex(), c.getTbIndex()));
		c.getCriterias().stream().parallel().forEach((criteria) -> {
			where(sql, criteria);
			c.setCriteriaSize(c.getCriteriaSize() - 1);
			if(c.getCriteriaSize() > 0) {
				sql.OR();
			}
		});
		String sqlStr = sql.toString().replace("OR ()", "");
		LOGGER.debug(sqlStr);
		return sqlStr;
	}
	
	private void where(SQL sql, Criteria criteria) {
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
		String sqlStr = sql.toString();
		LOGGER.debug(sqlStr);
		return sqlStr;
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
		String sqlStr = sql.toString();
		LOGGER.debug(sqlStr);
		return sqlStr;
	}
	
	/**
	 * 通用更新脚本(所有字段都更新)
	 * @param e
	 * @return
	 */
	public String update(E e) {
		return updateSQL(e, false);
	}

	/**
	 * 通用更新脚本(字段值为空的不处理)(需要存在主键)
	 * @param e
	 * @return
	 */
	public String updateSelective(E e) {
		return updateSQL(e,true);
	}

	private String updateSQL(E e, boolean selective) {
		SQL sql = new SQL();
		DatabaseMeta meta = DatabaseConvertHandler.convert(e);
		String primaryKey = meta.getPrimaryKey();
		if(StringUtils.isBlank(primaryKey)) {
			throw new DatabaseException("there has no primary key.");
		}
		sql.UPDATE(convert(meta.getDatabase(), meta.getTableName(), e.getDbIndex(), e.getTbIndex()));
		Map<String, String> columnFieldMap = meta.getColumnFieldMap();
		Map<String, String> columnValueMap = meta.getColumnValueMap();
		if(columnValueMap.get(meta.getPrimaryKey()) == null) {
			throw new DatabaseException("the primary key has no value");
		}
		if(selective) {
			for (String column : columnValueMap.keySet()) {
				if(!primaryKey.equalsIgnoreCase(column)) {
					sql.SET(String.format("%s = #{%s}", column, columnFieldMap.get(column)));
				}
			}
		} else {
			for (String column : columnFieldMap.keySet()) {
				if(!primaryKey.equalsIgnoreCase(column)) {
					sql.SET(String.format("%s = #{%s}", column, columnFieldMap.get(column)));
				}
			}
		}
		sql.WHERE(String.format("%s = #{%s}", primaryKey, columnFieldMap.get(primaryKey)));
		String sqlStr = sql.toString();
		LOGGER.debug(sqlStr);
		return sqlStr;
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
		if(entity == null || !Entity.class.isAssignableFrom(entity.getClass())) {
			throw new DatabaseException("参数类型异常.参数1必须使用@Param(\"entity\")标记且继承com.xiaoye.iworks.persistent.entity.Entity");
		}
		if(criterias == null || !Condition.class.isAssignableFrom(criterias.getClass())) {
			throw new DatabaseException("参数类型异常.参数2必须使用@Param(\"criteria\")标记且继承com.xiaoye.iworks.persistent.entity.Condition");
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
		c.getCriterias().stream().parallel().forEach((criteria) -> {
			where(sql, criteria);
			sql.OR();
		});
		String sqlStr = sql.toString().replace("OR ()", "");
		LOGGER.debug(sqlStr);
		return sqlStr;
	}
	
	/**
	 * 通用删除脚本
	 * @param c
	 * @return
	 */
	public String deleteByCriteria(C c) {
		if(!Condition.class.isAssignableFrom(c.getClass())) {
			throw new DatabaseException("参数类型异常.参数必须是继承com.xiaoye.iworks.persistent.entity.Condition");
		}
		SQL sql = new SQL();
		DatabaseMeta meta = DatabaseConvertHandler.convert(c.geEntityClass());
		sql.DELETE_FROM(convert(meta.getDatabase(), meta.getTableName(), c.getDbIndex(), c.getTbIndex()));
		c.getCriterias().stream().parallel().forEach((criteria) -> {
			where(sql, criteria);
			c.setCriteriaSize(c.getCriteriaSize() - 1);
			if(c.getCriteriaSize() > 0) {
				sql.OR();
			}
		});
		String sqlStr = sql.toString().replace("OR ()", "");
		LOGGER.debug(sqlStr);
		return sqlStr;
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
	private String convert(String database, String tableName, String dbIndex, String tbIndex) {
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
