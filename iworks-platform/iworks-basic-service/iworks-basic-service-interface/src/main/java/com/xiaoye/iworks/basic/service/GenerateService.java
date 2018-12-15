package com.xiaoye.iworks.basic.service;

import com.xiaoye.iworks.basic.service.vo.generate.ColumnDto;
import com.xiaoye.iworks.basic.service.vo.generate.TableDto;
import com.xiaoye.iworks.core.basic.common.PageVO;

import java.util.List;

/**
 * 代码生成器接口类
 * @author yehl
 * @date 2018/11/1 19:20
 */
public interface GenerateService {

    List<TableDto> listTables(TableDto tableDto);

    List<ColumnDto> listColumns(ColumnDto columnDto);
}
