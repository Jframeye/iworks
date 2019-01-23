package com.xiaoye.iworks.basic.core.support;

import com.xiaoye.iworks.basic.api.dto.DictConstantDataDto;
import com.xiaoye.iworks.basic.api.dto.DictConstantDto;
import com.xiaoye.iworks.basic.api.input.DictConstantQueryInput;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantCriteria;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantDataCriteria;
import com.xiaoye.iworks.basic.persistent.entity.DictConstantDataDO;
import com.xiaoye.iworks.basic.persistent.mapper.DictConstantDataMapper;
import com.xiaoye.iworks.basic.persistent.mapper.DictConstantMapper;
import com.xiaoye.iworks.persistent.constant.PersistentConstant;
import com.xiaoye.iworks.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能描述: 字典常量相关辅助服务类
 * @auther: yehl
 * @date: 2019/1/23 10:14
 */
@Service
public class DictConstantAuxiliaryService {
    public final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private DictConstantMapper dictConstantMapper;
    @Resource
    private DictConstantDataMapper dictConstantDataMapper;

    /**
     * 功能描述: 根据字典编码查询分类数据
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/23 10:01
     */
    public List<DictConstantDataDto> listDictDatasByDictCode(DictConstantDto dto) {
        DictConstantDataCriteria dataCriteria = new DictConstantDataCriteria();
        dataCriteria.createCriteriaInternal().andDictCodeEqualTo(dto.getDictCode());
        List<DictConstantDataDO> dataResults = dictConstantDataMapper.selectForList(dataCriteria);
        List<DictConstantDataDto> results = CollectionUtils.transform(dataResults, (data) -> {
            DictConstantDataDto dataDto = new DictConstantDataDto();
            BeanUtils.copyProperties(data, dataDto);
            return dataDto;
        });
        return results;
    }

    /**
     * 功能描述: 删除字典常量数据
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/23 10:11
     */
    @Transactional
    public Integer deleteDictConstant(DictConstantQueryInput queryInput) {
        DictConstantCriteria criteria = new DictConstantCriteria();
        criteria.createCriteriaInternal().andLstateEqualTo(PersistentConstant.Lstate.NORMAL)
                .andPkidEqualTo(queryInput.getPkid())
                .andPkidIn(queryInput.getPkids())
                .andDictCodeEqualTo(queryInput.getDictCode())
                .andDictCodeIn(queryInput.getDictCodes());
        Integer result = dictConstantMapper.delete(criteria);
        DictConstantDataCriteria dataCriteria = new DictConstantDataCriteria();
        dataCriteria.createCriteriaInternal().andDictCodeEqualTo(queryInput.getDictCode());
        dictConstantDataMapper.delete(dataCriteria);
        return result;
    }
}
