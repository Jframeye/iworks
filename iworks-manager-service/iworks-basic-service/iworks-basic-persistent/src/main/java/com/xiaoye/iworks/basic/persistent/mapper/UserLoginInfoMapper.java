package com.xiaoye.iworks.basic.persistent.mapper;

import com.xiaoye.iworks.basic.persistent.entity.UserLoginInfoDO;
import com.xiaoye.iworks.basic.persistent.entity.UserLoginInfoCriteria;
import com.xiaoye.iworks.persistent.provider.SQLProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * 功能描述: 实体类数据库接口【系统用户登录信息】
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
@Mapper
public interface UserLoginInfoMapper {

    @InsertProvider(method = "insert", type = SQLProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "pkid", keyColumn = "pkid")
    public int insert(UserLoginInfoDO entity);

    @InsertProvider(method = "insertSelective", type = SQLProvider.class)
    @SelectKey(statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS pkid", keyProperty = "pkid", before = false, resultType = Long.class)
    public int insertSelective(UserLoginInfoDO entity);

    @UpdateProvider(method = "update", type = SQLProvider.class)
    public int update(UserLoginInfoDO entity);

    @UpdateProvider(method = "updateSelective", type = SQLProvider.class)
    public int updateSelective(UserLoginInfoDO entity);

    @DeleteProvider(method = "deleteByCriteria", type = SQLProvider.class)
    public int delete(UserLoginInfoCriteria criteria);

    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public UserLoginInfoDO selectForOne(UserLoginInfoCriteria criteria);

    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public List<UserLoginInfoDO> selectForList(UserLoginInfoCriteria criteria);

    @SelectProvider(method = "countByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public int count(UserLoginInfoCriteria criteria);
}