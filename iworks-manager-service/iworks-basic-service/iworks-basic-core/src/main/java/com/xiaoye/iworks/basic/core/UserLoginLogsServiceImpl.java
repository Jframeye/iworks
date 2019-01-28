package com.xiaoye.iworks.basic.core;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;
import com.xiaoye.iworks.basic.api.UserLoginLogsService;
import com.xiaoye.iworks.basic.api.constant.UserLoginLogsConstant;
import com.xiaoye.iworks.basic.api.dto.UserLoginLogsDto;
import com.xiaoye.iworks.basic.api.input.UserLoginLogsQueryInput;
import com.xiaoye.iworks.basic.core.exception.UserLoginLogsErrorCode;
import com.xiaoye.iworks.basic.persistent.entity.UserLoginLogsCriteria;
import com.xiaoye.iworks.basic.persistent.entity.UserLoginLogsDO;
import com.xiaoye.iworks.basic.persistent.mapper.UserLoginLogsMapper;
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
 * 功能描述: 实体类业务接口实现类【系统用户登录日志】
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
@Service
public class UserLoginLogsServiceImpl implements UserLoginLogsService {
    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private UserLoginLogsMapper UserLoginLogsMapper;
    @Override
    public PageResponse<UserLoginLogsDto> listUserLoginLogss(UserLoginLogsQueryInput queryInput) {
        PageResponse<UserLoginLogsDto> response = new PageResponse<>();
        try {
            UserLoginLogsCriteria criteria = new UserLoginLogsCriteria();
            // TODO do something here 

            if(queryInput.isPagenation()) {
                criteria.setPagination(queryInput.isPagenation());
                criteria.setOffset(queryInput.getOffset());
                criteria.setLimit(queryInput.getLimit());
                Integer total = UserLoginLogsMapper.count(criteria);
                response.getData().setOffset(queryInput.getOffset());
                response.getData().setLimit(queryInput.getLimit());
                response.getData().setTotal(total);
                if(total == 0) {
                    return response;
                }
            }
            List<UserLoginLogsDO> results = UserLoginLogsMapper.selectForList(criteria);
            List<UserLoginLogsDto> datas = CollectionUtils.transform(results, (entity) -> {
                UserLoginLogsDto dto = new UserLoginLogsDto();
                BeanUtils.copyProperties(entity, dto);
                return dto;
            });
            response.getData().setDatas(datas);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserLoginLogsErrorCode.DATA_QRY_ERROR);
            response.setMessage("系统用户登录日志数据列表查询异常");
            LOGGER.error(UserLoginLogsErrorCode.DATA_QRY_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<UserLoginLogsDto> findUserLoginLogs(UserLoginLogsQueryInput queryInput) {
        DataResponse<UserLoginLogsDto> response = new DataResponse<>();
        try {
            UserLoginLogsCriteria criteria = new UserLoginLogsCriteria();
            // TODO do something here 

            UserLoginLogsDO result = UserLoginLogsMapper.selectForOne(criteria);
            if(result == null) {
                if(queryInput.isCheckNull()) {
                    response.setRetcode(UserLoginLogsErrorCode.DATA_UNEXIST_ERROR);
                }
                response.setMessage("系统用户登录日志数据不存在");
                return response;
            }
            UserLoginLogsDto dto = new UserLoginLogsDto();
            BeanUtils.copyProperties(result, dto);
            response.setData(dto);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserLoginLogsErrorCode.DATA_QRY_ERROR);
            response.setMessage("系统用户登录日志数据详情查询异常");
            LOGGER.error(UserLoginLogsErrorCode.DATA_QRY_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Long> insertUserLoginLogs(UserLoginLogsDto dto) {
        DataResponse<Long> response = new DataResponse<>();
        try {
            UserLoginLogsDO UserLoginLogsDO = new UserLoginLogsDO();
            BeanUtils.copyProperties(dto, UserLoginLogsDO);
            UserLoginLogsDO.setState(UserLoginLogsConstant.State.NORMAL);
            UserLoginLogsDO.setLstate(PersistentConstant.Lstate.NORMAL);
            UserLoginLogsDO.setCreateTime(DateTimeUtils.currentDate());
            UserLoginLogsDO.setModifyTime(DateTimeUtils.currentDate());
            UserLoginLogsMapper.insertSelective(UserLoginLogsDO);
            response.setData(UserLoginLogsDO.getPkid());
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserLoginLogsErrorCode.DATA_INSERT_ERROR);
            response.setMessage("系统用户登录日志数据新增异常");
            LOGGER.error(UserLoginLogsErrorCode.DATA_INSERT_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Integer> updateUserLoginLogs(UserLoginLogsDto dto) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            UserLoginLogsDO UserLoginLogsDO = new UserLoginLogsDO();
            BeanUtils.copyProperties(dto, UserLoginLogsDO);
            UserLoginLogsDO.setModifyTime(DateTimeUtils.currentDate());
            Integer result = UserLoginLogsMapper.insertSelective(UserLoginLogsDO);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserLoginLogsErrorCode.DATA_UPDATE_ERROR);
            response.setMessage("系统用户登录日志数据修改异常");
            LOGGER.error(UserLoginLogsErrorCode.DATA_UPDATE_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Integer> deleteUserLoginLogs(UserLoginLogsQueryInput queryInput) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            UserLoginLogsCriteria criteria = new UserLoginLogsCriteria();
            // TODO do something here 

            Integer result = UserLoginLogsMapper.delete(criteria);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(UserLoginLogsErrorCode.DATA_DELETE_ERROR);
            response.setMessage("系统用户登录日志数据删除异常");
            LOGGER.error(UserLoginLogsErrorCode.DATA_DELETE_ERROR, e);
        }
        return response;
    }
}