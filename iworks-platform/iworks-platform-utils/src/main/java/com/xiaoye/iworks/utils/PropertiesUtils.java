package com.xiaoye.iworks.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 属性文件读取
 * @author yehl
 * @date 2018年6月23日
 */
public class PropertiesUtils {
    
    /** 线程安全的 Map 用以存储属性文件信息 */
    private static Map<String, Properties> loadedProperties = new ConcurrentHashMap<String, Properties>();

    /**
     * 加载属性文件信息
     * @param filePath
     * @return
     */
    public static Properties loadProperties(String filePath) {
        try {
            if(loadedProperties.get(filePath) != null) {
                return loadedProperties.get(filePath);
            }
            InputStream stream = PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath);
            Properties properties = new Properties();
            properties.load(stream);
            loadedProperties.put(filePath, properties);
            return properties;
        }
        catch (IOException e) {
			throw new RuntimeException(e);
        }
    }
    
    /**
     * 加载属性文件信息
     * @param file
     * @return
     */
    public static Properties loadProperties(File file) {
        String filePath = file.getAbsolutePath();
        return loadProperties(filePath);
    }

    /**
     * 获取属性文件信息
     * @param fileName
     * @return
     */
    public static Properties getProperties(String fileName) {
		if (!loadedProperties.containsKey(fileName)) {
			loadProperties(fileName);
		}
        return loadedProperties.get(fileName);
    }

    /**
     * 清空
     */
    public static void clear(String fileName) {
        if(loadedProperties.get(fileName) != null) {
            loadedProperties.remove(fileName);
        }
    }
    
    /**
     * 清空
     */
    public static void clear() {
        if(loadedProperties != null && !loadedProperties.isEmpty()) {
            loadedProperties.clear();
        }
    }

	public static void megre() {

	}
}