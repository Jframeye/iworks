package com.xiaoye.iworks.basic.api.constant;


/**
 * 功能描述: 【系统用户登录信息】常量类
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
public class UserLoginInfoConstant {

    public static interface State {
        public static final Integer NORMAL = 1; // 正常
        public static final Integer FREEZE = 2; // 冻结
        public static final Integer LOCKED = 3; // 锁定
        public static final Integer DISCARD = 4; // 废弃
    }
}