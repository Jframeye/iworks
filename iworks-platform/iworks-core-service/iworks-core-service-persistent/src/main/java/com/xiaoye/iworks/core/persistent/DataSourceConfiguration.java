/**
 * 
**/
package com.xiaoye.iworks.core.persistent;

import javax.sql.DataSource;

import com.xiaoye.iworks.utils.DES3Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源配置
 * @author yehl
 * @date 2018年6月23日
 */
@Configuration
@PropertySource(value = { "classpath:application-database.properties" })
public class DataSourceConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("datasource.driver-class").trim());
		dataSource.setUsername(environment.getRequiredProperty("datasource.username").trim());
		dataSource.setPassword(DES3Utils.des3DecodeStr(environment.getRequiredProperty("datasource.password").trim()));
		dataSource.setUrl(environment.getRequiredProperty("datasource.url").trim());
		// TODO 其他配置
		return dataSource;
	}
}
