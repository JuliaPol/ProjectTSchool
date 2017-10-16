package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.CustomerDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.entities.User;

import java.util.List;

public interface UserFacade extends Facade<UserDTO, User> {
    List<CustomerDTO> getAllCustomers();
    UserDTO findByLogin(String login);
}
