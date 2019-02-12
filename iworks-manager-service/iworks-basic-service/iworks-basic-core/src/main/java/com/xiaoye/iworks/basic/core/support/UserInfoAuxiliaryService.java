package com.xiaoye.iworks.basic.core.support;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.basic.api.constant.UserBasicInfoConstant;
import com.xiaoye.iworks.basic.api.constant.UserLoginInfoConstant;
import com.xiaoye.iworks.basic.api.dto.DictConstantDataDto;
import com.xiaoye.iworks.basic.api.dto.UserLoginInfoDto;
import com.xiaoye.iworks.basic.api.input.UserBasicInfoQueryInput;
import com.xiaoye.iworks.basic.api.vo.UserInfoAndPermissionInfo;
import com.xiaoye.iworks.basic.core.exception.UserLoginInfoErrorCode;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantDataCriteria;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantDataDO;
import com.xiaoye.iworks.basic.persistent.entity.UserBasicInfoDO;
import com.xiaoye.iworks.basic.persistent.entity.UserLoginInfoDO;
import com.xiaoye.iworks.basic.persistent.mapper.UserBasicInfoMapper;
import com.xiaoye.iworks.basic.persistent.mapper.UserLoginInfoMapper;
import com.xiaoye.iworks.persistent.constant.PersistentConstant;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.DateTimeUtils;
import com.xiaoye.iworks.utils.EncryptUtil;
import com.xiaoye.iworks.utils.StringUtils;
import com.xiaoye.iworks.utils.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述: 用户相关辅助类
 * @auther: yehl
 * @date: 2019/1/23 10:14
 */
@Service
public class UserInfoAuxiliaryService {
    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * 功能描述: 查找用户信息以及权限信息
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/2/12 18:04
     */
    public Response findUserInfoAndPermissions(UserBasicInfoQueryInput queryInput) {
        DataResponse<UserInfoAndPermissionInfo> response = new DataResponse<>();
        try {
            UserInfoAndPermissionInfo info = new UserInfoAndPermissionInfo();

            response.setData(info);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserLoginInfoErrorCode.DATA_INSERT_ERROR);
            response.setMessage("系统用户登录信息数据新增异常");
            LOGGER.error(UserLoginInfoErrorCode.DATA_INSERT_ERROR, e);
        }
        return response;
    }
}
