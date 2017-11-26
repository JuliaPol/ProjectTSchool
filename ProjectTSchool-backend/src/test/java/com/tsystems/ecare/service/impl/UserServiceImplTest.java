package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.RoleDao;
import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class UserServiceImplTest {

    @Configuration
    static class ContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }


        @Bean
        public RoleDao roleDao() {
            RoleDao roleDao = mock(RoleDao.class);
            return roleDao;
        }

        @Bean
        public UserDao userDao() {
            UserDao userDao = mock(UserDao.class);
            return userDao;
        }

        @Bean
        public JavaMailSender mailSender() {
            JavaMailSender mailSender = mock(JavaMailSender.class);
            return mailSender;
        }
    }
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void findByLogin() {
        String login = "king";
        Long id = 11L;
        User user = new User();
        user.setId(id);
        when(userDao.findByLogin(login)).thenReturn(user);
        User user1 = userService.findByLogin(login);
        assertNotNull(user1);
        assertEquals(id, user1.getId());
    }

    @Test
    public void getAllCustomers() {
        Long id = 11L;
        Long id1 = 12L;
        Long id2 = 13L;
        User user = new User();
        User user1 = new User();
        User user2 = new User();
        user.setId(id);
        user.setId(id1);
        user.setId(id2);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        users.add(user2);
        when(roleDao.getAllCustomers()).thenReturn(users);
        List<User> users1 = userService.getAllCustomers();
        assertNotNull(users1);
        assertEquals(users1.get(0).getId(), users.get(0).getId());
        assertEquals(users1.get(1).getId(), users.get(1).getId());
        assertEquals(users1.get(2).getId(), users.get(2).getId());
    }
}
