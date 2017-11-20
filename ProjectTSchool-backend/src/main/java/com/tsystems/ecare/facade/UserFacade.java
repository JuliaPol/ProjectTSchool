package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.CustomerDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.dto.UserWithPasswordDTO;
import com.tsystems.ecare.entities.User;

import java.text.ParseException;
import java.util.List;

public interface UserFacade extends Facade<UserDTO, User> {
    List<CustomerDTO> getAllCustomers();
    void createCustomer(UserWithPasswordDTO userDTO);
    UserDTO findByLogin(String login);
    CustomerDTO findByLoginWithContract(String login);
    CustomerDTO convertToCustomerDto(User entity);
}
