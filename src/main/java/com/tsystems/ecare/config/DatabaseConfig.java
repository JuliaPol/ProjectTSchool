package com.tsystems.ecare.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactory =
                new LocalContainerEntityManagerFactoryBean();

        final Map<String, String> jpaProperties = new HashMap<String, String>();
        jpaProperties.put(DRIVER, "com.mysql.jdbc.Driver");
        jpaProperties.put(URL, "jdbc:mysql://localhost:3306/ecare?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        jpaProperties.put(USER, "root");
        jpaProperties.put(PASS, "123456gf");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        jpaProperties.put("hibernate.enable_lazy_load_no_trans", "true");
        entityManagerFactory.setJpaPropertyMap(jpaProperties);
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactory.setPersistenceUnitName("ecare");
        entityManagerFactory.setPackagesToScan("com.tsystems.ecare.entities");
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//
//        MethodValidationPostProcessor processor =
//                new MethodValidationPostProcessor();
//        processor.setValidator((javax.validation.Validator) validator());
//        return processor;
//    }

//    @Bean
//    public Validator validator() {
//        return new LocalValidatorFactoryBean();
//    }
}