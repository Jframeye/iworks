package com.xiaoye.iworks.basic.core;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;
import com.xiaoye.iworks.basic.api.DictConstantService;
import com.xiaoye.iworks.basic.api.constant.DictConstant;
import com.xiaoye.iworks.basic.api.dto.DictConstantDataDto;
import com.xiaoye.iworks.basic.api.dto.DictConstantDto;
import com.xiaoye.iworks.basic.api.input.DictConstantDataQueryInput;
import com.xiaoye.iworks.basic.api.input.DictConstantQueryInput;
import com.xiaoye.iworks.basic.core.exception.DictConstantErrorCode;
import com.xiaoye.iworks.basic.core.support.DictConstantAuxiliaryService;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantCriteria;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantDO;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantDataCriteria;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantDataDO;
import com.xiaoye.iworks.basic.persistent.mapper.DictConstantDataMapper;
import com.xiaoye.iworks.basic.persistent.mapper.DictConstantMapper;
import com.xiaoye.iworks.common.exception.BizServiceException;
import com.xiaoye.iworks.persistent.constant.PersistentConstant;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.DateTimeUtils;
import com.xiaoye.iworks.utils.StringUtils;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;
import com.xiaoye.iworks.utils.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述: 实体类业务接口实现类【字典分类】
 * @auther: auto create by python 
 * @date: 2019-01-21 12:55:16 
 */
@Service
public class DictConstantServiceImpl implements DictConstantService {
    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private DictConstantMapper dictConstantMapper;
    @Resource
    private DictConstantDataMapper dictConstantDataMapper;
    @Autowired
    private DictConstantAuxiliaryService auxiliaryService;

    @Override
    public PageResponse<DictConstantDto> listDictConstants(DictConstantQueryInput input) {
        PageResponse<DictConstantDto> response = new PageResponse<>();
        try {
            // 查询字典分类
            DictConstantCriteria criteria = new DictConstantCriteria();
            criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andDictNameLike(input.getDictName())
                    .andDictCodeLike(input.getDictCode())
                    .andStateEqualTo(input.getState())
                    .andCreateByLike(input.getCreateBy())
                    .andModifyByLike(input.getModifyBy());
            if(input.isPagenation()) {
                criteria.setPagination(input.isPagenation());
                criteria.setOffset(input.getOffset());
                criteria.setLimit(input.getLimit());
                Integer total = dictConstantMapper.count(criteria);
                response.getData().setOffset(input.getOffset());
                response.getData().setLimit(input.getLimit());
                response.getData().setTotal(total);
                if(total == 0) {
                    return response;
                }
            }
            List<DictConstantDO> results = dictConstantMapper.selectForList(criteria);
            List<DictConstantDto> datas = CollectionUtils.transform(results, (entity) -> {
                DictConstantDto dto = new DictConstantDto();
                BeanUtils.copyProperties(entity, dto);
                if(DictConstant.LIST_DICT_DATA.equals(input.getListDictData())) {
                    List<DictConstantDataDto> dictDataList = auxiliaryService.listDictDatasByDictCode(dto);
                    dto.setDictDataList(dictDataList);
                }
                return dto;
            });
            response.getData().setDatas(datas);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(DictConstantErrorCode.DATA_QRY_ERROR);
            response.setMessage("字典常量数据列表查询异常");
            LOGGER.error(DictConstantErrorCode.DATA_QRY_ERROR, e);
        }
        return response;
    }

    @Override
    public PageResponse<DictConstantDataDto> listDictConstantDatas(DictConstantDataQueryInput input) {
        PageResponse<DictConstantDataDto> response = new PageResponse<>();
        try {
            // 查询字典分类
            DictConstantDataCriteria dataCriteria = new DictConstantDataCriteria();
            dataCriteria.createCriteriaInternal()
                    .andDictCodeEqualTo(input.getDictCode())
                    .andDictKeyLike(input.getDictKey());
            List<DictConstantDataDO> dataResults = dictConstantDataMapper.selectForList(dataCriteria);
            List<DictConstantDataDto> datas = CollectionUtils.transform(dataResults, (data) -> {
                DictConstantDataDto dataDto = new DictConstantDataDto();
                BeanUtils.copyProperties(data, dataDto);
                return dataDto;
            });
            response.getData().setDatas(datas);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(DictConstantErrorCode.DATA_QRY_ERROR);
            response.setMessage("字典常量分类数据列表查询异常");
            LOGGER.error(DictConstantErrorCode.DATA_QRY_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<DictConstantDto> findDictConstant(DictConstantQueryInput input) {
        DataResponse<DictConstantDto> response = new DataResponse<>();
        try {
            if(input.getPkid() == null && StringUtils.isBlank(input.getDictCode())) {
                throw new BizServiceException(ServiceErrorCode.PARAM_ERROR, "必要参数未传");
            }
            DictConstantCriteria criteria = new DictConstantCriteria();
            criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andPkidEqualTo(input.getPkid())
                    .andDictCodeEqualTo(input.getDictCode());
            DictConstantDO result = dictConstantMapper.selectForOne(criteria);
            if(result == null) {
                if(input.isCheckNull()) {
                    response.setRetcode(DictConstantErrorCode.DATA_UNEXIST_ERROR);
                }
                response.setMessage("字典常量数据不存在");
                return response;
            }
            DictConstantDto dto = new DictConstantDto();
            BeanUtils.copyProperties(result, dto);
            if(DictConstant.LIST_DICT_DATA.equals(input.getListDictData())) {
                List<DictConstantDataDto> dictDataList = auxiliaryService.listDictDatasByDictCode(dto);
                dto.setDictDataList(dictDataList);
            }
            response.setData(dto);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(DictConstantErrorCode.DATA_QRY_ERROR);
            response.setMessage("字典常量数据详情查询异常");
            LOGGER.error(DictConstantErrorCode.DATA_QRY_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<DictConstantDataDto> findDictConstantData(DictConstantDataQueryInput input) {
        DataResponse<DictConstantDataDto> response = new DataResponse<>();
        try {
            if(input.getPkid() == null && StringUtils.hasBlankString(input.getDictCode(), input.getDictKey())) {
                throw new BizServiceException(ServiceErrorCode.PARAM_ERROR, "必要参数未传");
            }
            DictConstantDataCriteria dataCriteria = new DictConstantDataCriteria();
            dataCriteria.createCriteriaInternal()
                    .andPkidEqualTo(input.getPkid())
                    .andDictCodeEqualTo(input.getDictCode())
                    .andDictKeyEqualTo(input.getDictKey());
            DictConstantDataDO dictConstantDataDO = dictConstantDataMapper.selectForOne(dataCriteria);
            if(dictConstantDataDO == null) {
                if(input.isCheckNull()) {
                    response.setRetcode(DictConstantErrorCode.DATA_UNEXIST_ERROR);
                }
                response.setMessage("字典常量分类数据不存在");
                return response;
            }
            DictConstantDataDto dataDto = new DictConstantDataDto();
            BeanUtils.copyProperties(dictConstantDataDO, dataDto);
            response.setData(dataDto);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(DictConstantErrorCode.DATA_QRY_ERROR);
            response.setMessage("字典常量分类数据详情查询异常");
            LOGGER.error(DictConstantErrorCode.DATA_QRY_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Long> insertDictConstant(DictConstantDto dto) {
        DataResponse<Long> response = new DataResponse<>();
        try {
            DictConstantCriteria criteria = new DictConstantCriteria();
            criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andDictCodeEqualTo(dto.getDictCode());
            DictConstantDO dictConstantDO = dictConstantMapper.selectForOne(criteria);
            if(dictConstantDO != null) {
                throw new BizServiceException(DictConstantErrorCode.DATA_EXIST_ERROR, String.format("待新增数据字典编码【%s】已存在", dto.getDictCode()));
            }
            dictConstantDO = new DictConstantDO();
            BeanUtils.copyProperties(dto, dictConstantDO);
            dictConstantDO.setState(DictConstant.State.NORMAL);
            dictConstantDO.setLstate(PersistentConstant.Lstate.NORMAL);
            dictConstantDO.setCreateTime(DateTimeUtils.currentDate());
            dictConstantDO.setModifyTime(DateTimeUtils.currentDate());
            dictConstantMapper.insertSelective(dictConstantDO);
            response.setData(dictConstantDO.getPkid());
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(DictConstantErrorCode.DATA_INSERT_ERROR);
            response.setMessage("字典常量数据新增异常");
            LOGGER.error(DictConstantErrorCode.DATA_INSERT_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Long> insertDictConstantData(DictConstantDataDto dto) {
        DataResponse<Long> response = new DataResponse<>();
        try {
            if(StringUtils.isBlank(dto.getDictCode())) {
                throw new BizServiceException(ServiceErrorCode.PARAM_ERROR, "必要参数未传");
            }
            // 查询字典常量是否存在
            DictConstantCriteria criteria = new DictConstantCriteria();
            criteria.createCriteriaInternal().andDictCodeEqualTo(dto.getDictCode());
            DictConstantDO dictConstantDO = dictConstantMapper.selectForOne(criteria);
            if(dictConstantDO == null) {
                throw new BizServiceException(DictConstantErrorCode.DATA_UNEXIST_ERROR, String.format("字典常量数据不存在，字典编码【%s】", dto.getDictCode()));
            }
            DictConstantDataCriteria dataCriteria = new DictConstantDataCriteria();
            dataCriteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andDictCodeEqualTo(dto.getDictCode())
                    .andDictKeyEqualTo(dto.getDictKey());
            DictConstantDataDO dictConstantDataDO = dictConstantDataMapper.selectForOne(dataCriteria);
            if(dictConstantDataDO != null) {
                throw new BizServiceException(DictConstantErrorCode.DATA_EXIST_ERROR, String.format("待新增数据字典编码-标签【%s - %s】已存在", dto.getDictCode(), dto.getDictKey()));
            }
            dictConstantDataDO = new DictConstantDataDO();
            BeanUtils.copyProperties(dto, dictConstantDataDO);
            dictConstantDataDO.setState(DictConstant.State.NORMAL);
            dictConstantDataDO.setLstate(PersistentConstant.Lstate.NORMAL);
            dictConstantDataDO.setCreateTime(DateTimeUtils.currentDate());
            dictConstantDataDO.setModifyTime(DateTimeUtils.currentDate());
            dictConstantDataMapper.insertSelective(dictConstantDataDO);
            response.setData(dictConstantDataDO.getPkid());
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(DictConstantErrorCode.DATA_QRY_ERROR);
            response.setMessage("字典常量数据新增异常");
            LOGGER.error(DictConstantErrorCode.DATA_INSERT_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Integer> updateDictConstant(DictConstantDto dto) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            if( dto.getPkid() == null && StringUtils.isBlank(dto.getDictCode())) {
                throw new BizServiceException(ServiceErrorCode.PARAM_ERROR, "必要参数未传");
            }
            DictConstantCriteria criteria = new DictConstantCriteria();
            criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andPkidEqualTo(dto.getPkid())
                    .andDictDescEqualTo(dto.getDictCode());
            DictConstantDO dictConstantDO = dictConstantMapper.selectForOne(criteria);
            if(dictConstantDO == null) {
                throw new BizServiceException(DictConstantErrorCode.DATA_UNEXIST_ERROR, "待更新数据不存在");
            }
            BeanUtils.copyProperties(dto, dictConstantDO);
            dictConstantDO.setDictCode(null); // 字典编码不做修改
            dictConstantDO.setModifyTime(DateTimeUtils.currentDate());
            Integer result = dictConstantMapper.updateSelective(dictConstantDO);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(DictConstantErrorCode.DATA_UPDATE_ERROR);
            response.setMessage("字典常量数据更新异常");
            LOGGER.error(DictConstantErrorCode.DATA_UPDATE_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Integer> updateDictConstantData(DictConstantDataDto dto) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            if(dto.getPkid() == null && (StringUtils.hasBlankString(dto.getDictCode(), dto.getDictKey()))) {
                throw new BizServiceException(ServiceErrorCode.PARAM_ERROR, "必要参数未传");
            }
            DictConstantDataCriteria criteria = new DictConstantDataCriteria();
            criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andPkidEqualTo(dto.getPkid())
                    .andDictCodeEqualTo(dto.getDictCode())
                    .andDictKeyEqualTo(dto.getDictKey());
            DictConstantDataDO dictConstantDataDO = dictConstantDataMapper.selectForOne(criteria);
            if(dictConstantDataDO == null) {
                throw new BizServiceException(DictConstantErrorCode.DATA_UNEXIST_ERROR, "待更新数据不存在");
            }
            BeanUtils.copyProperties(dto, dictConstantDataDO);
            dictConstantDataDO.setModifyTime(DateTimeUtils.currentDate());
            Integer result = dictConstantDataMapper.updateSelective(dictConstantDataDO);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(DictConstantErrorCode.DATA_UPDATE_ERROR);
            response.setMessage("字典常量分类数据更新异常");
            LOGGER.error(DictConstantErrorCode.DATA_UPDATE_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Integer> deleteDictConstant(DictConstantQueryInput input) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            if(input.getPkid() == null && CollectionUtils.isEmpty(input.getPkids())
                    && StringUtils.isBlank(input.getDictCode())
                    && CollectionUtils.isEmpty(input.getDictCodes())) {
                throw new BizServiceException(ServiceErrorCode.PARAM_ERROR, "必要参数未传");
            }
            Integer result = auxiliaryService.deleteDictConstant(input);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(DictConstantErrorCode.DATA_DELETE_ERROR);
            response.setMessage("字典常量数据删除异常");
            LOGGER.error(DictConstantErrorCode.DATA_DELETE_ERROR, e);
        }
        return response;
    }

    @Override
    public DataResponse<Integer> deleteDictConstantData(DictConstantDataQueryInput input) {
        DataResponse<Integer> response = new DataResponse<>();
        try {
            if(input.getPkid() == null && CollectionUtils.isEmpty(input.getPkids())
                    && StringUtils.isBlank(input.getDictCode())
                    && CollectionUtils.isEmpty(input.getDictCodes())
                    && StringUtils.isBlank(input.getDictKey())
                    && CollectionUtils.isEmpty(input.getDictKeys())) {
                throw new BizServiceException(ServiceErrorCode.PARAM_ERROR, "必要参数未传");
            }
            DictConstantDataCriteria criteria = new DictConstantDataCriteria();
            criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                    .andPkidEqualTo(input.getPkid())
                    .andPkidIn(input.getPkids())
                    .andDictCodeEqualTo(input.getDictCode())
                    .andDictCodeIn(input.getDictCodes())
                    .andDictKeyEqualTo(input.getDictKey())
                    .andDictKeyIn(input.getDictKeys());
            Integer result = dictConstantDataMapper.delete(criteria);
            response.setData(result);
        } catch (ServiceException e) {
            response.setRetcode(e.getCode());
            response.setMessage(e.getMessage());
            LOGGER.error(e.getCode(), e);
        } catch (Exception e) {
            response.setRetcode(DictConstantErrorCode.DATA_DELETE_ERROR);
            response.setMessage("字典常量分类数据删除异常");
            LOGGER.error(DictConstantErrorCode.DATA_DELETE_ERROR, e);
        }
        return response;
    }
}
