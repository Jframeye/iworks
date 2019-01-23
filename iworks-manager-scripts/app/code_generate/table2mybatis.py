# -*- coding: utf-8 -*-
import os, time

from common.str_utils import convert
from app.code_generate.entity.database_table import DB_COLUMN_DATA_TYPE_MAP, get_key

'''
package com.xiaoye.iworks.basic.persistent.mapper;

import com.xiaoye.iworks.basic.persistent.entity.AppConstantDO;
import com.xiaoye.iworks.basic.persistent.entity.AppConstantCriteria;
import com.xiaoye.iworks.persistent.provider.SQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yehl
 * @date 2018年6月28日 下午7:51:30
 */
@Mapper
public interface AppConstantMapper {

    @InsertProvider(method = "insert", type = SQLProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "pkid", keyColumn = "pkid")
    public int insert(AppConstantDO entity);

    @InsertProvider(method = "insertSelective", type = SQLProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "pkid", keyColumn = "pkid")
    public int insertSelective(AppConstantDO entity);

    @UpdateProvider(method = "update", type = SQLProvider.class)
    public int update(AppConstantDO entity);

    @UpdateProvider(method = "updateSelective", type = SQLProvider.class)
    public int updateSelective(AppConstantDO entity);

    @DeleteProvider(method = "deleteByCriteria", type = SQLProvider.class)
    public int delete(AppConstantCriteria criteria);

    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public AppConstantDO selectForOne(AppConstantCriteria criteria);

    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public List<AppConstantDO> selectForList(AppConstantCriteria criteria);

    @SelectProvider(method = "countByCriteria", type = SQLProvider.class)
    public int count(AppConstantCriteria criteria);
}
'''


def generate_mapper(table_list, package_name, file_out_put_path):
    if table_list is None or len(table_list) == 0:
        print('table list is empty, no table could be processed')
        return

    package_path = package_name.replace('.', '/')
    mapper_out_put_path = '%s/%s/persistent/mapper' % (file_out_put_path, package_path)
    mapping_out_put_path = '%s/%s/persistent/mapping' % (file_out_put_path, package_path)
    table_size = 0
    for (_, db_table) in table_list.items():
        _generate_mapper4table(db_table, package_name, mapper_out_put_path)
        _generate_mapping4table(db_table, package_name, mapping_out_put_path)
        table_size += 1

    print('generate %d mybatis to %s' % (table_size, os.path.abspath(mapper_out_put_path)))


def _generate_mapper4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path)
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = entity_name + 'Mapper.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo
    table_columns = db_table.tablecolumns

    # package
    file.write('package %s.persistent.mapper;\r\n' % package_name)
    # import
    file.write('import %s.persistent.entity.%sDO;\n' % (package_name, entity_name))
    file.write('import %s.persistent.entity.%sCriteria;\n' % (package_name, entity_name))
    file.write('import com.xiaoye.iworks.persistent.provider.SQLProvider;\n')
    file.write('import org.apache.ibatis.annotations.*;\n')
    file.write('import java.util.List;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: 实体类数据库接口【%s】\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('@Mapper\n')
    file.write('public interface %sMapper {\r\n' % entity_name)

    file.write('    @InsertProvider(method = "insert", type = SQLProvider.class)\n')
    if len(table_info.primarykeys) == 1:
        file.write('    @Options(useGeneratedKeys = true, keyProperty = "%s", keyColumn = "%s")\n' % (
            convert(table_info.primarykeys[0], '_'), table_info.primarykeys[0]))
    file.write('    public int insert(%sDO entity);\r\n' % entity_name)

    file.write('    @InsertProvider(method = "insertSelective", type = SQLProvider.class)\n')
    if len(table_info.primarykeys) == 1:
        file.write('    @Options(useGeneratedKeys = true, keyProperty = "%s", keyColumn = "%s")\n' % (
            convert(table_info.primarykeys[0], '_'), table_info.primarykeys[0]))
    file.write('    public int insertSelective(%sDO entity);\r\n' % entity_name)

    file.write('    @UpdateProvider(method = "update", type = SQLProvider.class)\n')
    file.write('    public int update(%sDO entity);\r\n' % entity_name)

    file.write('    @UpdateProvider(method = "updateSelective", type = SQLProvider.class)\n')
    file.write('    public int updateSelective(%sDO entity);\r\n' % entity_name)

    file.write('    @DeleteProvider(method = "deleteByCriteria", type = SQLProvider.class)\n')
    file.write('    public int delete(%sCriteria criteria);\r\n' % entity_name)

    file.write('    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)\n')
    file.write('    @ResultMap("BasicResultMap")\n')
    file.write('    public %sDO selectForOne(%sCriteria criteria);\r\n' % (entity_name, entity_name))

    file.write('    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)\n')
    file.write('    @ResultMap("BasicResultMap")\n')
    file.write('    public List<%sDO> selectForList(%sCriteria criteria);\r\n' % (entity_name, entity_name))

    file.write('    @SelectProvider(method = "countByCriteria", type = SQLProvider.class)\n')
    file.write('    @ResultMap("BasicResultMap")\n')
    file.write('    public int count(%sCriteria criteria);\n' % entity_name)

    file.write('}')
    file.close()


def _generate_mapping4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path)
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = entity_name + 'Mapping.xml'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo
    table_columns = db_table.tablecolumns

    file.write('<?xml version="1.0" encoding="UTF-8"?>\r\n')
    # namespace
    file.write(
        '<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">\n')
    file.write('<mapper namespace="%s.persistent.mapper.%sMapper">\r\n' % (package_name, entity_name))
    file.write('	<resultMap id="BasicResultMap" type="%s.persistent.entity.%sDO">\n' % (package_name, entity_name))

    for table_column in table_columns:
        data_type = get_key(DB_COLUMN_DATA_TYPE_MAP, table_column.datatype)
        if data_type == 'datetime':
            data_type = 'TIMESTAMP'
        java_name = convert(table_column.columnname, '_')
        if table_column.primary:
            file.write('	    <id column="%s" jdbcType="%s" property="%s" />\n' % (
                table_column.columnname, data_type.upper(), java_name))
        else:
            file.write('	    <result column="%s" jdbcType="%s" property="%s" />\n' % (
                table_column.columnname, data_type.upper(), java_name))

    file.write('	</resultMap>\r\n')

    file.write('	<sql id="Basic_Where_Clause">\n')
    file.write('		<where>\n')
    file.write('			<foreach collection="criterias" item="item" separator="or">\n')
    file.write('				<if test="item.valid">\n')
    file.write('					<trim prefix="(" prefixOverrides="and" suffix=")">\n')
    file.write('						<foreach collection="item.criterions" item="criterion">\n')
    file.write('							<choose>\n')
    file.write('								<when test="criterion.noValue">\n')
    file.write('									and ${criterion.condition}\n')
    file.write('								</when>\n')
    file.write('								<when test="criterion.singleValue">\n')
    file.write('									and ${criterion.condition} #{criterion.value}\n')
    file.write('								</when>\n')
    file.write('								<when test="criterion.betweenValue">\n')
    file.write(
        '									and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}\n')
    file.write('								</when>\n')
    file.write('								<when test="criterion.listValue">\n')
    file.write('									and ${criterion.condition}\n')
    file.write(
        '									<foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">\n')
    file.write('										#{listItem}\n')
    file.write('									</foreach>\n')
    file.write('								</when>\n')
    file.write('							</choose>\n')
    file.write('						</foreach>\n')
    file.write('					</trim>\n')
    file.write('				</if>\n')
    file.write('			</foreach>\n')
    file.write('		</where>\n')
    file.write('	</sql>\r\n')

    file.write('	<sql id="Basic_Columns">\n')
    file.write('		')
    length = len(table_columns)
    i = 1
    for table_column in table_columns:
        if i < length:
            file.write('`%s`,' % table_column.columnname)
        else:
            file.write('`%s`' % table_column.columnname)
        i += 1
    file.write('\n	</sql>\r\n')

    file.write('	<sql id="Database_Table_Choose">\n')
    file.write('		<choose>\n')
    file.write('			<when test="dbIndex == null and tbIndex == null">\n')
    file.write('				`%s`.`%s`\n' % (table_info.dbname, table_info.tablename))
    file.write('			</when>\n')
    file.write('			<when test="dbIndex != null and tbIndex == null">\n')
    file.write('				`%s_#{dbIndex}`.`%s`\n' % (table_info.dbname, table_info.tablename))
    file.write('			</when>\n')
    file.write('			<when test="dbIndex != null and tbIndex != null">\n')
    file.write('				`%s_#{dbIndex}`.`%s_#{tbIndex}`\n' % (table_info.dbname, table_info.tablename))
    file.write('			</when>\n')
    file.write('			<when test="dbIndex == null and tbIndex != null">\n')
    file.write('				`%s`.`%s_#{tbIndex}`\n' % (table_info.dbname, table_info.tablename))
    file.write('			</when>\n')
    file.write('		</choose>\n')
    file.write('	</sql>\n')
    file.write('</mapper>\n')
    file.close()
