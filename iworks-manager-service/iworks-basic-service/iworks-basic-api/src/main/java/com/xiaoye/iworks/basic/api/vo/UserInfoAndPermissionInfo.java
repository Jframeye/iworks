package com.xiaoye.iworks.basic.api.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 功能描述: 用户信息以及用户权限信息
 * @auther: yehl
 * @date: 2019/2/12 18:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfoAndPermissionInfo implements Serializable {
    private static final long serialVersionUID = 6516097165169971017L;
}
