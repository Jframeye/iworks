# -*- coding: utf-8 -*-
import os, time

from common.str_utils import convert
from app.code_generate.entity.database_table import DB_COLUMN_DATA_TYPE_MAP, get_key


def generate_controller(table_list, package_name, file_out_put_path):
    if table_list is None or len(table_list) == 0:
        print('table list is empty, no table could be processed')
        return

    package_path = package_name.replace('.', '/')
    file_out_put_path = '%s/%s' % (file_out_put_path, package_path)
    table_size = 0
    for (_, db_table) in table_list.items():
        _generate_controller_query4table(db_table, package_name, file_out_put_path)
        _generate_controller_update4table(db_table, package_name, file_out_put_path)
        _generate_controller4table(db_table, package_name, file_out_put_path)
        table_size += 1

    print('generate %d table controller to %s' % (table_size, os.path.abspath(file_out_put_path)))


def _generate_controller_query4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path) + '/request'
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = entity_name + 'QueryRequest.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo

    # package
    file.write('package %s.request;\r\n' % package_name)
    # import
    file.write('import com.xiaoye.iworks.api.input.Input;\n')
    file.write('import lombok.Data;\n')
    file.write('import lombok.EqualsAndHashCode;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: %s 数据查询入参\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('@Data\n')
    file.write('@EqualsAndHashCode(callSuper = false)\n')
    file.write('public class %sQueryRequest extends Input {\n' % entity_name)
    file.write('    private static final long serialVersionUID = 1L;\r\n')

    file.write('}')
    file.close()


def _generate_controller_update4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path) + '/request'
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = entity_name + 'UpdateRequest.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo

    # package
    file.write('package %s.request;\r\n' % package_name)
    # import
    file.write('import com.xiaoye.iworks.api.input.Input;\n')
    file.write('import lombok.Data;\n')
    file.write('import lombok.EqualsAndHashCode;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: %s 数据更新入参\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('@Data\n')
    file.write('@EqualsAndHashCode(callSuper = false)\n')
    file.write('public class %sUpdateRequest extends Input {\n' % entity_name)
    file.write('    private static final long serialVersionUID = 1L;\r\n')

    file.write('}')
    file.close()


def _generate_controller4table(db_table, package_name, file_output_path):
    abs_path = os.path.abspath(file_output_path)
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    entity_name = db_table.tableinfo.tablename
    if entity_name.startswith('t_'):
        entity_name = entity_name[2:]
    up_entity_name = convert(entity_name, '_', True)  # 实体类名称
    lo_entity_name = convert(entity_name, '_')  # 实体类名称
    file_name = up_entity_name + 'Controller.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo

    # package
    file.write('package %s;\r\n' % package_name)
    file.write('import com.xiaoye.iworks.api.result.Response;\n')
    file.write('import %s.api.%sService;\n' % (package_name, up_entity_name))
    file.write('import %s.api.dto.%sDto;\n' % (package_name, up_entity_name))
    file.write('import %s.api.input.%sQueryInput;\n' % (package_name, up_entity_name))
    file.write('import %s.request.%sQueryRequest;\n' % (package_name, up_entity_name))
    file.write('import %s.request.%sUpdateRequest;\n' % (package_name, up_entity_name))
    file.write('import com.xiaoye.iworks.common.api.BasicController;\n')
    file.write('import com.xiaoye.iworks.common.logger.annotation.RecordLogger;\n')
    file.write('import org.springframework.beans.factory.annotation.Autowired;\n')
    file.write('import org.springframework.web.bind.annotation.RequestMapping;\n')
    file.write('import org.springframework.web.bind.annotation.RestController;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: 【%s】 控制器类\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('@RestController\n')
    file.write('@RequestMapping(value = "%s", produces = "application/json")\n' % entity_name)
    file.write('public class %sController extends BasicController {\r\n' % up_entity_name)

    file.write('    @Autowired\n')
    file.write('    private %sService %sService;\r\n' % (up_entity_name, lo_entity_name))

    file.write('    @RecordLogger\n')
    file.write('    @RequestMapping(value = "list")\n')
    file.write('    public Response list(%sQueryRequest request) {\n' % up_entity_name)
    file.write('        %sQueryInput queryInput = new %sQueryInput();\n' % (up_entity_name, up_entity_name))
    file.write('        // TODO 参数校验以及参数填充\r\n')
    file.write('        return %sService.list%ss(queryInput);\n' % (lo_entity_name, up_entity_name))
    file.write('    }\r\n')

    file.write('    @RecordLogger\n')
    file.write('    @RequestMapping(value = "find")\n')
    file.write('    public Response find(%sQueryRequest request) {\n' % up_entity_name)
    file.write('        %sQueryInput queryInput = new %sQueryInput();\n' % (up_entity_name, up_entity_name))
    file.write('        // TODO 参数校验以及参数填充\r\n')
    file.write('        return %sService.find%s(queryInput);\n' % (lo_entity_name, up_entity_name))
    file.write('    }\r\n')

    file.write('    @RecordLogger\n')
    file.write('    @RequestMapping(value = "update")\n')
    file.write('    public Response update(%sUpdateRequest request) {\n' % up_entity_name)
    file.write('        %sDto dto = new %sDto();\n' % (up_entity_name, up_entity_name))
    file.write('        // TODO 参数填充(判断新增or修改)\r\n')
    file.write('        return null;\n')
    file.write('    }\r\n')

    file.write('    @RecordLogger\n')
    file.write('    @RequestMapping(value = "delete")\n')
    file.write('    public Response delete(%sQueryRequest request) {\n' % up_entity_name)
    file.write('        %sQueryInput queryInput = new %sQueryInput();\n' % (up_entity_name, up_entity_name))
    file.write('        // TODO 参数校验以及参数填充\r\n')
    file.write('        return %sService.delete%s(queryInput);\n' % (lo_entity_name, up_entity_name))
    file.write('    }\r\n')

    file.write('}')
    file.close()

