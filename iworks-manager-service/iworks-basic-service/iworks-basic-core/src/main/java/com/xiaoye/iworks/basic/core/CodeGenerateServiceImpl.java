package com.xiaoye.iworks.basic.core;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;
import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.basic.api.CodeGenerateService;
import com.xiaoye.iworks.basic.api.dto.CodeGenerateDto;
import com.xiaoye.iworks.basic.api.input.CodeGenerateQueryInput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * 功能描述: 代码服务接口实现类
 * @auther: yehl
 * @date: 2019/1/5 18:47
 */
@Service
public class CodeGenerateServiceImpl implements CodeGenerateService {
    public final Log LOGGER = LogFactory.getLog(getClass());

    @Override
    public PageResponse<CodeGenerateDto> listDataTables(CodeGenerateQueryInput queryInput) {
        return null;
    }

    @Override
    public DataResponse<CodeGenerateDto> findDataTable(CodeGenerateQueryInput queryInput) {
        return null;
    }

    @Override
    public DataResponse<Long> insertDataTable(CodeGenerateDto codeGenerateDto) {
        return null;
    }

    @Override
    public DataResponse<Integer> updateDataTable(CodeGenerateDto codeGenerateDto) {
        return null;
    }

    @Override
    public DataResponse<Integer> deleteDataTable(CodeGenerateQueryInput queryInput) {
        return null;
    }

    @Override
    public Response upload() {
        return null;
    }
}
