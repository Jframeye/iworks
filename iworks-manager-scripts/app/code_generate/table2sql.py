# -*- coding: utf-8 -*-
import os

'''
example:
﻿create database if not exists xxx;
use xxx;
CREATE TABLE if not exists xxxx(
    xxx bigint(20) AUTO_INCREMENT,
    yyy bigint(20) NOT NULL,
    PRIMARY KEY (`xxx`),
    UNIQUE KEY `idx_uid_curtype` (`Fuid`,`Fcur_type`),
    KEY `idx_mtime` (`Fmodify_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
'''


def generate_sql(table_list, file_out_put_path):
    if table_list is None or len(table_list) == 0:
        print('table list is empty, no table could be processed')
        return

    table_size = 0
    for (_, db_table) in table_list.items():
        _generate_sql4table(db_table, file_out_put_path)
        table_size += 1

    print('generate %d table sql to %s' % (table_size, os.path.abspath(file_out_put_path)))


def _generate_sql4table(db_table, file_output_path):
    abs_path = os.path.abspath(file_output_path)
    if not os.path.exists(abs_path):
        os.makedirs(abs_path)

    file_name = db_table.tableinfo.dbname + '.' + db_table.tableinfo.tablename + '.sql'
    file_path = os.path.join(abs_path, file_name)
    file = open(file_path, 'w', encoding='utf-8')

    # 表注释
    if db_table.tableinfo.tabledesc is not None:
        file.write('/**\n')
        file.write(' ** ' + db_table.tableinfo.tabledesc)
        file.write('\n**/')

    file.write('\r\nCREATE DATABASE IF NOT EXISTS %s;' % db_table.tableinfo.dbname)
    file.write('\nUSE %s;' % db_table.tableinfo.dbname)
    file.write('\r\nCREATE TABLE IF NOT EXISTS `%s` (' % db_table.tableinfo.tablename)

    # 表字段
    is_first = True
    for table_column in db_table.tablecolumns:
        table_column_sql = _generate_column_sql(table_column)
        if is_first:
            is_first = False
            file.write('\n')
            file.write(table_column_sql)
        else:
            file.write(',\n')
            file.write(table_column_sql)

    # 表索引
    if db_table.tableindexs is not None and len(db_table.tableindexs) > 0:
        for table_index in db_table.tableindexs:
            table_index_sql = _generate_index_sql(table_index)
            if is_first:
                is_first = False
                file.write('\n' + table_index_sql)
            else:
                file.write(',\n' + table_index_sql)

    file.write(
        '\n) ENGINE=InnoDB CHARSET=utf8 COLLATE=utf8_bin COMMENT=\'' + db_table.tableinfo.tabledesc + '\';\r\n')
    file.close()


def _generate_column_sql(table_column):
    allow_null = ''
    if not table_column.isnull:
        allow_null = ' NOT NULL'

    def_value = ''
    if table_column.defvalue is not None and len(table_column.defvalue) > 0:
        def_value = ' DEFAULT ' + str(table_column.defvalue)

    comment = ''
    if table_column.desc is not None and len(table_column.desc) > 0:
        comment = table_column.desc

    if comment:
        comment = ' COMMENT \'' + comment.strip() + '\''

    auto_increment = ''
    if comment.find('auto_increment') >= 0:
        auto_increment = ' AUTO_INCREMENT'

    return '    `%s` %s%s%s%s%s' % (
        table_column.columnname, table_column.fulldatatype, allow_null, def_value, auto_increment, comment)


TBI_PRIMARY_KEY = 1
TBI_UNIQUE_KEY = 2
TBI_KEY = 3
KEY_MAP = {
    TBI_PRIMARY_KEY: 'PRIMARY KEY',
    TBI_UNIQUE_KEY: 'UNIQUE KEY',
    TBI_KEY: 'KEY'
}


def _generate_index_sql(table_index):
    index_type = KEY_MAP[table_index.indextype]
    index_name = table_index.indexname
    if index_name is not None and len(index_name) > 0:
        index_name = ' `' + index_name + '`'

    index_column = ''
    for column in table_index.indexcolumns:
        index_column += '`' + column + '`,'

    index_column = index_column.strip(',')
    return '  %s%s (%s)' % (index_type, index_name, index_column)
