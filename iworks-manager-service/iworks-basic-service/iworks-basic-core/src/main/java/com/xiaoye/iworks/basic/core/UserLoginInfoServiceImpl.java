package com.xiaoye.iworks.basic.core;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;
import com.xiaoye.iworks.basic.api.UserLoginInfoService;
import com.xiaoye.iworks.basic.api.constant.UserLoginInfoConstant;
import com.xiaoye.iworks.basic.api.dto.UserLoginInfoDto;
import com.xiaoye.iworks.basic.api.input.UserLoginInfoQueryInput;
import com.xiaoye.iworks.basic.core.exception.UserLoginInfoErrorCode;
import com.xiaoye.iworks.basic.persistent.entity.UserLoginInfoCriteria;
import com.xiaoye.iworks.basic.persistent.entity.UserLoginInfoDO;
import com.xiaoye.iworks.basic.persistent.mapper.UserLoginInfoMapper;
import com.xiaoye.iworks.common.exception.BizServiceException;
import com.xiaoye.iworks.persistent.constant.PersistentConstant;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.DateTimeUtils;
import com.xiaoye.iworks.utils.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述: 实体类业务接口实现类【系统用户登录信息】
 * @auther: auto create by python 
 * @date: 2019-01-24 18:03:37 
 */
@Service
public class UserLoginInfoServiceImpl implements UserLoginInfoService {
    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private UserLoginInfoMapper UserLoginInfoMapper;
    @Override
    public PageResponse<UserLoginInfoDto> listUserLoginInfos(UserLoginInfoQueryInput queryInput) {
        PageResponse<UserLoginInfoDto> response = new PageResponse<>();
        try {
            UserLoginInfoCriteria criteria = new UserLoginInfoCriteria();
            // TODO do something here 

            if(queryInput.isPagenation()) {
                criteria.setPagination(queryInput.isPagenation());
                criteria.setOffset(queryInput.getOffset());
                criteria.setLimit(queryInput.getLimit());
                Integer total = UserLoginInfoMapper.count(criteria);
                response.getData().setOffset(queryInput.getOffset());
                response.getData().setLimit(queryInput.getLimit());
                response.getData().setTotal(total);
                if(total == 0) {
                    return response;
                }
            }
            List<UserLoginInfoDO> results = UserLoginInfoMapper.selectForList(criteria);
            List<UserLoginInfoDto> datas = CollectionUtils.transform(results, (entity) -> {
                UserLoginInfoDto dto = new UserLoginInfoDto();
                BeanUtils.copyProperties(entity, dto);
                return dto;
            });
            response.getData().setDatas(datas);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserLoginInfoErrorCode.DATA_QRY_ERROR);
            response.setMessage("系统用户登录信息数据列表查询异常");
            LOGGER.error(UserLoginInfoErrorCode.DATA_QRY_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<UserLoginInfoDto> findUserLoginInfo(UserLoginInfoQueryInput queryInput) {
        DataResponse<UserLoginInfoDto> response = new DataResponse<>();
        try {
            UserLoginInfoCriteria criteria = new UserLoginInfoCriteria();
            // TODO do something here 

            UserLoginInfoDO result = UserLoginInfoMapper.selectForOne(criteria);
            if(result == null) {
                if(queryInput.isCheckNull()) {
                    response.setRetcode(UserLoginInfoErrorCode.DATA_UNEXIST_ERROR);
                }
                response.setMessage("系统用户登录信息数据详情不存在");
                return response;
            }
            UserLoginInfoDto dto = new UserLoginInfoDto();
            BeanUtils.copyProperties(result, dto);
            response.setData(dto);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserLoginInfoErrorCode.DATA_QRY_ERROR);
            response.setMessage("系统用户登录信息数据详情查询异常");
            LOGGER.error(UserLoginInfoErrorCode.DATA_QRY_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Long> insertUserLoginInfo(UserLoginInfoDto dto) {
        DataResponse<Long> response = new DataResponse<>();
        try {
            UserLoginInfoDO UserLoginInfoDO = new UserLoginInfoDO();
            BeanUtils.copyProperties(dto, UserLoginInfoDO);
            UserLoginInfoDO.setState(UserLoginInfoConstant.State.NORMAL);
            UserLoginInfoDO.setLstate(PersistentConstant.Lstate.NORMAL);
            UserLoginInfoDO.setCreateTime(DateTimeUtils.currentDate());
            UserLoginInfoDO.setModifyTime(DateTimeUtils.currentDate());
            UserLoginInfoMapper.insertSelective(UserLoginInfoDO);
            response.setData(UserLoginInfoDO.getPkid());
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

    @Override
    public DataResponse<Integer> updateUserLoginInfo(UserLoginInfoDto dto) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            UserLoginInfoDO UserLoginInfoDO = new UserLoginInfoDO();
            BeanUtils.copyProperties(dto, UserLoginInfoDO);
            UserLoginInfoDO.setModifyTime(DateTimeUtils.currentDate());
            Integer result = UserLoginInfoMapper.insertSelective(UserLoginInfoDO);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserLoginInfoErrorCode.DATA_UPDATE_ERROR);
            response.setMessage("系统用户登录信息数据修改异常");
            LOGGER.error(UserLoginInfoErrorCode.DATA_UPDATE_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Integer> deleteUserLoginInfo(UserLoginInfoQueryInput queryInput) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            UserLoginInfoCriteria criteria = new UserLoginInfoCriteria();
            // TODO do something here 

            Integer result = UserLoginInfoMapper.delete(criteria);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserLoginInfoErrorCode.DATA_DELETE_ERROR);
            response.setMessage("系统用户登录信息数据删除异常");
            LOGGER.error(UserLoginInfoErrorCode.DATA_DELETE_ERROR, e);
        }
        return response;
    }
}