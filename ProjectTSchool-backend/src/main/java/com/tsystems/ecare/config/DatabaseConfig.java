package com.tsystems.ecare.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:persistence-mysql.properties"})
public class DatabaseConfig implements EnvironmentAware {

    private Environment env;

    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String ECARE = "database.name";
    private static final String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClassName";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_USER = "jdbc.user";
    private static final String JDBC_PASS = "jdbc.pass";
    private static final String HIBENATE_ENABLE_LAZY_LOAD_NO_TRANS = "hibernate.enable_lazy_load_no_trans";

    @Bean(name = ECARE)
    public DataSource dataSource() {
        Properties properties = new Properties();
        properties.setProperty(HIBENATE_ENABLE_LAZY_LOAD_NO_TRANS, "true");
        properties.setProperty("hibernate.dialect", env.getRequiredProperty(HIBERNATE_DIALECT));
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(env.getRequiredProperty(JDBC_DRIVER_CLASS_NAME));
        driverManagerDataSource.setUsername(env.getRequiredProperty(JDBC_USER));
        driverManagerDataSource.setSchema(ECARE);
        driverManagerDataSource.setUrl(env.getRequiredProperty(JDBC_URL));
        driverManagerDataSource.setPassword(env.getRequiredProperty(JDBC_PASS));
        driverManagerDataSource.setConnectionProperties(properties);
        return driverManagerDataSource;
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactory =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactory.setPersistenceUnitName(ECARE);
        entityManagerFactory.setPackagesToScan("com.tsystems.ecare.entities");
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}