package com.xiaoye.iworks.utils;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.xiaoye.iworks.utils.handler.ListFindHandler;
import com.xiaoye.iworks.utils.handler.ListPagerHandler;
import com.xiaoye.iworks.utils.handler.ListToMapHandler;
import com.xiaoye.iworks.utils.handler.ListTransformHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Desc 集合工具类
 * @Author yehl
 * @Date 2018年1月17日
 */
public class CollectionUtils {

	/**
	 * 从List集合中查找第一个符合条件的元素，都没有则返回 null
	 * 
	 * @param list
	 * @param findHandler
	 * @return
	 */
	public static <T> T firstMatched(List<T> list, ListFindHandler<T> findHandler) {
		if (isNotEmpty(list)) {
			for (T t : list) {
				if (findHandler.match(t)) {
					return t;
				}
			}
		}
		return null;
	}

	/**
	 * 从List集合中查找最后一个符合条件的元素，都没有则返回 null
	 * 
	 * @param list
	 * @param findHandler
	 * @return
	 */
	public static <T> T lastMatched(List<T> list, ListFindHandler<T> findHandler) {
		if (isNotEmpty(list)) {
			for (int i = list.size() - 1; i >= 0; i--) {
				T t = list.get(i);
				if (findHandler.match(t)) {
					return t;
				}
			}
		}
		return null;
	}

	/**
	 * 判断list集合中是否存在某个对象
	 * 
	 * @param list
	 * @param findHandler
	 * @return
	 */
	public static <T> boolean exist(List<T> list, ListFindHandler<T> findHandler) {
		if (isNotEmpty(list)) {
			for (int i = list.size() - 1; i >= 0; i--) {
				T t = list.get(i);
				if (findHandler.match(t)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 从List集合中查找所有符合条件的元素，没有则返回空集合
	 * 
	 * @param list
	 * @param findHandler
	 * @return
	 */
	public static <T> List<T> find(List<T> list, ListFindHandler<T> findHandler) {
		List<T> results = new ArrayList<T>();
		if (isNotEmpty(list)) {
			for (T t : list) {
				if (findHandler.match(t)) {
					results.add(t);
				}
			}
		}
		return results;
	}

	/**
	 * 集合转换
	 * 
	 * @param list
	 * @param transformHandler
	 * @return
	 */
	public static <F, T> List<T> transform(List<F> list, ListTransformHandler<F, T> transformHandler) {
		List<T> results = new ArrayList<>();
		if (isNotEmpty(list)) {
			for (F f : list) {
				results.add(transformHandler.transform(f));
			}
		}
		return results;
	}
	
	/**
	 * 删除集合中指定元素
	 * 
	 * @param all
	 * @param removed
	 * @return
	 */
	public static <T> List<T> subtract(List<T> all, List<T> removed) {
		if (isEmpty(all)) {
			return new ArrayList<T>();
		}
		List<T> list = new ArrayList<T>(all);
		if (isNotEmpty(removed)) {
			Iterator<T> iterator = removed.iterator();
			while (iterator.hasNext()) {
				list.remove(iterator.next());
			}
		}
		return list;
	}

	/**
	 * 两集合的并集
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static <T> List<T> merge(List<T> list1, List<T> list2) {
		List<T> results = subtract(list1, null);
		if (isNotEmpty(list2)) {
			Iterator<T> iterator = list1.iterator();
			while (iterator.hasNext()) {
				T t = iterator.next();
				if (!results.contains(t)) {
					results.add(t);
				}
			}
		}
		return results;
	}

	/**
	 * 两集合的交集
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static <T> List<T> intersection(List<T> list1, List<T> list2) {
		List<T> all = subtract(list1, null);
		all.addAll(subtract(list2, null));
		return subtract(all, merge(list1, list2));
	}

	/**
	 * List集合转为Map集合
	 * 
	 * @param list
	 * @param mapHandler
	 * @return
	 */
	public static <K, T> Map<K, T> listToMap(List<T> list, ListToMapHandler<K, T> mapHandler) {
		// 传统方法
		if (isNotEmpty(list)) {
			Map<K, T> resultMap = Maps.uniqueIndex(list, new Function<T, K>() {
				public K apply(T t) {
					return mapHandler.getKey(t);
				}
			});
			return resultMap;
		}
		return null;
	}

	/**
	 * 集合分页处理
	 * 
	 * @param list
	 * @param limit
	 * @param pageHandler
	 */
	public static <T> void pageControl(List<T> list, int limit, ListPagerHandler<T> pageHandler) {
		if (isEmpty(list)) {
			return;
		}
		int totalRecord = list.size();
		int totalPages = totalRecord % limit == 0 ? (totalRecord / limit) : ((totalRecord / limit) + 1);
		for (int i = 0; i < totalPages; i++) {
			pageHandler.control(list.subList(i * limit, (i + 1) * limit));
		}
	}

	/**
	 * 判断List集合是否为空
	 * 
	 * @param list
	 * @return true 非空
	 */
	public static <T> boolean isNotEmpty(List<T> list) {
		if (list == null || list.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断List集合是否为空
	 * 
	 * @param list
	 * @return true 为空
	 */
	public static <T> boolean isEmpty(List<T> list) {
		return !isNotEmpty(list);
	}

	/**
	 * 将字符串按照regex转为List集合
	 * @param string
	 * @param separator
	 * @return
	 */
	public static List<String> stringToList(String string, String separator) {
		return stringToList(string, separator, true);
	}

	/**
	 * 将字符串按照regex转为List集合
	 * @param string
	 * @param separator
	 * @param removeEmpty
	 * @return
	 */
	public static List<String> stringToList(String string, String separator, boolean removeEmpty) {
		if(removeEmpty) return Splitter.on(separator).trimResults().omitEmptyStrings().splitToList(string);
		return Splitter.on(separator).trimResults().splitToList(string);
	}

	/**
	 * 将List转为字符串，摒弃null的数据
	 * @param list
	 * @param separator
	 * @return
	 */
	public static String listToString(List<String> list, String separator) {
		return listToString(list, separator, true);
	}

	/**
	 * 将List转为字符串，摒弃null的数据
	 * @param list
	 * @param separator
	 * @param removeNull
	 * @return
	 */
	public static String listToString(List<String> list, String separator, boolean removeNull) {
		if(removeNull) return Joiner.on(separator).skipNulls().join(list);
		return Joiner.on(separator).join(list);
	}

	/**
	 * 将List转为字符串，null数据使用默认值替换
	 * @param list
	 * @param separator
	 * @param defaultValue null 替代值
	 * @return
	 */
	public static String listToString(List<String> list, String separator, String defaultValue) {
		return Joiner.on(separator).useForNull(defaultValue).join(list);
	}

	/**
	 * 功能描述: 字符串转Map集合
	 * @param: string
	 * @param separator 分隔符
	 * @param keyValueSeparator 键值分隔符
	 * @return: 
	 * @auther: yehl
	 * @date: 2018/12/21 13:03
	 */
	public static Map<String, String> stringToMap(String string, String separator, String keyValueSeparator) {
		return Splitter.on(separator).withKeyValueSeparator(keyValueSeparator).split(string);
	}

	/**
	 * 功能描述: Map集合转字符串
	 * @param: map
	 * @param separator 分割符
	 * @param keyValueSeparator 键值分隔符
	 * @return:
	 * @auther: yehl
	 * @date: 2018/12/21 13:06
	 */
	public static String mapToString(Map<String, Object> map, String separator, String keyValueSeparator) {
		return Joiner.on(separator).withKeyValueSeparator(keyValueSeparator).join(map);
	}

	public static void main(String[] args) {
		String str = "asd,null,dsa";
		System.out.println(stringToList(str, ",").size());

		Map<String, Object> map = Maps.newHashMap();
		map.put("listid", "123123123132");
		map.put("amount", BigDecimal.ZERO);
		System.out.println(mapToString(map, "&", "="));
	}
}
