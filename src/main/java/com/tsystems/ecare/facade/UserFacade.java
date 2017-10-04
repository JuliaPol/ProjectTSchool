package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.UserDTO;

import java.util.List;

public interface UserFacade extends Facade<UserDTO> {
    List<UserDTO> getAllUsersByRole(String role);
}
