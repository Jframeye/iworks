package com.xiaoye.iworks.utils;

import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @Desc XML JAXB 工具类
 * @Author yehl
 * @Date 2017年11月2日
 */
public class XmlParserWithJAXBUtils {

	/**
	 * 将xml文件内容转化为JAXB Bean实体类
	 * @param xmlPath
	 * @param clazz
	 * @return
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertXmlToBean(String xmlPath, Class<T> clazz) throws Exception {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(XmlParserWithJAXBUtils.class.getClassLoader().getResourceAsStream(xmlPath));
		} catch (JAXBException exception) {
			throw exception;
		}
	}

	/**
	 * 将JAXB Bean实体转为xml写入文件
	 * @param xmlOutPath
	 * @param bean
	 * @throws Exception
	 */
	public static <T> void convertBeanToXml(String xmlOutPath, T bean) throws Exception {
		try {
			JAXBContext context = JAXBContext.newInstance(bean.getClass());
			Marshaller marshaller = context.createMarshaller();

			// output pretty printed
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			marshaller.marshal(bean, new FileOutputStream(XmlParserWithJAXBUtils.class.getClassLoader().getResource(xmlOutPath).getPath()));
			// marshaller.marshal(bean, System.out); // 打印
		} catch (Exception exception) {
			throw exception;
		}
	}
}
