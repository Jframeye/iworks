package com.xiaoye.iworks.basic.persistent.mapper;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantDataDO;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantDataCriteria;
import com.xiaoye.iworks.persistent.provider.SQLProvider;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 功能描述: 实体类数据库接口【字典分类数据】
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
@Mapper
public interface DictConstantDataMapper {
    @InsertProvider(method = "insert", type = SQLProvider.class)
    public int insert(DictConstantDataDO entity);
    @InsertProvider(method = "insertSelective", type = SQLProvider.class)
    public int insertSelective(DictConstantDataDO entity);
    @UpdateProvider(method = "update", type = SQLProvider.class)
    public int update(DictConstantDataDO entity);
    @UpdateProvider(method = "updateSelective", type = SQLProvider.class)
    public int updateSelective(DictConstantDataDO entity);
    @DeleteProvider(method = "deleteByCriteria", type = SQLProvider.class)
    public int delete(DictConstantDataCriteria criteria);
    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public DictConstantDataDO selectForOne(DictConstantDataCriteria criteria);
    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public List<DictConstantDataDO> selectForList(DictConstantDataCriteria criteria);
    @SelectProvider(method = "countByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public int count(DictConstantDataCriteria criteria);
}