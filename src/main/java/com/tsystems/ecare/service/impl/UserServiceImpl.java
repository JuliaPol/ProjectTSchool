package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.dto.converter.UserConverter;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserConverter userConverter;

    @Override
    public void save(User user) {
    }

    @Override
    @Transactional
    public UserDTO findByLogin(String login) {
        return userConverter.from(userDao.findByLogin(login));
}
}
