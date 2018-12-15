package com.xiaoye.iworks.basic.api.input.generate;

import com.xiaoye.iworks.core.api.input.Input;

/**
 * @author yehl
 * @date 2018/11/1 16:11
 */
public class TableQueryInput extends Input {
    private static final long serialVersionUID = 1941054477231884743L;

    /** 主键 **/
    private Long pkid;

    /** 数据库名称 **/
    private String data_base;

    /** 数据表名称 **/
    private String table_name;

    /** 数据表注释 **/
    private String comments;

    /** 数据表状态 **/
    private Integer state;

    public Long getPkid() {
        return pkid;
    }

    public void setPkid(Long pkid) {
        this.pkid = pkid;
    }

    public String getData_base() {
        return data_base;
    }

    public void setData_base(String data_base) {
        this.data_base = data_base;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
