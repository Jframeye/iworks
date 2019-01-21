# -*- coding: utf-8 -*-
import os, time

from common.str_utils import convert
from app.code_generate.entity.database_table import DB_COLUMN_DATA_TYPE_MAP, get_key

'''
package com.xiaoye.iworks.basic.api;

import com.xiaoye.iworks.basic.api.dto.AppConstantDto;
import com.xiaoye.iworks.basic.api.input.AppConstantQueryInput;
import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;

/**
 * 功能描述: 系统常量服务类(对外服务)
 * @auther: yehl
 * @date: 2019/1/4 12:37
 */
public interface AppConstantService {

    /**
     * 功能描述: 列表查询 <br>
     *     查询条件：
     *     constant_key[like]
     *     create_by[like]
     *     modify_by[like]
     * @param: queryInput
     * @return: 
     * @auther: yehl
     * @date: 2019/1/4 12:59
     */
    public PageResponse<AppConstantDto> listAppConstant(AppConstantQueryInput queryInput);

    /**
     * 功能描述: 数量统计 <br>
     *     统计条件：
     *     constant_key[like]
     * @param: queryInput
     * @return: 
     * @auther: yehl
     * @date: 2019/1/4 13:00
     */
    public DataResponse<Integer> countAppConstant(AppConstantQueryInput queryInput);

    /**
     * 功能描述: 明细查询 <br>
     *     查询条件：
     *     pkid[equal]
     *     constant_key[equal]
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/4 13:01
     */
    public DataResponse<AppConstantDto> findAppConstant(AppConstantQueryInput queryInput);

    /**
     * 功能描述: 数据删除 <br>
     *     删除条件：
     *     pkid[equal]
     *     pkids[in list]
     *     constant_key[equal]
     *     constant_keys[in list]
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/4 13:03
     */
    public DataResponse<Integer> deleteAppConstant(AppConstantQueryInput queryInput);

    /**
     * 功能描述: 数据更新
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/4 13:03
     */
    public DataResponse<Integer> updateAppConstant(AppConstantDto dto);

    /**
     * 功能描述: 数据新增
     * @param:
     * @return: 返回数据主键
     * @auther: yehl
     * @date: 2019/1/4 13:04
     */
    public DataResponse<Long> insertAppConstant(AppConstantDto dto);
}
'''


def generate_service(table_list, package_name, file_out_put_path):
    if table_list is None or len(table_list) == 0:
        print('table list is empty, no table could be processed')
        return

    package_path = package_name.replace('.', '/')
    file_out_put_path = '%s/%s/api' % (file_out_put_path, package_path)
    table_size = 0
    for (_, db_table) in table_list.items():
        _generate_service_input4table(db_table, package_name, file_out_put_path)
        _generate_service_dto4table(db_table, package_name, file_out_put_path)
        _generate_service_constant4table(db_table, package_name, file_out_put_path)
        _generate_service4table(db_table, package_name, file_out_put_path)
        _generate_service_impl4table(db_table, package_name, file_out_put_path)
        table_size += 1

    print('generate %d table service to %s' % (table_size, os.path.abspath(file_out_put_path)))
    print('generate %d table service impl to %s' % (table_size, os.path.abspath(file_out_put_path)))


def _generate_service_input4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path) + '/input'
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = entity_name + 'QueryInput.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo

    # package
    file.write('package %s.api.input;\r\n' % package_name)
    # import
    file.write('import com.xiaoye.iworks.api.input.Input;\n')
    file.write('import lombok.Data;\n')
    file.write('import lombok.EqualsAndHashCode;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: %s 查询入参\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('public class %sQueryInput extends Input {\n' % entity_name)
    file.write('    private static final long serialVersionUID = 1L;\r\n')

    file.write('}')
    file.close()


def _generate_service_dto4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path) + '/input'
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = entity_name + 'Dto.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo

    # package
    file.write('package %s.api.dto;\r\n' % package_name)
    # import
    file.write('import com.xiaoye.iworks.api.input.Input;\n')
    file.write('import lombok.Data;\n')
    file.write('import lombok.EqualsAndHashCode;\n')
    if table_info.hasdecimal:
        file.write('import java.math.BigDecimal;\n')
    if table_info.hasdate:
        file.write('import java.util.Date;\n')
    file.write('import java.util.List;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: %s 查询入参\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('@Data')
    file.write('@EqualsAndHashCode(callSuper = false)')
    file.write('public class %sDto implements Serializable {\n' % entity_name)
    file.write('    private static final long serialVersionUID = 1L;\r\n')

    file.write('}')
    file.close()


def _generate_service_constant4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path)
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = entity_name + 'Constant.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo

    # package
    file.write('package %s.api.constant;\r\n' % package_name)

    file.write('\n/**\n')
    file.write(' * 功能描述: 常量类【%s】\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('public class %sConstant {\r\n' % entity_name)

    file.write('}')
    file.close()


def _generate_service4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path)
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = entity_name + 'Service.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo

    # package
    file.write('package %s.api;\r\n' % package_name)
    # import
    file.write('import %s.api.dto.%sDto;\n' % (package_name, entity_name))
    file.write('import %s.api.input.%sQueryInput;\n' % (package_name, entity_name))
    file.write('import com.xiaoye.iworks.api.result.DataResponse;\n')
    file.write('import com.xiaoye.iworks.api.result.PageResponse;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: 实体类业务接口【%s】\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('public interface %sService {\r\n' % entity_name)

    file.write('    /**\n')
    file.write('     * 功能描述: 列表查询 <br>\n')
    file.write('     *     查询条件：\n')
    file.write('     * @param: queryInput\n')
    file.write('     * @return: \n')
    file.write('     * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')
    file.write('    public PageResponse<%sDto> list%ss(%sQueryInput queryInput);\r\n' % (
        entity_name, entity_name, entity_name))

    file.write('    /**\n')
    file.write('     * 功能描述: 明细查询 <br>\n')
    file.write('     *     查询条件：\n')
    file.write('     * @param: queryInput\n')
    file.write('     * @return: \n')
    file.write('     * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')
    file.write(
        '    public DataResponse<%sDto> find%s(%sQueryInput queryInput);\r\n' % (entity_name, entity_name, entity_name))

    file.write('    /**\n')
    file.write('     * 功能描述: 数据新增 <br>\n')
    file.write('     * @param: \n')
    file.write('     * @return: 返回数据主键\n')
    file.write('     * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')
    file.write('    public DataResponse<%sDto> insert%s(%sDto dto);\r\n' % (entity_name, entity_name, entity_name))

    file.write('    /**\n')
    file.write('     * 功能描述: 数据修改 <br>\n')
    file.write('     * @param: \n')
    file.write('     * @return: \n')
    file.write('     * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')
    file.write('    public DataResponse<Integer> update%s(%sDto dto);\r\n' % (entity_name, entity_name))

    file.write('    /**\n')
    file.write('     * 功能描述: 数据删除 <br>\n')
    file.write('     *     查询条件：\n')
    file.write('     * @param: queryInput\n')
    file.write('     * @return: \n')
    file.write('     * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')
    file.write('    public DataResponse<Integer> delete%s(%sQueryInput queryInput);\r\n' % (entity_name, entity_name))

    file.write('}')
    file.close()


def _generate_service_impl4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path)
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = entity_name + 'ServiceImpl.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo

    # package
    file.write('package %s.api;\r\n' % package_name)
    # import
    file.write('import %s.api.dto.%sDto;\n' % (package_name, entity_name))
    file.write('import %s.api.input.%sQueryInput;\n' % (package_name, entity_name))
    file.write('import com.xiaoye.iworks.api.result.DataResponse;\n')
    file.write('import com.xiaoye.iworks.api.result.PageResponse;\n')
    file.write('import org.springframework.stereotype.Service;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: 实体类业务接口实现类【%s】\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('@Service\n')
    file.write('public class %sServiceImpl implements %sService {\n' % (entity_name, entity_name))
    file.write('    public final Logger LOGGER = LoggerFactory.getLogger(getClass());\r\n')

    file.write('    @Override')
    file.write(
        '    public PageResponse<%sDto> list%ss(%sQueryInput queryInput) {\n' % (entity_name, entity_name, entity_name))
    file.write('        return null;')
    file.write('    }')

    file.write('    @Override')
    file.write(
        '    public DataResponse<%sDto> find%s(%sQueryInput queryInput) {\n' % (entity_name, entity_name, entity_name))
    file.write('        return null;')
    file.write('    }')

    file.write('    @Override')
    file.write('    public DataResponse<%sDto> insert%s(%sDto dto) {\n' % (entity_name, entity_name, entity_name))
    file.write('        return null;')
    file.write('    }')

    file.write('    @Override')
    file.write('    public DataResponse<Integer> update%s(%sDto dto) {\n' % (entity_name, entity_name))
    file.write('        return null;')
    file.write('    }')

    file.write('    @Override')
    file.write('    public DataResponse<Integer> delete%s(%sQueryInput queryInput) {\n' % (entity_name, entity_name))
    file.write('        return null;')
    file.write('    }')
    file.write("}")
    file.close()
