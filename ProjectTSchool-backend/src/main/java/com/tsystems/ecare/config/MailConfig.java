package com.tsystems.ecare.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Properties;

@Configuration
@EnableAsync
@PropertySource(value = { "classpath:mail.properties" })
public class MailConfig implements EnvironmentAware {

    private Environment env;

    private static final String MAIL_USERNAME = "mail.username";
    private static final String MAIL_PASSWORD = "mail.password";
    private static final String MAIL_HOST = "mail.host";
    private static final String MAIL_PORT = "mail.port";

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Bean(name = "mailSender")
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(env.getRequiredProperty(MAIL_USERNAME));
        javaMailSender.setPassword(env.getRequiredProperty(MAIL_PASSWORD));
        javaMailSender.setPort(Integer.parseInt(env.getRequiredProperty(MAIL_PORT)));
        javaMailSender.setHost(env.getRequiredProperty(MAIL_HOST));

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.starttls.required", true);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        return javaMailSender;
    }

}
