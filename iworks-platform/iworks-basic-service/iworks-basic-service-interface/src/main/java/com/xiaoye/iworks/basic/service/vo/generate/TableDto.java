package com.xiaoye.iworks.basic.service.vo.generate;

import java.io.Serializable;

/**
 * 数据传输类
 * @author yehl
 * @date 2018/11/1 19:31
 */
public class TableDto implements Serializable {
    private static final long serialVersionUID = -8516651317131944076L;

    private Long pkid;
    private String dataBase;
    private String tableName;
    private String comments;
    private Integer state;
    private String createTime;
    private String updateTime;

    public Long getPkid() {
        return pkid;
    }

    public void setPkid(Long pkid) {
        this.pkid = pkid;
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
