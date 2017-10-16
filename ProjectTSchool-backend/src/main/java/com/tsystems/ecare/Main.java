package com.tsystems.ecare;

import com.tsystems.ecare.config.AppConfig;
import com.tsystems.ecare.config.DatabaseConfig;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.dao.impl.ContractDaoImpl;
import com.tsystems.ecare.dao.impl.OptionDaoImpl;
import com.tsystems.ecare.dao.impl.RoleDaoImpl;
import com.tsystems.ecare.dao.impl.UserDaoImpl;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.entities.Role;
import com.tsystems.ecare.entities.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AppConfig.class);
        context.register(DatabaseConfig.class);
        context.refresh();

//        RateDao bean1 = context.getBean(RateDao.class);
//        Rate r = bean1.findForCustomerByNumber("88005353");
//        System.out.println(r);
//
//        ContractDaoImpl bean = context.getBean(ContractDaoImpl.class);
//        for (Option option : bean.getAllOption(1L)) {
//            System.out.println(option.getName() + " " + option.getCost());
//        }
//
//        OptionDaoImpl optionDao = context.getBean(OptionDaoImpl.class);
//        for (Option option : optionDao.getAll()) {
//            if (option.getCompatibleOption()!=null)
//                System.out.println(option.getCompatibleOption().getName());
//        }

//        RoleDaoImpl roleDao = context.getBean(RoleDaoImpl.class);
//        List<User> list = roleDao.getAllUsersByRole("ROLE_CUSTOMER");
//        for (User user : list) {
//                System.out.println(user.getFirstName());
//        }
//        RoleDaoImpl roleDao = context.getBean(RoleDaoImpl.class);
//        for (Role role : roleDao.getAll()) {
//            System.out.println(role.getRoleName());
//        }
    }
}
