package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.dto.converter.Converter;
import com.tsystems.ecare.dto.converter.UserConverter;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.facade.UserFacade;
import com.tsystems.ecare.service.Service;
import com.tsystems.ecare.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("userFacade")
public class UserFacadeImpl extends FacadeImpl<User, UserDTO> implements UserFacade {
    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected Converter<User, UserDTO> getDefaultConverter() {
        return userConverter;
    }

    @Override
    protected Service<User> getDefaultService() {
        return userService;
    }

    @Override
    public List<UserDTO> getAllUsersByRole(String role) {
        List<User> userList = userService.findAllUsersByRole(role);
        return convertList(userList);
    }

    @Override
    public UserDTO findByLogin(String login) {
        return convertToDto(userService.findByLogin(login));
    }

    protected UserDTO convertToDto(User user) {
        UserDTO userDto = modelMapper.map(user, UserDTO.class);
        userDto.dateConverter(user.getBirthDate(), user.getPassportIssuedWhen());
        return userDto;
    }
}
