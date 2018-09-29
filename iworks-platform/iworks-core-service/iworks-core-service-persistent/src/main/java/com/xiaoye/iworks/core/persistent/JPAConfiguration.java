package com.xiaoye.iworks.core.persistent;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.xiaoye.iworks" }) // 设置Repository所在位置
public class JPAConfiguration {

	private static final String PACKAGES_TO_SCAN = "packages.to.scan";

	@Autowired
	private Environment environment;

	@Autowired
	private DataSource dataSource;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		factory.setPackagesToScan(environment.getRequiredProperty(PACKAGES_TO_SCAN));
		factory.setJpaProperties(hibernateProperties());
		factory.afterPropertiesSet();
		return factory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactory().getObject());
		return manager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		// 显示sql语句
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		// 格式化sql语句
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		// 方言
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		// 自动生成表
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		// 名字策略
		properties.put("hibernate.physical_naming_strategy", environment.getRequiredProperty("hibernate.naming.physical-strategy"));

		return properties;
	}
}