# -*- coding: utf-8 -*-

class DatabaseTable(object):
    def __init__(self, table_info, table_indexs, table_columns):
        self._table_info = table_info
        self._table_indexs = table_indexs
        self._table_columns = table_columns

    @property
    def tableinfo(self):
        return self._table_info

    @property
    def tableindexs(self):
        return self._table_indexs

    @property
    def tablecolumns(self):
        return self._table_columns


class TableInfo(object):
    def __init__(self, db_name, table_name, table_desc, auto_create, has_date=False, has_decimal=False,
                 primary_keys=[]):
        self._db_name = db_name
        self._table_name = table_name
        self._table_desc = table_desc
        self._auto_create = auto_create
        self._has_date = has_date
        self._has_decimal = has_decimal
        self._primary_keys = primary_keys
        self._index = 0

    @property
    def dbname(self):
        return self._db_name

    @property
    def tablename(self):
        return self._table_name

    @property
    def tabledesc(self):
        return self._table_desc

    @property
    def autocreate(self):
        return self._auto_create

    @property
    def hasdate(self):
        return self._has_date

    @hasdate.setter
    def hasdate(self, value):
        self._has_date = value

    @property
    def hasdecimal(self):
        return self._has_decimal

    @hasdecimal.setter
    def hasdecimal(self, value):
        self._has_decimal = value

    @property
    def primarykeys(self):
        return self._primary_keys

    @property
    def index(self):
        return self._index

    @index.setter
    def index(self, value):
        self._index = value


class TableIndex(object):
    def __init__(self, index_type, index_name, columns):
        self._index_type = index_type
        self._index_name = index_name
        self._index_columns = columns

    @property
    def indextype(self):
        return self._index_type

    @property
    def indexname(self):
        return self._index_name

    @property
    def indexcolumns(self):
        return self._index_columns


class TableColumn(object):
    def __init__(self, column_name, full_data_type, data_type, is_null, def_value, desc, primary=False):
        self._column_name = column_name
        self._full_data_type = full_data_type
        self._data_type = data_type
        self._is_null = is_null
        self._def_value = def_value
        self._desc = desc
        self._primary = primary

    @property
    def columnname(self):
        return self._column_name

    @property
    def fulldatatype(self):
        return self._full_data_type

    @property
    def datatype(self):
        return self._data_type

    @property
    def isnull(self):
        return self._is_null

    @property
    def defvalue(self):
        return self._def_value

    @property
    def desc(self):
        return self._desc

    @property
    def primary(self):
        return self._primary

    @primary.setter
    def primary(self, value):
        self._primary = value


TBF_VARCHAR = 1
TBF_SMALLINT = 2
TBF_INT = 3
TBF_BIGINT = 4
TBF_DATETIME = 5
TBF_DECIMAL = 6
TBF_TEXT = 7
TBF_LONGTEXT = 8
TBF_TINYINT = 9
DB_COLUMN_DATA_TYPE_MAP = {
    'varchar': TBF_VARCHAR,
    'smallint': TBF_SMALLINT,
    'int': TBF_INT,
    'bigint': TBF_BIGINT,
    'datetime': TBF_DATETIME,
    'decimal': TBF_DECIMAL,
    'text': TBF_TEXT,
    'longtext': TBF_LONGTEXT,
    'tinyint': TBF_TINYINT
}

DB_JAVA_PROPERTY_TYPE_MAP = {
    TBF_VARCHAR: 'String',
    TBF_SMALLINT: 'Integer',
    TBF_INT: 'Integer',
    TBF_BIGINT: 'Long',
    TBF_DATETIME: 'Date',
    TBF_DECIMAL: 'BigDecimal',
    TBF_TEXT: 'String',
    TBF_LONGTEXT: 'String',
    TBF_TINYINT: 'Integer'
}


def get_key(dict, value):
    key = [k for k, v in dict.items() if v == value]
    if len(key) == 1:
        return key[0]
    return None


if __name__ == '__main__':
    print(DB_JAVA_PROPERTY_TYPE_MAP[1])
