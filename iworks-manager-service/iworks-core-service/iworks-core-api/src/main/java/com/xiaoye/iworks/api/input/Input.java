package com.xiaoye.iworks.api.input;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 功能描述: 请求入参基类
 * @auther: yehl
 * @date: 2019/1/2 19:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Input implements Serializable {
    private static final long serialVersionUID = -627003382109416429L;

    private boolean pagenation = false; // 是否分页查询
    private Integer offset = 0;
    private Integer limit = 20;
    /** 自定义参数 **/
    private String attach;

    /** 是否校验null **/
    boolean checkNull = false;
    /** 当前操作人信息 **/
    private String currentUserNo;
    private String currentNickName;
    private String currentUserName;
}
