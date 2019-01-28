package com.xiaoye.iworks.basic.api;
import com.xiaoye.iworks.basic.api.dto.UserLoginInfoDto;
import com.xiaoye.iworks.basic.api.input.UserLoginInfoQueryInput;
import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;

/**
 * 功能描述: 实体类业务接口【系统用户登录信息】
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
public interface UserLoginInfoService {
    /**
     * 功能描述: 列表查询 <br>
     *     查询条件：
     * @param: queryInput
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
    public PageResponse<UserLoginInfoDto> listUserLoginInfos(UserLoginInfoQueryInput queryInput);
    /**
     * 功能描述: 明细查询 <br>
     *     查询条件：
     * @param: queryInput
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
    public DataResponse<UserLoginInfoDto> findUserLoginInfo(UserLoginInfoQueryInput queryInput);
    /**
     * 功能描述: 数据新增 <br>
     * @param: 
     * @return: 返回数据主键
     * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
    public DataResponse<Long> insertUserLoginInfo(UserLoginInfoDto dto);
    /**
     * 功能描述: 数据修改 <br>
     * @param: 
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
    public DataResponse<Integer> updateUserLoginInfo(UserLoginInfoDto dto);
    /**
     * 功能描述: 数据删除 <br>
     *     查询条件：
     * @param: queryInput
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
    public DataResponse<Integer> deleteUserLoginInfo(UserLoginInfoQueryInput queryInput);
}