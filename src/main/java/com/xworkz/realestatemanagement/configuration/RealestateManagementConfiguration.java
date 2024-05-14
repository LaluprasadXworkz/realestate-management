package com.xworkz.realestatemanagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.xworkz.realestatemanagement")
@PropertySource("classpath:connection.properties")
public class RealestateManagementConfiguration {
    @Autowired
    Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean=new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[]{"com.xworkz.realestatemanagement"});
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter=new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        return entityManagerFactoryBean;
    }

    private Properties getHibernateProperties(){
        Properties properties=new Properties();
        properties.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20971520);
        multipartResolver.setMaxInMemorySize(1048576);
        return multipartResolver;
    }
}
