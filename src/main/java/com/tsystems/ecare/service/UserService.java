package com.tsystems.ecare.service;

import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.entities.User;

public interface UserService {
    void save(User user);
    UserDTO findByLogin(String login);

}
