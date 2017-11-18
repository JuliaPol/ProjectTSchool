package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.AddressDao;
import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.RoleDao;
import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.dto.UserWithPasswordDTO;
import com.tsystems.ecare.entities.Role;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Implementation of {@link UserService} interface.
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<User> implements UserService {


    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public JavaMailSender mailSender;

    private static Logger log = Logger.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    @Transactional
    public List<User> getAllCustomers() {
        return roleDao.getAllCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(User user) {
        user.setRole(getRole("ROLE_CUSTOMER"));
        user.setRegistrationDate(new Date());
        userDao.insert(user);
    }

    @Async
    @Override
    public void sendEmailToNewCustomer(UserWithPasswordDTO user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Welcome to Satellite!");
        if (user.getLogin() != null) {
            message.setText("Your login: " + user.getLogin() + "\n" + "Your password: " + user.getPassword());
        } else {
            message.setText("Your login: " + user.getEmail() + "\n" + "Your password: " + user.getLastName());
        }
        mailSender.send(message);
    }

    @Override
    public Role getRole(String name) {
        return roleDao.getByName(name);
    }

    @Override
    protected JpaDao<User> getDefaultDao() {
        return userDao;
    }


}
