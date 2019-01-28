package com.xiaoye.iworks.basic.persistent.mapper;
import com.xiaoye.iworks.basic.persistent.entity.UserBasicInfoDO;
import com.xiaoye.iworks.basic.persistent.entity.UserBasicInfoCriteria;
import com.xiaoye.iworks.persistent.provider.SQLProvider;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 功能描述: 实体类数据库接口【用户基本信息】
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
@Mapper
public interface UserBasicInfoMapper {
    @InsertProvider(method = "insert", type = SQLProvider.class)
    public int insert(UserBasicInfoDO entity);
    @InsertProvider(method = "insertSelective", type = SQLProvider.class)
    public int insertSelective(UserBasicInfoDO entity);
    @UpdateProvider(method = "update", type = SQLProvider.class)
    public int update(UserBasicInfoDO entity);
    @UpdateProvider(method = "updateSelective", type = SQLProvider.class)
    public int updateSelective(UserBasicInfoDO entity);
    @DeleteProvider(method = "deleteByCriteria", type = SQLProvider.class)
    public int delete(UserBasicInfoCriteria criteria);
    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public UserBasicInfoDO selectForOne(UserBasicInfoCriteria criteria);
    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public List<UserBasicInfoDO> selectForList(UserBasicInfoCriteria criteria);
    @SelectProvider(method = "countByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public int count(UserBasicInfoCriteria criteria);
}