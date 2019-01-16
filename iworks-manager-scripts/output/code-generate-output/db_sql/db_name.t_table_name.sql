/**
 ** 系统常量信息
**/
CREATE DATABASE IF NOT EXISTS db_name;
USE db_name;
CREATE TABLE IF NOT EXISTS `t_table_name` (
    `pkid` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键[auto_increment]',
    `column_1` varchar(64) NOT NULL COMMENT '字段1',
    `column_2` varchar(64) NOT NULL COMMENT '字段2',
    `column_3` varchar(64) COMMENT '字段3',
    `sign` varchar(64) DEFAULT '股乖乖' COMMENT '数据签名',
    `state` smallint NOT NULL DEFAULT '1' COMMENT '状态[1-可用；2-禁用]',
    `lstate` smallint NOT NULL DEFAULT '1' COMMENT '逻辑状态[1-正常；2-删除]',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `modify_time` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`pkid`,`xxx`)
) ENGINE=InnoDB CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统常量信息';
