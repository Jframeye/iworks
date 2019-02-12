package com.xiaoye.iworks.basic;

import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.basic.api.DictConstantService;
import com.xiaoye.iworks.basic.api.dto.DictConstantDataDto;
import com.xiaoye.iworks.basic.api.dto.DictConstantDto;
import com.xiaoye.iworks.basic.api.input.DictConstantDataQueryInput;
import com.xiaoye.iworks.basic.api.input.DictConstantQueryInput;
import com.xiaoye.iworks.basic.request.DictConstantDataQueryRequest;
import com.xiaoye.iworks.basic.request.DictConstantDataUpdateRequest;
import com.xiaoye.iworks.basic.request.DictConstantQueryRequest;
import com.xiaoye.iworks.basic.request.DictConstantUpdateRequest;
import com.xiaoye.iworks.common.api.BasicController;
import com.xiaoye.iworks.common.logger.annotation.RecordLogger;
import com.xiaoye.iworks.common.session.annotation.CheckSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 【字典分类】 控制器类
 * @auther: auto create by python 
 * @date: 2019-01-21 21:36:16 
 */
@RestController
@RequestMapping(value = "dict_constant", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DictConstantController extends BasicController {

    @Autowired
    private DictConstantService dictConstantService;

    @RecordLogger
    @RequestMapping(value = "list")
    public Response list(@RequestBody DictConstantQueryRequest request) {
        DictConstantQueryInput queryInput = new DictConstantQueryInput();
        queryInput.setDictCode(request.getDict_code());
        queryInput.setDictName(request.getDict_name());
        queryInput.setState(request.getState());
        queryInput.setListDictData(request.getList_dict_data());
        return dictConstantService.listDictConstants(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "list_dict")
    public Response listDict(@RequestBody DictConstantDataQueryRequest request) {
        DictConstantDataQueryInput queryInput = new DictConstantDataQueryInput();
        // TODO 参数校验以及参数填充

        return dictConstantService.listDictConstantDatas(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "find")
    public Response find(@RequestBody DictConstantQueryRequest request) {
        DictConstantQueryInput queryInput = new DictConstantQueryInput();
        // TODO 参数校验以及参数填充

        return dictConstantService.findDictConstant(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "find_dict")
    public Response findDict(@RequestBody DictConstantDataQueryRequest request) {
        DictConstantDataQueryInput queryInput = new DictConstantDataQueryInput();
        // TODO 参数校验以及参数填充

        return dictConstantService.findDictConstantData(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "update")
    public Response update(@RequestBody DictConstantUpdateRequest request) {
        DictConstantDto dto = new DictConstantDto();
        // TODO 参数填充(判断新增or修改)

        return dictConstantService.updateDictConstant(dto);
    }

    @RecordLogger
    @RequestMapping(value = "update_dict")
    public Response updateDict(@RequestBody DictConstantDataUpdateRequest request) {
        DictConstantDataDto dto = new DictConstantDataDto();
        // TODO 参数填充(判断新增or修改)

        return dictConstantService.updateDictConstantData(dto);
    }

    @RecordLogger
    @RequestMapping(value = "delete")
    public Response delete(@RequestBody DictConstantQueryRequest request) {
        DictConstantQueryInput queryInput = new DictConstantQueryInput();
        // TODO 参数校验以及参数填充

        return dictConstantService.deleteDictConstant(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "delete_dict")
    public Response deleteDict(@RequestBody DictConstantDataQueryRequest request) {
        DictConstantDataQueryInput queryInput = new DictConstantDataQueryInput();
        // TODO 参数校验以及参数填充

        return dictConstantService.deleteDictConstantData(queryInput);
    }
}