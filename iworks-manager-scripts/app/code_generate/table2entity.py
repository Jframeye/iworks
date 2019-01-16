# -*- coding: utf-8 -*-
import os, time

from common.str_utils import convert
from app.code_generate.entity.database_table import DB_JAVA_PROPERTY_TYPE_MAP

'''
package com.xiaoye.iworks.basic.persistent.entity;

import com.xiaoye.iworks.persistent.annotation.Column;
import com.xiaoye.iworks.persistent.annotation.Key;
import com.xiaoye.iworks.persistent.annotation.Table;
import com.xiaoye.iworks.persistent.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
/**
 * 功能描述: 
 * @auther: auto create by python
 * @date: 2019/1/9 12:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_app_constant", database = "basic")
public class AppConstantDO extends Entity {
	private static final long serialVersionUID = -8223026017174272388L;

	@Key(name = "pkid", comments = "自增主键")
	private Long pkid;
	@Column(name = "constant_key", comments = "常量编码")
    private String constantKey;
	@Column(name = "constant_value", comments = "常量值")
    private String constantValue;
	@Column(name = "constant_desc", comments = "常量描述")
    private String constantDesc;
	@Column(name = "state", comments = "状态：1-可用；2-禁用")
	private Integer state;
	@Column(name = "lstate", comments = "数据逻辑状态：1-正常；2-删除")
	private Integer lstate;
	@Column(name = "create_time", comments = "创建时间")
	private Date createTime;
	@Column(name = "create_by", comments = "创建人")
	private String createBy;
	@Column(name = "modify_time", comments = "最后修改时间")
	private Date modifyTime;
	@Column(name = "modify_by", comments = "最后修改人")
	private String modifyBy;
	@Column(name = "sign", comments = "数据签名")
	private String sign;
}
'''


def generate_entity(table_list, package_name, file_out_put_path):
    if table_list is None or len(table_list) == 0:
        print('table list is empty, no table could be processed')
        return

    package_path = package_name.replace('.', '/')
    file_out_put_path = '%s/%s/persistent/entity' % (file_out_put_path, package_path)
    table_size = 0
    for (_, db_table) in table_list.items():
        _generate_entity4table(db_table, package_name, file_out_put_path)
        _generate_criteria4table(db_table, package_name, file_out_put_path)
        table_size += 1

    print('generate %d table entity to %s' % (table_size, os.path.abspath(file_out_put_path)))
    print('generate %d table criteria to %s' % (table_size, os.path.abspath(file_out_put_path)))


def _generate_entity4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path)
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = entity_name + 'DO.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo
    table_columns = db_table.tablecolumns

    # package
    file.write('package %s.persistent.entity;\r\n' % package_name)
    # import
    file.write('import com.xiaoye.iworks.persistent.annotation.Column;\n')
    file.write('import com.xiaoye.iworks.persistent.annotation.Key;\n')
    file.write('import com.xiaoye.iworks.persistent.annotation.Table;\n')
    file.write('import lombok.Data;\n')
    file.write('import lombok.EqualsAndHashCode;\n')
    if table_info.hasdecimal:
        file.write('import java.math.BigDecimal;\n')
    if table_info.hasdate:
        file.write('import java.util.Date;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: 实体类【%s】\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('@Data\n')
    file.write('@EqualsAndHashCode(callSuper = false)\n')
    file.write('@Table(name = "%s", database = "%s")\n' % (table_info.tablename, table_info.dbname))
    file.write('public class %sDO extends Entity {\n' % entity_name)
    file.write('	private static final long serialVersionUID = 1L;\r\n')
    for table_column in table_columns:
        property_type = DB_JAVA_PROPERTY_TYPE_MAP[table_column.datatype]
        if table_column.primary:
            file.write('	@Key(name = "%s", comments = "%s")\n' % (table_column.columnname, table_column.desc))
        else:
            file.write('	@Column(name = "%s", comments = "%s")\n' % (table_column.columnname, table_column.desc))
        file.write('    private %s %s;\n' % (property_type, convert(table_column.columnname, '_')))
    file.write('}')
    file.close()


def _generate_criteria4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path)
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = entity_name + 'Criteria.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo
    table_columns = db_table.tablecolumns

    # package
    file.write('package %s.persistent.entity;\r\n' % package_name)
    # import
    file.write('import com.google.common.collect.Lists;\n')
    file.write('import com.xiaoye.iworks.persistent.entity.Condition;\n')
    file.write('import com.xiaoye.iworks.persistent.entity.Entity;\n')
    file.write('import com.xiaoye.iworks.utils.CollectionUtils;\n')
    file.write('import com.xiaoye.iworks.utils.StringUtils;\n')
    if table_info.hasdecimal:
        file.write('import java.math.BigDecimal;\n')
    if table_info.hasdate:
        file.write('import java.util.Date;\n')
    file.write('import java.util.List;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: 实体类查询条件【%s】\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('public class %sCriteria extends Condition {\r\n' % entity_name)
    file.write('    @Override\n')
    file.write('    public Class<? extends Entity> geEntityClass() {\n')
    file.write('        return %sDO.class;\n' % entity_name)
    file.write('    }\r\n')

    file.write('    public %sCriteria() {\n' % entity_name)
    file.write('        criterias = Lists.newArrayList();\n')
    file.write('    }\r\n')

    file.write('    public void or(GeneratedCriteria GeneratedCriteria) {\n')
    file.write('        criterias.add(GeneratedCriteria);\n')
    file.write('    }\r\n')

    file.write('    public GeneratedCriteria or() {\n')
    file.write('        GeneratedCriteria generatedCriteria = createCriteriaInternal();\n')
    file.write('        criterias.add(generatedCriteria);\n')
    file.write('        return generatedCriteria;\n')
    file.write('    }\r\n')

    file.write('    public GeneratedCriteria createCriteriaInternal() {\n')
    file.write('        GeneratedCriteria generatedCriteria = new GeneratedCriteria();\n')
    file.write('        if (criterias.size() == 0) {\n')
    file.write('            criterias.add(generatedCriteria);\n')
    file.write('        }\n')
    file.write('        return generatedCriteria;\n')
    file.write('    }\r\n')

    file.write('    public class GeneratedCriteria extends Criteria {\r\n')
    file.write('        protected GeneratedCriteria() {\n')
    file.write('            super();\n')
    file.write('            criterions = Lists.newArrayList();\n')
    file.write('        }\r\n')

    for table_column in table_columns:
        java_property = DB_JAVA_PROPERTY_TYPE_MAP[table_column.datatype]
        java_name = convert(table_column.columnname, '_')
        java_name_upper = convert(table_column.columnname, '_', True)
        file.write('        public GeneratedCriteria and%sIsNull() {\n' % java_name_upper)
        file.write('            addCriterion("`%s` is null");\n' % table_column.columnname)
        file.write('            return this;\n')
        file.write('        }\r\n')

        file.write('        public GeneratedCriteria and%sIsNotNull() {\n' % java_name_upper)
        file.write('            addCriterion("`%s` is not null");\n' % table_column.columnname)
        file.write('            return this;\n')
        file.write('        }\r\n')

        if java_property != 'String':
            file.write('        public GeneratedCriteria and%sEqualTo(%s value) {\n' % (java_name_upper, java_property))
            file.write('            if(value == null) return this;\n')
            file.write('            addCriterion("`%s` =", value, "%s");\n' % (table_column.columnname, java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')

            file.write(
                '        public GeneratedCriteria and%sNotEqualTo(%s value) {\n' % (java_name_upper, java_property))
            file.write('            if(value == null) return this;\n')
            file.write('            addCriterion("`%s` <>", value, "%s");\n' % (table_column.columnname, java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')
        else:
            file.write('        public GeneratedCriteria and%sEqualTo(%s value) {\n' % (java_name_upper, java_property))
            file.write('            if(StringUtils.isBlank(value)) return this;\n')
            file.write('            addCriterion("`%s` =", value, "%s");\n' % (table_column.columnname, java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')

            file.write(
                '        public GeneratedCriteria and%sNotEqualTo(%s value) {\n' % (java_name_upper, java_property))
            file.write('            if(StringUtils.isBlank(value)) return this;\n')
            file.write('            addCriterion("`%s` <>", value, "%s");\n' % (table_column.columnname, java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')

        if java_property != 'String':
            file.write(
                '        public GeneratedCriteria and%sGreaterThan(%s value) {\n' % (java_name_upper, java_property))
            file.write('            if(value == null) return this;\n')
            file.write('            addCriterion("`%s` >", value, "%s");\n' % (table_column.columnname, java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')

            file.write('        public GeneratedCriteria and%sGreaterThanOrEqualTo(%s value) {\n' % (
            java_name_upper, java_property))
            file.write('            if(value == null) return this;\n')
            file.write('            addCriterion("`%s` >=", value, "%s");\n' % (table_column.columnname, java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')

            file.write(
                '        public GeneratedCriteria and%sLessThan(%s value) {\n' % (java_name_upper, java_property))
            file.write('            if(value == null) return this;\n')
            file.write('            addCriterion("`%s` <", value, "%s");\n' % (table_column.columnname, java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')

            file.write('        public GeneratedCriteria and%sLessThanOrEqualTo(%s value) {\n' % (
            java_name_upper, java_property))
            file.write('            if(value == null) return this;\n')
            file.write('            addCriterion("`%s` <=", value, "%s");\n' % (table_column.columnname, java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')
        else:
            file.write('        public GeneratedCriteria and%sLike(%s value) {\n' % (java_name_upper, java_property))
            file.write('            if(StringUtils.isBlank(value)) return this;\n')
            file.write('            addCriterion("`%s` like", %s, "%s");\n' % (
            table_column.columnname, 'String.format("%s%s%s", "%", value, "%")', java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')

            file.write('        public GeneratedCriteria and%sNotLike(%s value) {\n' % (java_name_upper, java_property))
            file.write('            if(StringUtils.isBlank(value)) return this;\n')
            file.write('            addCriterion("`%s` not like", %s, "%s");\n' % (
            table_column.columnname, 'String.format("%s%s%s", "%", value, "%")', java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')

        file.write('        public GeneratedCriteria and%sIn(List<%s> values) {\n' % (java_name_upper, java_property))
        file.write('            if(CollectionUtils.isEmpty(values)) return this;\n')
        file.write('            addCriterion("`%s` in", values, "%s");\n' % (table_column.columnname, java_name))
        file.write('            return this;\n')
        file.write('        }\r\n')

        file.write(
            '        public GeneratedCriteria and%sNotIn(List<%s> values) {\n' % (java_name_upper, java_property))
        file.write('            if(CollectionUtils.isEmpty(values)) return this;\n')
        file.write('            addCriterion("`%s` not in", values, "%s");\n' % (table_column.columnname, java_name))
        file.write('            return this;\n')
        file.write('        }\r\n')

        if java_property != 'String':
            file.write('        public GeneratedCriteria and%sBetween(%s value1, %s value2) {\n' % (
            java_name_upper, java_property, java_property))
            file.write('            if(value1 == null || value2 == null) return this;\n')
            file.write('            addCriterion("`%s` between", value1, value2, "%s");\n' % (
            table_column.columnname, java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')

            file.write('        public GeneratedCriteria and%sNotBetween(%s value1, %s value2) {\n' % (
            java_name_upper, java_property, java_property))
            file.write('            if(value1 == null || value2 == null) return this;\n')
            file.write('            addCriterion("`%s` not between", value1, value2, "%s");\n' % (
            table_column.columnname, java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')
        else:
            file.write('        public GeneratedCriteria and%sBetween(%s value1, %s value2) {\n' % (
            java_name_upper, java_property, java_property))
            file.write('            if(StringUtils.hasBlankString(value1, value2)) return this;\n')
            file.write('            addCriterion("`%s` between", value1, value2, "%s");\n' % (
            table_column.columnname, java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')

            file.write('        public GeneratedCriteria and%sNotBetween(%s value1, %s value2) {\n' % (
            java_name_upper, java_property, java_property))
            file.write('            if(StringUtils.hasBlankString(value1, value2)) return this;\n')
            file.write('            addCriterion("`%s` not between", value1, value2, "%s");\n' % (
            table_column.columnname, java_name))
            file.write('            return this;\n')
            file.write('        }\r\n')

    file.write('    }\n')
    file.write('}')
    file.close()
