package com.xiaoye.iworks.basic.api;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;
import com.xiaoye.iworks.basic.api.dto.DictConstantDataDto;
import com.xiaoye.iworks.basic.api.dto.DictConstantDto;
import com.xiaoye.iworks.basic.api.input.DictConstantDataQueryInput;
import com.xiaoye.iworks.basic.api.input.DictConstantQueryInput;

/**
 * 功能描述: 实体类业务接口【字典分类】
 * @auther: auto create by python 
 * @date: 2019-01-21 12:55:16 
 */
public interface DictConstantService {

    /**
     * 功能描述: 列表查询 <br>
     *     查询条件：
     * @param: queryInput
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-21 12:55:16 
 */
    public PageResponse<DictConstantDto> listDictConstants(DictConstantQueryInput queryInput);

    /**
     * 功能描述: 列表查询 <br>
     *     查询条件：
     * @param: queryInput
     * @return:
     * @auther: auto create by python
     * @date: 2019-01-21 12:55:16
     */
    public PageResponse<DictConstantDataDto> listDictConstantDatas(DictConstantDataQueryInput queryInput);

    /**
     * 功能描述: 明细查询 <br>
     *     查询条件：
     * @param: queryInput
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-21 12:55:16 
 */
    public DataResponse<DictConstantDto> findDictConstant(DictConstantQueryInput queryInput);

    /**
     * 功能描述: 明细查询 <br>
     *     查询条件：
     * @param: queryInput
     * @return:
     * @auther: auto create by python
     * @date: 2019-01-21 12:55:16
     */
    public DataResponse<DictConstantDataDto> findDictConstantData(DictConstantDataQueryInput queryInput);

    /**
     * 功能描述: 数据新增 <br>
     * @param: 
     * @return: 返回数据主键
     * @auther: auto create by python 
 * @date: 2019-01-21 12:55:16 
 */
    public DataResponse<Long> insertDictConstant(DictConstantDto dto);

    /**
     * 功能描述: 数据新增 <br>
     * @param:
     * @return: 返回数据主键
     * @auther: auto create by python
     * @date: 2019-01-21 12:55:16
     */
    public DataResponse<Long> insertDictConstantData(DictConstantDataDto dto);

    /**
     * 功能描述: 数据修改 <br>
     * @param: 
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-21 12:55:16 
 */
    public DataResponse<Integer> updateDictConstant(DictConstantDto dto);

    /**
     * 功能描述: 数据修改 <br>
     * @param:
     * @return:
     * @auther: auto create by python
     * @date: 2019-01-21 12:55:16
     */
    public DataResponse<Integer> updateDictConstantData(DictConstantDataDto dto);

    /**
     * 功能描述: 数据删除 <br>
     *     查询条件：
     * @param: queryInput
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-21 12:55:16 
 */
    public DataResponse<Integer> deleteDictConstant(DictConstantQueryInput queryInput);

    /**
     * 功能描述: 数据删除 <br>
     *     查询条件：
     * @param: queryInput
     * @return:
     * @auther: auto create by python
     * @date: 2019-01-21 12:55:16
     */
    public DataResponse<Integer> deleteDictConstantData(DictConstantDataQueryInput queryInput);

}