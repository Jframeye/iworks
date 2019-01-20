package com.xiaoye.iworks.basic.core;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;
import com.xiaoye.iworks.basic.api.AppConstantService;
import com.xiaoye.iworks.basic.api.constant.AppConstant;
import com.xiaoye.iworks.basic.api.dto.AppConstantDto;
import com.xiaoye.iworks.basic.api.input.AppConstantQueryInput;
import com.xiaoye.iworks.basic.core.exception.AppConstantErrorCode;
import com.xiaoye.iworks.basic.persistent.entity.AppConstantCriteria;
import com.xiaoye.iworks.basic.persistent.entity.AppConstantDO;
import com.xiaoye.iworks.basic.persistent.mapper.AppConstantMapper;
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
 * 功能描述: 系统常量服务实现类
 * @auther: yehl
 * @date: 2019/1/4 13:08
 */
@Service
public class AppConstantServiceImpl implements AppConstantService {
    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private AppConstantMapper appConstantMapper;

    @Override
    public PageResponse<AppConstantDto> listAppConstant(AppConstantQueryInput queryInput) {
        PageResponse<AppConstantDto> response = new PageResponse<>();
        try {
            AppConstantCriteria criteria = new AppConstantCriteria();
            criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andConstantKeyLike(queryInput.getConstantKey())
                    .andConstantValueLike(queryInput.getConstantValue())
                    .andStateEqualTo(queryInput.getState())
                    .andCreateByLike(queryInput.getCreateBy())
                    .andModifyByLike(queryInput.getModifyBy());
            criteria.setPagination(queryInput.isPagenation());
            criteria.setOffset(queryInput.getOffset());
            criteria.setLimit(queryInput.getLimit());
            if(queryInput.isPagenation()) {
                Integer total = appConstantMapper.count(criteria);
                response.getData().setOffset(queryInput.getOffset());
                response.getData().setLimit(queryInput.getLimit());
                response.getData().setTotal(total);
                if(total == 0) {
                    return response;
                }
            }
            List<AppConstantDO> results = appConstantMapper.selectForList(criteria);
            List<AppConstantDto> dataList = CollectionUtils.transform(results, (entity) -> {
                AppConstantDto dto = new AppConstantDto();
                BeanUtils.copyProperties(entity, dto);
                return dto;
            });
            response.getData().setDatas(dataList);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(AppConstantErrorCode.DATA_QRY_ERROR);
            response.setMessage("系统常量数据列表查询异常");
            LOGGER.error(AppConstantErrorCode.DATA_QRY_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Integer> countAppConstant(AppConstantQueryInput queryInput) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            AppConstantCriteria criteria = new AppConstantCriteria();
            criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andConstantKeyLike(queryInput.getConstantKey());
            Integer result = appConstantMapper.count(criteria);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(AppConstantErrorCode.DATA_COUNT_ERROR);
            response.setMessage("系统常量数据统计异常");
            LOGGER.error(AppConstantErrorCode.DATA_COUNT_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<AppConstantDto> findAppConstant(AppConstantQueryInput queryInput) {
        DataResponse<AppConstantDto> response = new DataResponse<>();
        try {
            AppConstantCriteria criteria = new AppConstantCriteria();
            criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andPkidEqualTo(queryInput.getPkid())
                    .andConstantKeyEqualTo(queryInput.getConstantKey());
            AppConstantDO result = appConstantMapper.selectForOne(criteria);
            AppConstantDto dto = new AppConstantDto();
            BeanUtils.copyProperties(result, dto);
            response.setData(dto);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(AppConstantErrorCode.DATA_QRY_ERROR);
            response.setMessage("系统常量数据详情查询异常");
            LOGGER.error(AppConstantErrorCode.DATA_QRY_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Integer> deleteAppConstant(AppConstantQueryInput queryInput) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            AppConstantCriteria criteria = new AppConstantCriteria();
            criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andPkidEqualTo(queryInput.getPkid())
                    .andPkidIn(queryInput.getPkids())
                    .andConstantKeyEqualTo(queryInput.getConstantKey())
                    .andConstantKeyIn(queryInput.getConstantKeys());
            Integer result = appConstantMapper.delete(criteria);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(AppConstantErrorCode.DATA_DELETE_ERROR);
            response.setMessage("系统常量数据删除异常");
            LOGGER.error(AppConstantErrorCode.DATA_DELETE_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Integer> updateAppConstant(AppConstantDto dto) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            AppConstantCriteria criteria = new AppConstantCriteria();
            criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andPkidEqualTo(dto.getPkid())
                    .andConstantKeyEqualTo(dto.getConstantKey());
            AppConstantDO appConstantDO = appConstantMapper.selectForOne(criteria);
            if(appConstantDO == null) {
                throw new BizServiceException(AppConstantErrorCode.DATA_UNEXIST_ERROR, "待更新数据不存在");
            }
            BeanUtils.copyProperties(dto, appConstantDO);
            appConstantDO.setModifyTime(DateTimeUtils.currentDate());
            Integer result = appConstantMapper.updateSelective(appConstantDO);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(AppConstantErrorCode.DATA_UPDATE_ERROR);
            response.setMessage("系统常量数据更新异常");
            LOGGER.error(AppConstantErrorCode.DATA_UPDATE_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Long> insertAppConstant(AppConstantDto dto) {
        DataResponse<Long> response = new DataResponse<>();
        try {
            AppConstantCriteria criteria = new AppConstantCriteria();
            criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andConstantKeyEqualTo(dto.getConstantKey());
            AppConstantDO appConstantDO = appConstantMapper.selectForOne(criteria);
            if(appConstantDO != null) {
                throw new BizServiceException(AppConstantErrorCode.DATA_EXIST_ERROR, String.format("待新增数据常量编码【%s】已存在", dto.getConstantKey()));
            }
            appConstantDO = new AppConstantDO();
            BeanUtils.copyProperties(dto, appConstantDO);
            appConstantDO.setState(AppConstant.State.NORMAL);
            appConstantDO.setLstate(PersistentConstant.Lstate.NORMAL);
            appConstantDO.setCreateTime(DateTimeUtils.currentDate());
            appConstantDO.setModifyTime(DateTimeUtils.currentDate());
            appConstantMapper.insertSelective(appConstantDO);
            response.setData(appConstantDO.getPkid());
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(AppConstantErrorCode.DATA_INSERT_ERROR);
            response.setMessage("系统常量数据新增异常");
            LOGGER.error(AppConstantErrorCode.DATA_INSERT_ERROR, e);
        }
        return response;
    }
}
