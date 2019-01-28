package com.xiaoye.iworks.basic.persistent.mapper;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantDO;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantCriteria;
import com.xiaoye.iworks.persistent.provider.SQLProvider;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 功能描述: 实体类数据库接口【字典分类】
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
@Mapper
public interface DictConstantMapper {
    @InsertProvider(method = "insert", type = SQLProvider.class)
    public int insert(DictConstantDO entity);
    @InsertProvider(method = "insertSelective", type = SQLProvider.class)
    public int insertSelective(DictConstantDO entity);
    @UpdateProvider(method = "update", type = SQLProvider.class)
    public int update(DictConstantDO entity);
    @UpdateProvider(method = "updateSelective", type = SQLProvider.class)
    public int updateSelective(DictConstantDO entity);
    @DeleteProvider(method = "deleteByCriteria", type = SQLProvider.class)
    public int delete(DictConstantCriteria criteria);
    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public DictConstantDO selectForOne(DictConstantCriteria criteria);
    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public List<DictConstantDO> selectForList(DictConstantCriteria criteria);
    @SelectProvider(method = "countByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public int count(DictConstantCriteria criteria);
}