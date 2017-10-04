package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.RoleDao;
import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.dto.converter.UserConverter;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<User> implements UserService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserConverter userConverter;

    @Override
    public void save(User user) {
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    @Transactional
    public List<User> findAllUsersByRole(String role) {
        return roleDao.getAllUsersByRole(role);
    }


    @Override
    protected JpaDao<User> getDefaultDao() {
        return userDao;
    }
}
