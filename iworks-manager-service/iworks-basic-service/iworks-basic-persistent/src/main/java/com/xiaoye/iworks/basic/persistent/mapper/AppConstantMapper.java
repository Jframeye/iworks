package com.xiaoye.iworks.basic.persistent.mapper;

import com.xiaoye.iworks.basic.persistent.entity.AppConstantDO;
import com.xiaoye.iworks.basic.persistent.entity.AppConstantCriteria;
import com.xiaoye.iworks.persistent.provider.SQLProvider;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 功能描述: 实体类数据库接口【系统常量数据】
 * @auther: auto create by python
 * @date: 2019-01-25 16:57:59
 */
@Mapper
public interface AppConstantMapper {

    @InsertProvider(method = "insert", type = SQLProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "pkid", keyColumn = "pkid")
    public int insert(AppConstantDO entity);

    @InsertProvider(method = "insertSelective", type = SQLProvider.class)
    public int insertSelective(AppConstantDO entity);

    @UpdateProvider(method = "update", type = SQLProvider.class)
    public int update(AppConstantDO entity);

    @UpdateProvider(method = "updateSelective", type = SQLProvider.class)
    public int updateSelective(AppConstantDO entity);

    @DeleteProvider(method = "deleteByCriteria", type = SQLProvider.class)
    public int delete(AppConstantCriteria criteria);

    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public AppConstantDO selectForOne(AppConstantCriteria criteria);

    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public List<AppConstantDO> selectForList(AppConstantCriteria criteria);

    @SelectProvider(method = "countByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public int count(AppConstantCriteria criteria);
}