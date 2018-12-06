package com.xiaoye.iworks.basic.api;

import com.xiaoye.iworks.basic.api.input.generate.GenerateQueryInput;
import com.xiaoye.iworks.basic.service.GenerateService;
import com.xiaoye.iworks.basic.service.vo.generate.GenerateVO;
import com.xiaoye.iworks.core.api.BasicController;
import com.xiaoye.iworks.core.api.result.Result;
import com.xiaoye.iworks.core.basic.common.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码生成器接口
 * @author yehl
 * @date 2018/9/30 17:43
 */
@RestController
@RequestMapping(value = "api/generate")
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
    @RequestMapping(value = "listTablesByPage", method = { RequestMethod.GET, RequestMethod.POST})
    public Result listTablesByPage(GenerateQueryInput input) {
        GenerateVO generateVO = new GenerateVO();
        // TODO
        PageVO<GenerateVO> pageVO = generateService.listTablesByPage(generateVO);
        return Result.success(pageVO);
    }

    @RequestMapping(value = "listTableColumns", method = { RequestMethod.GET, RequestMethod.POST})
    public Result listTableColumns(GenerateQueryInput input) {
        GenerateVO generateVO = new GenerateVO();
        // TODO
        PageVO<GenerateVO> pageVO = generateService.listTablesByPage(generateVO);
        return Result.success(pageVO);
    }
}
