package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.dto.UserDTO;
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

    @Override
    public void save(User user) {
    }

    @Override
    @Transactional
    public UserDTO findByLogin(String login) {
        return fromEntityToDto(userDao.findByLogin(login));
    }


    private UserDTO fromEntityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setLogin(user.getLogin());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(user.getBirth_date()));
        userDTO.setPassportNumber(user.getPassportNumber());
        userDTO.setPassportIssuedWhen(new SimpleDateFormat("yyyy-MM-dd").format(user.getPassportIssuedWhen()));
        userDTO.setPassportIssuedByWhom(user.getPassportIssuedByWhom());
        userDTO.setAddress(user.getAddress().toString());
        return userDTO;
    }
}
