# -*- coding: utf-8 -*-

import sys, os
import getopt

runpath = os.path.dirname(os.path.abspath(__file__))
self_module_path = os.path.abspath(os.path.join(runpath, '../'))
sys.path.append(self_module_path)

import app.code_generate.code_generate_excel_parse
from app.code_generate.table2sql import generate_sql
from app.code_generate.table2entity import generate_entity
from app.code_generate.table2mybatis import generate_mapper
from app.code_generate.table2service import generate_service
from app.code_generate.table2controller import generate_controller


def usage():
    print("Usage: code generate [options]")
    print("-p base package name")
    print("-f the excel file path")
    print("-o output dir")


def get_args():
    args, _ = getopt.getopt(sys.argv[1:], "p:e:f:o:h")
    print(args)
    arg_list = {'package_name': None, 'entity_name': None, 'file_input_path': None, 'file_output_path': None}
    if len(args) > 0:
        for key, value in args:
            print(key, value)
            if key == '-p':
                arg_list["package_name"] = value
            elif key == '-f':
                arg_list['file_input_path'] = value
            elif key == '-o':
                arg_list['file_output_path'] = value
            elif key == '-h':
                usage()
                sys.exit()

    if not arg_list['package_name']:
        print('package name is empty')
        sys.exit(1)
    if not arg_list['file_input_path']:
        print('file path is empty')
        sys.exit(1)
    if not arg_list['file_output_path']:
        arg_list['output'] = '../output/code-generate-output'
    elif arg_list['file_output_path'].endswith('/'):
        arg_list['output'] = arg_list['output'][0: (len(arg_list['output']) - 1)]

    return arg_list


if __name__ == '__main__':
    # arg_list = get_args()
    arg_list = {'package_name': 'com.xiaoye.iworks.basic', 'file_input_path': '../input/表格设计.xlsx',
                'file_output_path': '../output/code-generate-output'}
    db_table_list = app.code_generate.code_generate_excel_parse.parse(arg_list['file_input_path'])

    print(u'SQL脚本生成')
    generate_sql(db_table_list, '%s/db_sql' % arg_list['file_output_path'])

    generate_entity(db_table_list, arg_list['package_name'], arg_list['file_output_path'])
    generate_mapper(db_table_list, arg_list['package_name'], arg_list['file_output_path'])
    generate_service(db_table_list, arg_list['package_name'], arg_list['file_output_path'])
    generate_controller(db_table_list, arg_list['package_name'], arg_list['file_output_path'])
