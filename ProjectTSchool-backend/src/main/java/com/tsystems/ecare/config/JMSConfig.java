package com.tsystems.ecare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class JMSConfig {

    @Bean
    public JndiObjectFactoryBean jmsConnectionFactoryFactoryBean() {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("java:jboss/exported/jms/RemoteConnectionFactory");
        return jndiObjectFactoryBean;
    }

    public ConnectionFactory connectionFactory() {
        return (ConnectionFactory) jmsConnectionFactoryFactoryBean()
                .getObject();
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory
                = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setDefaultDestinationName("testQueue");
        return jmsTemplate;
    }
}
