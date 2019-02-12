package com.xiaoye.iworks.basic.core;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;
import com.xiaoye.iworks.basic.api.UserBasicInfoService;
import com.xiaoye.iworks.basic.api.constant.UserBasicInfoConstant;
import com.xiaoye.iworks.basic.api.dto.UserBasicInfoDto;
import com.xiaoye.iworks.basic.api.input.UserBasicInfoQueryInput;
import com.xiaoye.iworks.basic.core.exception.UserBasicInfoErrorCode;
import com.xiaoye.iworks.basic.persistent.entity.UserBasicInfoCriteria;
import com.xiaoye.iworks.basic.persistent.entity.UserBasicInfoDO;
import com.xiaoye.iworks.basic.persistent.mapper.UserBasicInfoMapper;
import com.xiaoye.iworks.common.exception.BizServiceException;
import com.xiaoye.iworks.persistent.constant.PersistentConstant;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.DateTimeUtils;
import com.xiaoye.iworks.utils.EncryptUtil;
import com.xiaoye.iworks.utils.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述: 实体类业务接口实现类【用户基本信息】
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
@Service
public class UserBasicInfoServiceImpl implements UserBasicInfoService {
    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private UserBasicInfoMapper UserBasicInfoMapper;
    @Override
    public PageResponse<UserBasicInfoDto> listUserBasicInfos(UserBasicInfoQueryInput queryInput) {
        PageResponse<UserBasicInfoDto> response = new PageResponse<>();
        try {
            UserBasicInfoCriteria criteria = new UserBasicInfoCriteria();
            // TODO do something here 

            if(queryInput.isPagenation()) {
                criteria.setPagination(queryInput.isPagenation());
                criteria.setOffset(queryInput.getOffset());
                criteria.setLimit(queryInput.getLimit());
                Integer total = UserBasicInfoMapper.count(criteria);
                response.getData().setOffset(queryInput.getOffset());
                response.getData().setLimit(queryInput.getLimit());
                response.getData().setTotal(total);
                if(total == 0) {
                    return response;
                }
            }
            List<UserBasicInfoDO> results = UserBasicInfoMapper.selectForList(criteria);
            List<UserBasicInfoDto> datas = CollectionUtils.transform(results, (entity) -> {
                UserBasicInfoDto dto = new UserBasicInfoDto();
                BeanUtils.copyProperties(entity, dto);
                return dto;
            });
            response.getData().setDatas(datas);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserBasicInfoErrorCode.DATA_QRY_ERROR);
            response.setMessage("用户基本信息数据列表查询异常");
            LOGGER.error(UserBasicInfoErrorCode.DATA_QRY_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<UserBasicInfoDto> findUserBasicInfo(UserBasicInfoQueryInput queryInput) {
        DataResponse<UserBasicInfoDto> response = new DataResponse<>();
        try {
            UserBasicInfoCriteria criteria = new UserBasicInfoCriteria();
            // TODO do something here 

            UserBasicInfoDO result = UserBasicInfoMapper.selectForOne(criteria);
            if(result == null) {
                if(queryInput.isCheckNull()) {
                    response.setRetcode(UserBasicInfoErrorCode.DATA_UNEXIST_ERROR);
                }
                response.setMessage("用户基本信息数据不存在");
                return response;
            }
            UserBasicInfoDto dto = new UserBasicInfoDto();
            BeanUtils.copyProperties(result, dto);
            response.setData(dto);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserBasicInfoErrorCode.DATA_QRY_ERROR);
            response.setMessage("用户基本信息数据详情查询异常");
            LOGGER.error(UserBasicInfoErrorCode.DATA_QRY_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Long> insertUserBasicInfo(UserBasicInfoDto dto) {
        DataResponse<Long> response = new DataResponse<>();
        try {
            UserBasicInfoDO UserBasicInfoDO = new UserBasicInfoDO();
            BeanUtils.copyProperties(dto, UserBasicInfoDO);
            UserBasicInfoDO.setState(UserBasicInfoConstant.State.NORMAL);
            UserBasicInfoDO.setLstate(PersistentConstant.Lstate.NORMAL);
            UserBasicInfoDO.setCreateTime(DateTimeUtils.currentDate());
            UserBasicInfoDO.setModifyTime(DateTimeUtils.currentDate());
            UserBasicInfoDO.setSign(EncryptUtil.MD5(UserBasicInfoDO.genSign()));
            UserBasicInfoMapper.insertSelective(UserBasicInfoDO);
            response.setData(UserBasicInfoDO.getPkid());
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserBasicInfoErrorCode.DATA_INSERT_ERROR);
            response.setMessage("用户基本信息数据新增异常");
            LOGGER.error(UserBasicInfoErrorCode.DATA_INSERT_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Integer> updateUserBasicInfo(UserBasicInfoDto dto) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            UserBasicInfoDO UserBasicInfoDO = new UserBasicInfoDO();
            BeanUtils.copyProperties(dto, UserBasicInfoDO);
            UserBasicInfoDO.setModifyTime(DateTimeUtils.currentDate());
            Integer result = UserBasicInfoMapper.insertSelective(UserBasicInfoDO);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserBasicInfoErrorCode.DATA_UPDATE_ERROR);
            response.setMessage("用户基本信息数据修改异常");
            LOGGER.error(UserBasicInfoErrorCode.DATA_UPDATE_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Integer> deleteUserBasicInfo(UserBasicInfoQueryInput queryInput) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            UserBasicInfoCriteria criteria = new UserBasicInfoCriteria();
            // TODO do something here 

            Integer result = UserBasicInfoMapper.delete(criteria);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserBasicInfoErrorCode.DATA_DELETE_ERROR);
            response.setMessage("用户基本信息数据删除异常");
            LOGGER.error(UserBasicInfoErrorCode.DATA_DELETE_ERROR, e);
        }
        return response;
    }
}