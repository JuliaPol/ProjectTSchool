package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.entities.User;

import java.util.List;

public interface UserFacade extends Facade<UserDTO, User> {
    List<UserDTO> getAllUsersByRole(String role);
    UserDTO findByLogin(String login);
}
