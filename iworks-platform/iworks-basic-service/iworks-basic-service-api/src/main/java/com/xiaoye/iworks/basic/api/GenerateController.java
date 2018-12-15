package com.xiaoye.iworks.basic.api;

import com.xiaoye.iworks.basic.api.input.generate.TableQueryInput;
import com.xiaoye.iworks.basic.service.GenerateService;
import com.xiaoye.iworks.basic.service.vo.generate.ColumnDto;
import com.xiaoye.iworks.basic.service.vo.generate.TableDto;
import com.xiaoye.iworks.core.api.BasicController;
import com.xiaoye.iworks.core.api.result.PageResult;
import com.xiaoye.iworks.core.api.result.Result;
import com.xiaoye.iworks.core.basic.common.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 代码生成器接口
 * @author yehl
 * @date 2018/9/30 17:43
 */
@RestController
@RequestMapping(value = "api/generate", produces = "application/json")
public class GenerateController extends BasicController {

    @Autowired
    private GenerateService generateService;

    /** 
     * 分页查询数据库表列表
     * @param 
     * @return 
     * @author yehl
     * @date 2018/11/1 19:02
     */
    @RequestMapping(value = "listTables", method = { RequestMethod.GET, RequestMethod.POST})
    public Result listTables(TableQueryInput input) {
        TableDto tableDto = new TableDto();
        tableDto.setDataBase(input.getData_base());
        tableDto.setTableName(input.getTable_name());
        tableDto.setState(input.getState());
        if(input.isPagnation()) {
            PageResult<TableDto> result = new PageResult<>();

            return result;
        }
        return Result.success(generateService.listTables(tableDto));
    }

    @RequestMapping(value = "listColumns", method = { RequestMethod.GET, RequestMethod.POST})
    public Result listColumns(TableQueryInput input) {
        ColumnDto columnDto = new ColumnDto();
        // TODO
        List<ColumnDto> columnDtos = generateService.listColumns(columnDto);
        return Result.success(columnDtos);
    }
}
