package com.xiaoye.iworks.basic.api;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;
import com.xiaoye.iworks.basic.api.dto.AppConstantDto;
import com.xiaoye.iworks.basic.api.input.AppConstantQueryInput;

/**
 * 功能描述: 系统常量服务类(对外服务)
 * @auther: yehl
 * @date: 2019/1/4 12:37
 */
public interface AppConstantService {

    /**
     * 功能描述: 列表查询 <br>
     *     查询条件：
     *     constant_key[like]
     *     constant_value[like]
     *     constant_desc[like]
     *     create_by[like]
     *     modify_by[like]
     * @param: queryInput
     * @return: 
     * @auther: yehl
     * @date: 2019/1/4 12:59
     */
    public PageResponse<AppConstantDto> listAppConstant(AppConstantQueryInput queryInput);

    /**
     * 功能描述: 明细查询 <br>
     *     查询条件：
     *     pkid[equal]
     *     constant_key[equal]
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/4 13:01
     */
    public DataResponse<AppConstantDto> findAppConstant(AppConstantQueryInput queryInput);

    /**
     * 功能描述: 数据删除 <br>
     *     删除条件：
     *     pkid[equal]
     *     pkids[in list]
     *     constant_key[equal]
     *     constant_keys[in list]
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/4 13:03
     */
    public DataResponse<Integer> deleteAppConstant(AppConstantQueryInput queryInput);

    /**
     * 功能描述: 数据更新
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/4 13:03
     */
    public DataResponse<Integer> updateAppConstant(AppConstantDto dto);

    /**
     * 功能描述: 数据新增
     * @param:
     * @return: 返回数据主键
     * @auther: yehl
     * @date: 2019/1/4 13:04
     */
    public DataResponse<Long> insertAppConstant(AppConstantDto dto);
}
