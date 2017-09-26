package com.tsystems.ecare;

import com.tsystems.ecare.config.AppConfig;
import com.tsystems.ecare.config.DatabaseConfig;
import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.entities.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AppConfig.class);
        context.register(DatabaseConfig.class);
        context.refresh();

        UserDao userDao = context.getBean(UserDao.class);
        for (User user : userDao.getAll()) {
            System.out.println(user.getFirstName() + " " + user.getLastName());
        }

    }
}
