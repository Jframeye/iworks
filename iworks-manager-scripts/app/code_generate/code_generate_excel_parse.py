# -*- coding: utf-8 -*-

# 导入Excel读取扩展包
import re
import xlrd

from app.code_generate.entity.database_table import DatabaseTable, TableInfo, TableIndex, TableColumn, \
    DB_COLUMN_DATA_TYPE_MAP


def parse(file_path):
    db_table_list = {}
    # 打开Excel文件读取数据
    workbook = xlrd.open_workbook(file_path)
    # 获取excel工作簿名称
    sheet_names = workbook.sheet_names()
    print(u'sheet 数量：' + str(sheet_names))
    for sheet_name in sheet_names:
        # 获取工作簿
        sheet = workbook.sheet_by_name(sheet_name)
        db_table = _parse_sheet(sheet)
        print(db_table)
        if db_table is None:
            continue
        key = db_table.tableinfo.dbname + '.' + db_table.tableinfo.tablename
        if key in db_table_list:
            raise ValueError('table %s exist already' % key)
        if db_table.tableinfo.autocreate == u'是':
            db_table_list[key] = db_table

    workbook.release_resources()
    return db_table_list


'''
解析excel
'''


def _parse_sheet(sheet):
    if sheet is None:
        return None

    table_info = None
    table_indexs = None
    table_columns = None

    current_row = 0
    # 数据开始列，即“1.表描述”所在列
    start_column = 1
    while current_row < sheet.nrows:
        cell_value = sheet.cell(current_row, start_column).value
        if cell_value is None:
            continue
        cell_value = cell_value.strip()
        if cell_value == u'1.表描述':
            result = _get_table_info(sheet, current_row + 1, start_column)
            current_row = result[0]
            table_info = result[1]
        elif cell_value == u'2.表索引':
            result = _get_table_indexs(sheet, current_row + 2, start_column)
            current_row = result[0]
            table_indexs = result[1]
        elif cell_value == u'3.表字段结构':
            result = _get_table_columns(sheet, current_row + 2, start_column)
            current_row = result[0]
            table_columns = result[1]

        current_row += 1
    if table_info is None or table_columns is None or len(table_columns) == 0:
        return None

    primary_keys = [index.indexcolumns for index in table_indexs if index.indextype == 1]
    if len(primary_keys) > 0:
        primary_keys = primary_keys[0][0].split(',')
    for table_column in table_columns:
        if DB_COLUMN_DATA_TYPE_MAP['datetime'] == table_column.datatype:
            table_info.hasdate = True
        if DB_COLUMN_DATA_TYPE_MAP['decimal'] == table_column.datatype:
            table_info.hasdecimal = True
        # 判断字段是否为主键
        if table_column.columnname in primary_keys:
            table_column.primary = True
            table_info.primarykeys.append(table_column.columnname)
    return DatabaseTable(table_info, table_indexs, table_columns)


def _get_table_info(sheet, row, col):
    db_name = None
    table_name = None
    table_desc = None
    auto_create = None

    current_row = row
    while current_row < sheet.nrows:
        cell_value = sheet.cell(current_row, col).value
        if cell_value is None:
            continue
        cell_value = cell_value.strip()
        if _is_item_flag(cell_value):
            break
        if cell_value == u"表名":
            db_table_name = sheet.cell(current_row, col + 1).value
            # 解析数据库名称和表名称
            table_info = _parse_db_table_name(db_table_name)
            db_name = table_info[0]
            table_name = table_info[1]
        elif cell_value == u"描述":
            table_desc = sheet.cell(current_row, col + 1).value
        elif cell_value == u"创建":
            auto_create = sheet.cell(current_row, col + 1).value
        current_row += 1

    table_info = TableInfo(db_name.strip(), table_name.strip(), table_desc, auto_create)
    return [current_row - 1, table_info]


def _parse_db_table_name(db_table_name):
    regex = "(^.*)\.(.*)"
    match_result = re.match(regex, db_table_name)
    if match_result:
        db_name = match_result.group(1)
        table_name = match_result.group(2)
        if db_name is None:
            print("%s: invlid database name" % db_table_name)
            raise ValueError('invlid database/table name')
        if table_name is None:
            print("%s: invlid table name" % db_table_name)
            raise ValueError('invlid database/table name')

        return [db_name, table_name]
    else:
        print("%s: invlid database/table name" % db_table_name)
        raise ValueError('invlid database/table name')


def _get_table_indexs(sheet, row, col):
    table_indexs = []
    current_row = row
    parse_primary_key = False # 是否解析了主键，主键只能存在一栏
    while current_row < sheet.nrows:
        cell_value = sheet.cell(current_row, col).value
        if cell_value is None:
            continue
        cell_value = cell_value.strip()
        if _is_item_flag(cell_value):
            break

        if cell_value == u"主键":
            if parse_primary_key:
                print('table primary key must be one row')
                raise TypeError('主键栏只能一行，多个主键使用英文逗号相连')
            table_index = _parse_table_index(sheet, 1, current_row, col + 1)
            parse_primary_key = True
        elif cell_value == u"唯一索引":
            table_index = _parse_table_index(sheet, 2, current_row, col + 1)
        elif cell_value == u"索引":
            table_index = _parse_table_index(sheet, 3, current_row, col + 1)
        else:
            table_index = None

        if table_index is not None:
            table_indexs.append(table_index)
        current_row += 1

    return [current_row - 1, table_indexs]


# 索引解析
def _parse_table_index(sheet, index_type, row, col):
    index_name = sheet.cell(row, col).value
    index_columns = sheet.cell(row, col + 2).value

    if index_type != 1 and (index_name is None or len(index_name) == 0):
        return None

    if index_columns is None or len(index_columns) == 0:
        return None

    # 索引字段
    columns = index_columns.split(",")
    # 过滤空格
    for i in range(0, len(columns)):
        columns[i] = columns[i].strip()

    return TableIndex(index_type, index_name.strip(), columns)


def _get_table_columns(sheet, row, col):
    table_columns = []
    current_row = row
    while current_row < sheet.nrows:
        table_column = _parse_table_column(sheet, current_row, col)
        if table_column is not None:
            table_columns.append(table_column)
        current_row += 1

    return [current_row - 1, table_columns]


def _parse_table_column(sheet, row, col):
    column_desc = sheet.cell(row, col).value.strip()
    column_name = sheet.cell(row, col + 1).value.strip()
    if column_name is None or len(column_name) == 0:
        return None
    full_data_type = sheet.cell(row, col + 2).value.strip()
    if full_data_type is None or len(full_data_type) == 0:
        return None
    pos = full_data_type.find('(')
    data_type = full_data_type
    if pos > 0:
        data_type = full_data_type[0: pos]
    data_type = _check_column_data_type(column_name, data_type)

    is_not_null = sheet.cell(row, col + 3).value.strip()
    is_null = True
    if is_not_null == 'Y':
        is_null = False

    def_value = str(sheet.cell(row, col + 4).value).strip()
    if def_value.lower() == 'null':
        def_value = 'NULL'
    elif len(def_value) == 0:
        def_value = None
    else:
        def_value = '\'%s\'' % def_value
    desc = sheet.cell(row, col + 5).value.strip()
    if desc is not None and len(desc) > 0:
        column_desc = column_desc + '[' + desc + ']'

    return TableColumn(column_name, full_data_type, data_type, is_null, def_value, column_desc)


def _check_column_data_type(column_name, data_type):
    if data_type is None or len(data_type) == 0:
        print('table column data type for %s is empty' % column_name)
        raise TypeError('invalid table column data type for %s' % column_name)

    data_type = data_type.lower()
    if data_type in DB_COLUMN_DATA_TYPE_MAP:
        return DB_COLUMN_DATA_TYPE_MAP[data_type]
    else:
        print(column_name, data_type)
        raise TypeError('invalid table column data type %s for %s' % (data_type, column_name))


def _is_item_flag(text):
    if text == u'1.表描述':
        return True
    elif text == u'2.表索引':
        return True
    elif text == u'3.表字段结构':
        return True
    return False
