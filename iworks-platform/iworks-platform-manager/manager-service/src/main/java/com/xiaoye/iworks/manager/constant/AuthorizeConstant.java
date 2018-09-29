package com.xiaoye.iworks.manager.constant;

/**
 * @author yehl
 * @date 2018/9/17 10:34
 */
public class AuthorizeConstant {

    /**
     * 新增权限
     */
    public static final Integer INSERT_PERMISSION = 1 << 0;

    /**
     * 删除权限
     */
    public static final Integer DELETE_PERMISSION = 1 << 1;

    /**
     * 修改权限
     */
    public static final Integer UPDATE_PERMISSION = 1 << 2;

    /**
     * 查询权限
     */
    public static final Integer SELECT_PERMISSION = 1 << 3;

    /**
     * 展示权限
     */
    public static final Integer VIEW_PERMISSION = 1 << 4;

    /** 
     * 判断是否有权限
     * @param authorize      已有的权限码
     * @param permission     待校验的权限值
     * @return 
     * @author yehl
     * @date 2018/9/17 12:41
     */
    public static boolean hasPermission(Integer authorize, Integer permission) {
        if (authorize == null) {
            throw  new RuntimeException("授权码不能为空");
        }
        if (permission == null) {
            throw  new RuntimeException("待校验的权限值不能为空");
        }
        return (permission == (authorize & permission)) && (authorize == (authorize | permission));
    }

    /**
     * 授权
     * @param authorize      已有的权限码
     * @param newerauthorize 待新加的权限值
     * @return
     * @author yehl
     * @date 2018/9/17 11:33
     */
    public static Integer authorize(Integer authorize, Integer newerauthorize) {
        if (newerauthorize == null) {
            throw  new RuntimeException("待新增的权限值不能为空");
        }
        if(!is2pow(newerauthorize)) {
            throw  new RuntimeException("待新增的权限值必须是2的N次方");
        }
        if (authorize == null) return newerauthorize;
        return authorize | newerauthorize;
    }

    /**
     * 取消授权
     * @param authorize       已有的权限码
     * @param deleteauthorize 待取消的权限值
     * @return
     * @author yehl
     * @date 2018/9/17 11:33
     */
    public static Integer unauthorize(Integer authorize, Integer deleteauthorize) {
        if (authorize == null) {
            throw  new RuntimeException("授权码不能为空");
        }
        if (deleteauthorize == null) {
            throw  new RuntimeException("待取消的权限值不能为空");
        }
        if(!is2pow(deleteauthorize)) {
            throw  new RuntimeException("待取消的权限值必须是2的N次方");
        }
        return authorize & ~deleteauthorize;
    }

    /** 
     * 判断是否为2的N次方
     * @param 
     * @return 
     * @author yehl
     * @date 2018/9/17 12:43
     */
    private static boolean is2pow(int authorize) {
        if (authorize == 0) return false;
        if (authorize == 1) return true;
        return authorize % 2 == 0;
    }
}
