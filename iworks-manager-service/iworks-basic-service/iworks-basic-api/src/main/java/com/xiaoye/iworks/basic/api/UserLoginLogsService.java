package com.xiaoye.iworks.basic.api;
import com.xiaoye.iworks.basic.api.dto.UserLoginLogsDto;
import com.xiaoye.iworks.basic.api.input.UserLoginLogsQueryInput;
import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;

/**
 * 功能描述: 实体类业务接口【系统用户登录日志】
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
public interface UserLoginLogsService {
    /**
     * 功能描述: 列表查询 <br>
     *     查询条件：
     * @param: queryInput
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
    public PageResponse<UserLoginLogsDto> listUserLoginLogss(UserLoginLogsQueryInput queryInput);
    /**
     * 功能描述: 明细查询 <br>
     *     查询条件：
     * @param: queryInput
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
    public DataResponse<UserLoginLogsDto> findUserLoginLogs(UserLoginLogsQueryInput queryInput);
    /**
     * 功能描述: 数据新增 <br>
     * @param: 
     * @return: 返回数据主键
     * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
    public DataResponse<Long> insertUserLoginLogs(UserLoginLogsDto dto);
    /**
     * 功能描述: 数据修改 <br>
     * @param: 
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
    public DataResponse<Integer> updateUserLoginLogs(UserLoginLogsDto dto);
    /**
     * 功能描述: 数据删除 <br>
     *     查询条件：
     * @param: queryInput
     * @return: 
     * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
    public DataResponse<Integer> deleteUserLoginLogs(UserLoginLogsQueryInput queryInput);
}