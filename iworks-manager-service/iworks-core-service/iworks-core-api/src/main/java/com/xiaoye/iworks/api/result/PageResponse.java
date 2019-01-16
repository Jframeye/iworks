package com.xiaoye.iworks.api.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 功能描述: 分页数据结果响应
 * @auther: yehl
 * @date: 2019/1/2 19:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageResponse<T> extends Response {
    private static final long serialVersionUID = -1526467042701271359L;

    private Integer offset;
    private Integer limit;
    private Integer total;
    private List<T> dataList;
}
