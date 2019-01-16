package com.xiaoye.iworks.basic.api;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;
import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.basic.api.dto.CodeGenerateDto;
import com.xiaoye.iworks.basic.api.input.CodeGenerateQueryInput;

/**
 * 功能描述: 代码生成服务接口
 * @auther: yehl
 * @date: 2019/1/5 17:58
 */
public interface CodeGenerateService {

    /**
     * 功能描述: 数据库表列表查询 <br>
     *     查询条件：
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/5 18:38
     */
    public PageResponse<CodeGenerateDto> listDataTables(CodeGenerateQueryInput queryInput);

    /**
     * 功能描述: 数据库表详情查询 <br>
     *     查询条件：
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/5 18:39
     */
    public DataResponse<CodeGenerateDto> findDataTable(CodeGenerateQueryInput queryInput);

    /**
     * 功能描述: 新增数据库表信息
     * @param:
     * @return: 返回新增数据主键
     * @auther: yehl
     * @date: 2019/1/5 18:39
     */
    public DataResponse<Long> insertDataTable(CodeGenerateDto codeGenerateDto);

    /**
     * 功能描述: 修改数据库表信息
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/5 18:40
     */
    public DataResponse<Integer> updateDataTable(CodeGenerateDto codeGenerateDto);

    /**
     * 功能描述: 删除数据库表信息
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/5 18:40
     */
    public DataResponse<Integer> deleteDataTable(CodeGenerateQueryInput queryInput);

    /**
     * 功能描述: 表设计文档上传
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/5 18:54
     */
    public Response upload();
}
