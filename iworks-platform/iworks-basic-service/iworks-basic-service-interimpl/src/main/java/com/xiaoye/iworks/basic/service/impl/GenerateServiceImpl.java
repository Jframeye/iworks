package com.xiaoye.iworks.basic.service.impl;

import com.xiaoye.iworks.basic.service.GenerateService;
import com.xiaoye.iworks.basic.service.vo.generate.ColumnDto;
import com.xiaoye.iworks.core.basic.common.PageVO;
import org.springframework.stereotype.Service;

/**
 * 代码生成器实现类
 * @author yehl
 * @date 2018/11/1 19:23
 */
@Service
public class GenerateServiceImpl implements GenerateService {
    @Override
    public PageVO<ColumnDto> listTablesByPage(ColumnDto tableCoulmnVO) {
        return null;
    }
}
