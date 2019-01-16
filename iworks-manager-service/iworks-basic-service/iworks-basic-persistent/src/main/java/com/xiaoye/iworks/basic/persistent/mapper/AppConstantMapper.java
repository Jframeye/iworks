/**
 *
 **/
package com.xiaoye.iworks.basic.persistent.mapper;

import com.xiaoye.iworks.basic.persistent.entity.AppConstantDO;
import com.xiaoye.iworks.basic.persistent.entity.AppConstantCriteria;
import com.xiaoye.iworks.persistent.provider.SQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yehl
 * @date 2018年6月28日 下午7:51:30
 */
@Mapper
public interface AppConstantMapper {

    @InsertProvider(method = "insert", type = SQLProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "pkid", keyColumn = "pkid")
    public int insert(AppConstantDO entity);

    @InsertProvider(method = "insertSelective", type = SQLProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "pkid", keyColumn = "pkid")
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
    public int count(AppConstantCriteria criteria);
}
