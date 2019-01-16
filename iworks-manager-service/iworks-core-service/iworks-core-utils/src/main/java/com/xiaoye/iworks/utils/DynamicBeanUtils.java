/**
 * 
**/
package com.xiaoye.iworks.utils;

import java.beans.PropertyDescriptor;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

/**
 * 实体类动态添加属性工具类
 * @author yehl
 * @date 2018年7月4日 下午10:02:00
 */
public class DynamicBeanUtils {

	/**
	 * 为目标类动态添加属性并赋值
	 * @param target 目标类
	 * @param dynamicBeanFieldsMap 属性名称：属性值
	 * @return
	 */
	public static <T> T setDynamicBeanField(T target, Map<String, Object> dynamicBeanFieldsMap) {
		if(dynamicBeanFieldsMap == null || dynamicBeanFieldsMap.isEmpty()) {
			return target;
		}
		PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(target);
        Map<String, Class<?>> propertyMap = Maps.newHashMap();
        for (PropertyDescriptor descriptor : descriptors) {
            if (!"class".equalsIgnoreCase(descriptor.getName())) {
                propertyMap.put(descriptor.getName(), descriptor.getPropertyType());
            }
        }
        // 动态添加属性
        dynamicBeanFieldsMap.forEach((k, v) -> propertyMap.put(k, v.getClass()));

        // 动态创建类
        DynamicBean<T> dynamicBean = new DynamicBean<T>(target, propertyMap);
        // add old value
        propertyMap.forEach((k, v) -> {
            try {
                // filter extra properties
                if (!dynamicBeanFieldsMap.containsKey(k)) {
                    dynamicBean.setValue(k, propertyUtilsBean.getNestedProperty(target, k));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // add extra value
        dynamicBeanFieldsMap.forEach((k, v) -> {
            try {
                dynamicBean.setValue(k, v);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        });
		return dynamicBean.getTarget();
	}
	
	/**
	 * 动态实体类
	 * @author yehl
	 * @date 2018年7月4日 下午10:04:46
	 */
	public static class DynamicBean<T> {
       
		/** 目标对象 */
        private T target;
 
        /** 属性集合 */
        private BeanMap beanMap;
 
        public DynamicBean(T target, Map<String, Class<?>> propertyMap) {
            this.target = generateBean(target, propertyMap);
            this.beanMap = BeanMap.create(this.target);
        }
 
 
        /**
         * bean 添加属性和值
         * @param property
         * @param value
         */
        public void setValue(String property, Object value) {
            beanMap.put(property, value);
        }
 
        /**
         * 获取属性值
         * @param property
         * @return
         */
        public Object getValue(String property) {
            return beanMap.get(property);
        }
 
        /**
         * 获取对象
         * @return
         */
        public T getTarget() {
            return this.target;
        }
 
 
        /**
         * 根据属性生成对象
         * @param target
         * @param propertyMap
         * @return
         */
		@SuppressWarnings("unchecked")
		private T generateBean(T target, Map<String, Class<?>> propertyMap) {
            BeanGenerator generator = new BeanGenerator();
            if (null != target) {
                generator.setSuperclass(target.getClass());
            }
            BeanGenerator.addProperties(generator, propertyMap);
            return (T) generator.create();
        }
    }
	
	public static void main(String[] args) {
		Test test = new Test();
		Map<String, Object> dynamicBeanFieldsMap = Maps.newHashMap();
		dynamicBeanFieldsMap.put("user_name", "叶宏梁");
		dynamicBeanFieldsMap.put("class_name", new Entity());
		test = setDynamicBeanField(test, dynamicBeanFieldsMap);
		System.out.println(JSON.toJSONString(test));
	}
}

class Test {}

class Entity {
	private String str;
	
	public Entity() {
		this.str = "这是我的测试类";
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
