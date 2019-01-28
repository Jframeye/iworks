package com.xiaoye.iworks.basic.api;
import com.xiaoye.iworks.basic.api.dto.UserBasicInfoDto;
import com.xiaoye.iworks.basic.api.input.UserBasicInfoQueryInput;
import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;

/**
 * 功能描述: 实体类业务接口【用户基本信息】
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
public interface UserBasicInfoService {
    /**
     * 功能描述: 列表查询 <br>
     *     查询条件：
     * @param: queryInput
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
    public PageResponse<UserBasicInfoDto> listUserBasicInfos(UserBasicInfoQueryInput queryInput);
    /**
     * 功能描述: 明细查询 <br>
     *     查询条件：
     * @param: queryInput
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
    public DataResponse<UserBasicInfoDto> findUserBasicInfo(UserBasicInfoQueryInput queryInput);
    /**
     * 功能描述: 数据新增 <br>
     * @param: 
     * @return: 返回数据主键
     * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
    public DataResponse<Long> insertUserBasicInfo(UserBasicInfoDto dto);
    /**
     * 功能描述: 数据修改 <br>
     * @param: 
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
    public DataResponse<Integer> updateUserBasicInfo(UserBasicInfoDto dto);
    /**
     * 功能描述: 数据删除 <br>
     *     查询条件：
     * @param: queryInput
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
    public DataResponse<Integer> deleteUserBasicInfo(UserBasicInfoQueryInput queryInput);
}