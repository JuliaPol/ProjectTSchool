package com.tsystems.ecare.service;

import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.dto.UserWithPasswordDTO;
import com.tsystems.ecare.entities.Role;
import com.tsystems.ecare.entities.User;

import java.util.List;

/**
 * Service for {@link User}
 */

public interface UserService extends Service<User> {
    User findByLogin(String login);
    List<User> getAllCustomers();
    void saveCustomer(User user);
    void sendEmailToNewCustomer(UserWithPasswordDTO user);
    Role getRole(String name);
}
