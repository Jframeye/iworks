package com.xiaoye.iworks.persistent.mapper;
import com.xiaoye.iworks.persistent.entity.TableNameDO
import com.xiaoye.iworks.persistent.entity.TableNameCriteria;
import com.xiaoye.iworks.persistent.provider.SQLProvider;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 功能描述: 实体类数据库接口【系统常量信息】
 * @auther: auto create by python 
 * @date: 2019-01-10 13:34:22 
 */
@Mapper
public interface TableNameMapper {
    @InsertProvider(method = "insert", type = SQLProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "pkid", keyColumn = "pkid")
    public int insert(TableNameDO entity);
    @InsertProvider(method = "insertSelective", type = SQLProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "pkid", keyColumn = "pkid")
    public int insertSelective(TableNameDO entity);
    @UpdateProvider(method = "update", type = SQLProvider.class)
    public int update(TableNameDO entity);
    @UpdateProvider(method = "updateSelective", type = SQLProvider.class)
    public int updateSelective(TableNameDO entity);
    @DeleteProvider(method = "deleteByCriteria", type = SQLProvider.class)
    public int delete(TableNameCriteria criteria);
    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public TableNameDO selectForOne(TableNameCriteria criteria);
    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public List<TableNameDO> selectForList(TableNameCriteria criteria);
    @SelectProvider(method = "countByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public int count(TableNameCriteria criteria);
}