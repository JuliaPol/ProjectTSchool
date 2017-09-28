package com.tsystems.ecare;

import com.tsystems.ecare.config.AppConfig;
import com.tsystems.ecare.config.DatabaseConfig;
import com.tsystems.ecare.dao.impl.ContractDaoImpl;
import com.tsystems.ecare.entities.OptionEntity;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AppConfig.class);
        context.register(DatabaseConfig.class);
        context.refresh();

        ContractDaoImpl bean = context.getBean(ContractDaoImpl.class);
        for (OptionEntity option : bean.getAllOption(1L)) {
            System.out.println(option.getName() + " " + option.getCost());
        }
    }
}
