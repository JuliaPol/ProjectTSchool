package com.tsystems.ecare.service;

import com.tsystems.ecare.entities.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);

}
