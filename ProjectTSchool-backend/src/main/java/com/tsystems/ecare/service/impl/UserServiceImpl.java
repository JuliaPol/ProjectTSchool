package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.AddressDao;
import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.RoleDao;
import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        userDao.insert(user);
    }

    @Override
    protected JpaDao<User> getDefaultDao() {
        return userDao;
    }


}
