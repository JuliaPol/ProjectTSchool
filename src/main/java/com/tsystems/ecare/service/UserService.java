package com.tsystems.ecare.service;

import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.entities.User;

import java.util.List;

public interface UserService extends Service<User> {
    void save(User user);
    UserDTO findByLogin(String login);
    List<User> findAllUsersByRole(String role);

}
