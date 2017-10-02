package com.tsystems.ecare;

import com.tsystems.ecare.config.AppConfig;
import com.tsystems.ecare.config.DatabaseConfig;
import com.tsystems.ecare.dao.RuleDao;
import com.tsystems.ecare.dao.impl.ContractDaoImpl;
import com.tsystems.ecare.dao.impl.RuleDaoImpl;
import com.tsystems.ecare.dao.impl.UserDaoImpl;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.entities.Rule;
import com.tsystems.ecare.entities.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AppConfig.class);
        context.register(DatabaseConfig.class);
        context.refresh();

//        UserDaoImpl bean1 = context.getBean(UserDaoImpl.class);
//        System.out.println(bean1.get(1L).getLastName());
//        for (User user: bean1.getAll()) {
//            System.out.println(user.getFirstName());
//        }
//
//        RuleDaoImpl ruleDao = context.getBean(RuleDaoImpl.class);
//        for (Rule rule: ruleDao.getAll()) {
//            System.out.println(rule.getOption1());
//        }
//
//        ContractDaoImpl bean = context.getBean(ContractDaoImpl.class);
//        for (Option option : bean.getAllOption(1L)) {
//            System.out.println(option.getName() + " " + option.getCost());
//        }
    }
}
