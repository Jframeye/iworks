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
        _generate_service_exception4table(db_table, package_name, file_out_put_path)
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
    if entity_name.endswith('constant'):
        file_name = entity_name + '.java'

    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo

    # package
    file.write('package %s.api.constant;\r\n' % package_name)

    file.write('\n/**\n')
    file.write(' * 功能描述: 【%s】常量类\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('public class %sConstant {\r\n' % entity_name)
    file.write('    public static interface State {\n')
    file.write('        public static final Integer NORMAL = 1;\n')
    file.write('        // TODO 其他状态类型\r\n')
    file.write('    }\n')
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
    file.write('import com.xiaoye.iworks.common.exception.annotation.Error;\n')
    file.write('import com.xiaoye.iworks.utils.exception.ServiceErrorCode;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: 【%s】异常编码类【3624222019%s】\n' % (table_info.tabledesc, table_info.index))
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('public class %sErrorCode extends ServiceErrorCode {\r\n' % entity_name)
    file.write('    @Error(code = "3624222019%s01", desc = "%s数据查询异常")\n' % (table_info.index, table_info.tabledesc))
    file.write('    public static final String DATA_QRY_ERROR = "3624222019%s01";\n' % table_info.index)
    file.write('    @Error(code = "3624222019%s02", desc = "%s数据已存在异常")\n' % (table_info.index, table_info.tabledesc))
    file.write('    public static final String DATA_EXIST_ERROR = "3624222019%s02";\n' % table_info.index)
    file.write('    @Error(code = "3624222019%s03", desc = "%s数据不存在异常")\n' % (table_info.index, table_info.tabledesc))
    file.write('    public static final String DATA_UNEXIST_ERROR = "3624222019%s03";\n' % table_info.index)
    file.write('    @Error(code = "3624222019%s04", desc = "%s数据新增异常")\n' % (table_info.index, table_info.tabledesc))
    file.write('    public static final String DATA_INSERT_ERROR = "3624222019%s04";\n' % table_info.index)
    file.write('    @Error(code = "3624222019%s05", desc = "%s数据更新异常")\n' % (table_info.index, table_info.tabledesc))
    file.write('    public static final String DATA_UPDATE_ERROR = "3624222019%s05";\n' % table_info.index)
    file.write('    @Error(code = "3624222019%s06", desc = "%s数据刪除异常")\n' % (table_info.index, table_info.tabledesc))
    file.write('    public static final String DATA_DELETE_ERROR = "3624222019%s06";\n' % table_info.index)
    file.write('\n    public static final Class getCurrentClass(){\n')
    file.write('        return new Object(){\n')
    file.write('            public Class getClassForStatic(){\n')
    file.write('                return this.getClass();\n')
    file.write('            }\n')
    file.write('        }.getClassForStatic();\n')
    file.write('    }\n')
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
    file.write('    public DataResponse<Long> insert%s(%sDto dto);\r\n' % (entity_name, entity_name))

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
    up_entity_name = convert(entity_name, '_', True)  # 实体类名称
    lo_entity_name = convert(entity_name, '_', True)  # 实体类名称
    file_name = up_entity_name + 'ServiceImpl.java'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    table_info = db_table.tableinfo

    # package
    file.write('package %s.core;\r\n' % package_name)
    # import
    file.write('import com.xiaoye.iworks.api.result.DataResponse;\n')
    file.write('import com.xiaoye.iworks.api.result.PageResponse;\n')
    file.write('import %s.api.%sService;\n' % (package_name, up_entity_name))
    if entity_name.endswith('constant'):
        file.write('import %s.api.constant.%s;\n' % (package_name, up_entity_name))
    else:
        file.write('import %s.api.constant.%sConstant;\n' % (package_name, up_entity_name))
    file.write('import %s.api.dto.%sDto;\n' % (package_name, up_entity_name))
    file.write('import %s.api.input.%sQueryInput;\n' % (package_name, up_entity_name))
    file.write('import %s.core.exception.%sErrorCode;\n' % (package_name, up_entity_name))
    file.write('import %s.persistent.entity.%sCriteria;\n' % (package_name, up_entity_name))
    file.write('import %s.persistent.entity.%sDO;\n' % (package_name, up_entity_name))
    file.write('import %s.persistent.mapper.%sMapper;\n' % (package_name, up_entity_name))
    file.write('import com.xiaoye.iworks.common.exception.BizServiceException;\n')
    file.write('import com.xiaoye.iworks.persistent.constant.PersistentConstant;\n')
    file.write('import com.xiaoye.iworks.utils.CollectionUtils;\n')
    file.write('import com.xiaoye.iworks.utils.DateTimeUtils;\n')
    file.write('import com.xiaoye.iworks.utils.exception.ServiceException;\n')
    file.write('import org.slf4j.Logger;\n')
    file.write('import org.slf4j.LoggerFactory;\n')
    file.write('import org.springframework.beans.BeanUtils;\n')
    file.write('import org.springframework.stereotype.Service;\n')
    file.write('import javax.annotation.Resource;\n')
    file.write('import java.util.List;\n')

    file.write('\n/**\n')
    file.write(' * 功能描述: 实体类业务接口实现类【%s】\n' % table_info.tabledesc)
    file.write(' * @auther: auto create by python \n')
    file.write(' * @date: %s \n' % time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
    file.write(' */\n')

    file.write('@Service\n')
    file.write('public class %sServiceImpl implements %sService {\n' % (up_entity_name, up_entity_name))
    file.write('    public final Logger LOGGER = LoggerFactory.getLogger(getClass());\r\n')

    file.write('    @Resource\n')
    file.write('    private %sMapper %sMapper;\n' % (up_entity_name, lo_entity_name))

    file.write('    @Override\n')
    file.write(
        '    public PageResponse<%sDto> list%ss(%sQueryInput queryInput) {\n' % (up_entity_name, up_entity_name, up_entity_name))
    file.write('        PageResponse<%sDto> response = new PageResponse<>();\n' % up_entity_name)
    file.write('        try {\n')
    file.write('            %sCriteria criteria = new %sCriteria();\n' % (up_entity_name, up_entity_name))
    file.write('            // TODO do something here \r\n')
    file.write('            if(queryInput.isPagenation()) {\n')
    file.write('                criteria.setPagination(queryInput.isPagenation());\n')
    file.write('                criteria.setOffset(queryInput.getOffset());\n')
    file.write('                criteria.setLimit(queryInput.getLimit());\n')
    file.write('                Integer total = %sMapper.count(criteria);\n' % lo_entity_name)
    file.write('                response.getData().setOffset(queryInput.getOffset());\n')
    file.write('                response.getData().setLimit(queryInput.getLimit());\n')
    file.write('                response.getData().setTotal(total);\n')
    file.write('                if(total == 0) {\n')
    file.write('                    return response;\n')
    file.write('                }\n')
    file.write('            }\n')
    file.write('            List<%sDO> results = %sMapper.selectForList(criteria);\n' % (up_entity_name, lo_entity_name))
    file.write('            List<%sDto> datas = CollectionUtils.transform(results, (entity) -> {\n' % up_entity_name)
    file.write('                %sDto dto = new %sDto();\n' % (up_entity_name, up_entity_name))
    file.write('                BeanUtils.copyProperties(entity, dto);\n')
    file.write('                return dto;\n')
    file.write('            });\n')
    file.write('            response.getData().setDatas(datas);\n')
    file.write('        } catch (ServiceException e) {\n')
    file.write('            response.setRetcode(e.getCode());\n')
    file.write('            response.setMessage(e.getMessage());\n')
    file.write('            LOGGER.error(e.getCode(), e);\n')
    file.write('        } catch (Exception e) {\n')
    file.write('            response.setRetcode(%sErrorCode.DATA_QRY_ERROR);\n' % up_entity_name)
    file.write('            response.setMessage("%s数据列表查询异常");\n' % table_info.tabledesc)
    file.write('            LOGGER.error(%sErrorCode.DATA_QRY_ERROR, e);\n' % up_entity_name)
    file.write('        }\n')
    file.write('        return response;\n')
    file.write('    }\r\n')

    file.write('    @Override\n')
    file.write(
        '    public DataResponse<%sDto> find%s(%sQueryInput queryInput) {\n' % (up_entity_name, up_entity_name, up_entity_name))
    file.write('        DataResponse<%sDto> response = new DataResponse<>();\n' % up_entity_name)
    file.write('        try {\n')
    file.write('            %sCriteria criteria = new %sCriteria();\n' % (up_entity_name, up_entity_name))
    file.write('            // TODO do something here \r\n')
    file.write('            %sDO result = %sMapper.selectForOne(criteria);\n' % (up_entity_name, lo_entity_name))
    file.write('            if(result == null) {\n')
    file.write('                if(queryInput.isCheckNull()) {\n')
    file.write('                    response.setRetcode(%sErrorCode.DATA_UNEXIST_ERROR);\n' % up_entity_name)
    file.write('                }\n')
    file.write('                response.setMessage("%s数据详情不存在");\n' % table_info.tabledesc)
    file.write('                return response;\n')
    file.write('            }\n')
    file.write('            %sDto dto = new %sDto();\n' % (up_entity_name, up_entity_name))
    file.write('            BeanUtils.copyProperties(result, dto);\n')
    file.write('            response.setData(dto);\n')
    file.write('        } catch (ServiceException e) {\n')
    file.write('            response.setRetcode(e.getCode());\n')
    file.write('            response.setMessage(e.getMessage());\n')
    file.write('            LOGGER.error(e.getCode(), e);\n')
    file.write('        } catch (Exception e) {\n')
    file.write('            response.setRetcode(%sErrorCode.DATA_QRY_ERROR);\n' % up_entity_name)
    file.write('            response.setMessage("%s数据详情查询异常");\n' % table_info.tabledesc)
    file.write('            LOGGER.error(%sErrorCode.DATA_QRY_ERROR, e);\n' % up_entity_name)
    file.write('        }\n')
    file.write('        return response;\n')
    file.write('    }\r\n')

    file.write('    @Override\n')
    file.write('    public DataResponse<Long> insert%s(%sDto dto) {\n' % (up_entity_name, up_entity_name))
    file.write('        DataResponse<Long> response = new DataResponse<>();\n')
    file.write('        try {\n')
    file.write('            %sDO %sDO = new %sDO();\n' % (up_entity_name, lo_entity_name, up_entity_name))
    file.write('            BeanUtils.copyProperties(dto, %sDO);\n' % lo_entity_name)
    if entity_name.endswith('constant'):
        file.write('            %sDO.setState(%s.State.NORMAL);\n' % (lo_entity_name, up_entity_name))
    else:
        file.write('            %sDO.setState(%sConstant.State.NORMAL);\n' % (lo_entity_name, up_entity_name))
    file.write('            %sDO.setLstate(PersistentConstant.Lstate.NORMAL);\n' % lo_entity_name)
    file.write('            %sDO.setCreateTime(DateTimeUtils.currentDate());\n' % lo_entity_name)
    file.write('            %sDO.setModifyTime(DateTimeUtils.currentDate());\n' % lo_entity_name)
    file.write('            %sMapper.insertSelective(%sDO);\n' % (lo_entity_name, lo_entity_name))
    file.write('            response.setData(%sDO.getPkid());\n' % lo_entity_name)
    file.write('        } catch (ServiceException e) {\n')
    file.write('            response.setRetcode(e.getCode());\n')
    file.write('            response.setMessage(e.getMessage());\n')
    file.write('            LOGGER.error(e.getCode(), e);\n')
    file.write('        } catch (Exception e) {\n')
    file.write('            response.setRetcode(%sErrorCode.DATA_INSERT_ERROR);\n' % up_entity_name)
    file.write('            response.setMessage("%s数据新增异常");\n' % table_info.tabledesc)
    file.write('            LOGGER.error(%sErrorCode.DATA_INSERT_ERROR, e);\n' % up_entity_name)
    file.write('        }\n')
    file.write('        return response;\n')
    file.write('    }\r\n')

    file.write('    @Override\n')
    file.write('    public DataResponse<Integer> update%s(%sDto dto) {\n' % (up_entity_name, up_entity_name))
    file.write('        DataResponse<Integer> response = new DataResponse<>();\n')
    file.write('        try {\n')
    file.write('            %sDO %sDO = new %sDO();\n' % (up_entity_name, lo_entity_name, up_entity_name))
    file.write('            BeanUtils.copyProperties(dto, %sDO);\n' % lo_entity_name)
    file.write('            %sDO.setModifyTime(DateTimeUtils.currentDate());\n' % lo_entity_name)
    file.write('            Integer result = %sMapper.insertSelective(%sDO);\n' % (lo_entity_name, lo_entity_name))
    file.write('            response.setData(result);\n')
    file.write('        } catch (ServiceException e) {\n')
    file.write('            response.setRetcode(e.getCode());\n')
    file.write('            response.setMessage(e.getMessage());\n')
    file.write('            LOGGER.error(e.getCode(), e);\n')
    file.write('        } catch (Exception e) {\n')
    file.write('            response.setRetcode(%sErrorCode.DATA_UPDATE_ERROR);\n' % up_entity_name)
    file.write('            response.setMessage("%s数据修改异常");\n' % table_info.tabledesc)
    file.write('            LOGGER.error(%sErrorCode.DATA_UPDATE_ERROR, e);\n' % up_entity_name)
    file.write('        }\n')
    file.write('        return response;\n')
    file.write('    }\r\n')

    file.write('    @Override\n')
    file.write('    public DataResponse<Integer> delete%s(%sQueryInput queryInput) {\n' % (up_entity_name, up_entity_name))
    file.write('        DataResponse<Integer> response = new DataResponse<>();\n')
    file.write('        try {\n')
    file.write('            %sCriteria criteria = new %sCriteria();\n' % (up_entity_name, up_entity_name))
    file.write('            // TODO do something here \r\n')
    file.write('            Integer result = %sMapper.delete(criteria);\n' % lo_entity_name)
    file.write('            response.setData(result);\n')
    file.write('        } catch (ServiceException e) {\n')
    file.write('            response.setRetcode(e.getCode());\n')
    file.write('            response.setMessage(e.getMessage());\n')
    file.write('            LOGGER.error(e.getCode(), e);\n')
    file.write('        } catch (Exception e) {\n')
    file.write('            response.setRetcode(%sErrorCode.DATA_DELETE_ERROR);\n' % up_entity_name)
    file.write('            response.setMessage("%s数据删除异常");\n' % table_info.tabledesc)
    file.write('            LOGGER.error(%sErrorCode.DATA_DELETE_ERROR, e);\n' % up_entity_name)
    file.write('        }\n')
    file.write('        return response;\n')
    file.write('    }\n')
    file.write("}")
    file.close()
