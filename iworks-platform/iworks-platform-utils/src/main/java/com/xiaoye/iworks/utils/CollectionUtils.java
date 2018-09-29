package com.xiaoye.iworks.utils;

import com.xiaoye.iworks.utils.handler.FilterHandler;
import com.xiaoye.iworks.utils.handler.SummationHandler;
import com.xiaoye.iworks.utils.handler.TransferHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 集合操作工具类
 * @author yehl
 * @date 2018/9/17 16:58
 */
public class CollectionUtils {

    /**
     * 判断集合是否为空
     * @param
     * @return 为空，返回 true
     * @author yehl
     * @date 2018/9/17 17:00
     */
    public static boolean isEmpty(Collection collection) {
        if(collection == null) return true;
        return collection.size() == 0;
    }

    /** 
     * 判断集合是否不为空
     * @param 
     * @return 不为空，返回true
     * @author yehl
     * @date 2018/9/17 17:01
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断集合是否为空
     * @param
     * @return 为空，返回 true
     * @author yehl
     * @date 2018/9/17 17:00
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        if(map == null) return true;
        return map.size() == 0;
    }

    /**
     * 判断集合是否不为空
     * @param
     * @return 不为空，返回true
     * @author yehl
     * @date 2018/9/17 17:01
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

    /** 
     * 过滤集合中符合条件的元素
     * @param 
     * @return 
     * @author yehl
     * @date 2018/9/21 13:35
     */
    public static <T> List<T> filter(List<T> list, final FilterHandler handler) {
        List<T> target = new ArrayList<T>();
        if(isNotEmpty(list)) {
            return list.stream().filter(t -> handler.filter(t)).collect(Collectors.toList());
        }
        return target;
    }

    /** 
     * 集合元素转换
     * @param 
     * @return 
     * @author yehl
     * @date 2018/9/21 13:41
     */
    public static <F, T> List<T> transter(List<F> list, final TransferHandler hanler) {
        List<T> target = new ArrayList<T>();
        if(isNotEmpty(list)) {
            return list.stream().map(f -> (T) hanler.transfer(f)).collect(Collectors.toList());
        }
        return target;
    }

    /** 
     * 集合中统计某元素的值
     * @param 
     * @return 
     * @author yehl
     * @date 2018/9/21 14:01
     */
    public static <F> double summation(List<F> list, final SummationHandler handler) {
        double sum = 0;
        if(isNotEmpty(list)) {
           for (F f: list) {
               sum = handler.summation(sum, f);
           }
        }
        return sum;
    }
}
