package com.xiaoye.iworks.basic.service;

import com.xiaoye.iworks.basic.service.vo.generate.GenerateVO;
import com.xiaoye.iworks.core.basic.common.PageVO;

/**
 * 代码生成器接口类
 * @author yehl
 * @date 2018/11/1 19:20
 */
public interface GenerateService {

    PageVO<GenerateVO> listTablesByPage(GenerateVO tableCoulmnVO);
}
