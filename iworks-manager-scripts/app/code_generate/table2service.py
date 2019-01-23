# -*- coding: utf-8 -*-
import os, time

from common.str_utils import convert
from app.code_generate.entity.database_table import DB_JAVA_PROPERTY_TYPE_MAP


def generate_service(table_list, package_name, file_out_put_path):
    if table_list is None or len(table_list) == 0:
        print('table list is empty, no table could be processed')
        return

    package_path = package_name.replace('.', '/')
    file_out_put_path = '%s/%s' % (file_out_put_path, package_path)
    table_size = 0
    for (_, db_table) in table_list.items():
        _generate_service_input4table(db_table, package_name, file_out_put_path)
        _generate_service_dto4table(db_table, package_name, file_out_put_path)
        _generate_service_constant4table(db_table, package_name, file_out_put_path)
        _generate_service4table(db_table, package_name, file_out_put_path)
        _generate_service_impl4table(db_table, package_name, file_out_put_path)
        table_size += 1

    print('generate %d table service to %s' % (table_size, os.path.abspath(file_out_put_path)))


def _generate_service_input4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path) + '/api/input'
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
    abs_path = os.path.abspath(file_output_path) + '/api/dto'
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
    table_columns = db_table.tablecolumns

    # package
    file.write('package %s.api.dto;\r\n' % package_name)
    # import
    file.write('import com.xiaoye.iworks.api.input.Input;\n')
    file.write('import java.io.Serializable;\n')
    file.write('import lombok.Data;\n')
    file.write('import lombok.EqualsAndHashCode;\n')
    if table_info.hasdecimal:
        file.write('import java.math.BigDecimal;\n')
    if table_info.hasdate:
        file.write('import java.util.Date;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: %s 查询入参\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('@Data\n')
    file.write('@EqualsAndHashCode(callSuper = false)\n')
    file.write('public class %sDto implements Serializable {\n' % entity_name)
    file.write('    private static final long serialVersionUID = 1L;\r\n')

    for table_column in table_columns:
        property_type = DB_JAVA_PROPERTY_TYPE_MAP[table_column.datatype]
        file.write('    /** %s **/\n' % table_column.desc)
        file.write('    private %s %s;\n' % (property_type, convert(table_column.columnname, '_')))

    file.write('}')
    file.close()


def _generate_service_constant4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path) + '/api/constant'
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


def _generate_service_exception4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path) + '/core/exception'
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = entity_name + 'ErrorCode.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo

    # package
    file.write('package %s.core.exception;\r\n' % package_name)

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
    file.write('package %s;\r\n' % package_name)
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
    abs_path = os.path.abspath(file_output_path) + '/core'
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
    file.write('package %s.core;\r\n' % package_name)
    # import
    file.write('import %s.api.dto.%sDto;\n' % (package_name, entity_name))
    file.write('import %s.api.input.%sQueryInput;\n' % (package_name, entity_name))
    file.write('import com.xiaoye.iworks.api.result.DataResponse;\n')
    file.write('import com.xiaoye.iworks.api.result.PageResponse;\n')
    file.write('import org.slf4j.Logger;\n')
    file.write('import org.slf4j.LoggerFactory;\n')
    file.write('import org.springframework.stereotype.Service;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: 实体类业务接口实现类【%s】\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('@Service\n')
    file.write('public class %sServiceImpl implements %sService {\n' % (entity_name, entity_name))
    file.write('    public final Logger LOGGER = LoggerFactory.getLogger(getClass());\r\n')

    file.write('    @Override\n')
    file.write(
        '    public PageResponse<%sDto> list%ss(%sQueryInput queryInput) {\n' % (entity_name, entity_name, entity_name))
    file.write('        return null;\n')
    file.write('    }\r\n')

    file.write('    @Override\n')
    file.write(
        '    public DataResponse<%sDto> find%s(%sQueryInput queryInput) {\n' % (entity_name, entity_name, entity_name))
    file.write('        return null;\n')
    file.write('    }\r\n')

    file.write('    @Override\n')
    file.write('    public DataResponse<%sDto> insert%s(%sDto dto) {\n' % (entity_name, entity_name, entity_name))
    file.write('        return null;\n')
    file.write('    }\r\n')

    file.write('    @Override\n')
    file.write('    public DataResponse<Integer> update%s(%sDto dto) {\n' % (entity_name, entity_name))
    file.write('        return null;\n')
    file.write('    }\r\n')

    file.write('    @Override\n')
    file.write('    public DataResponse<Integer> delete%s(%sQueryInput queryInput) {\n' % (entity_name, entity_name))
    file.write('        return null;\n')
    file.write('    }\n')
    file.write("}")
    file.close()
