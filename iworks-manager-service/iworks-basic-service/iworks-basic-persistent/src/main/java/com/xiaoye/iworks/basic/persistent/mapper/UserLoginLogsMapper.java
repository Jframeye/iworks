package com.xiaoye.iworks.basic.persistent.mapper;
import com.xiaoye.iworks.basic.persistent.entity.UserLoginLogsDO;
import com.xiaoye.iworks.basic.persistent.entity.UserLoginLogsCriteria;
import com.xiaoye.iworks.persistent.provider.SQLProvider;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 功能描述: 实体类数据库接口【系统用户登录日志】
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
@Mapper
public interface UserLoginLogsMapper {
    @InsertProvider(method = "insert", type = SQLProvider.class)
    public int insert(UserLoginLogsDO entity);
    @InsertProvider(method = "insertSelective", type = SQLProvider.class)
    public int insertSelective(UserLoginLogsDO entity);
    @UpdateProvider(method = "update", type = SQLProvider.class)
    public int update(UserLoginLogsDO entity);
    @UpdateProvider(method = "updateSelective", type = SQLProvider.class)
    public int updateSelective(UserLoginLogsDO entity);
    @DeleteProvider(method = "deleteByCriteria", type = SQLProvider.class)
    public int delete(UserLoginLogsCriteria criteria);
    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public UserLoginLogsDO selectForOne(UserLoginLogsCriteria criteria);
    @SelectProvider(method = "selectByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public List<UserLoginLogsDO> selectForList(UserLoginLogsCriteria criteria);
    @SelectProvider(method = "countByCriteria", type = SQLProvider.class)
    @ResultMap("BasicResultMap")
    public int count(UserLoginLogsCriteria criteria);
}