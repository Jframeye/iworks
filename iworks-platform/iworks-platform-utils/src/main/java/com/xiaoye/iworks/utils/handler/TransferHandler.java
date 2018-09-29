package com.xiaoye.iworks.utils.handler;

/**
 * @author yehl
 * @date 2018/9/21 13:35
 */
public interface TransferHandler<F, T> {

    public T transfer(F f);
}
