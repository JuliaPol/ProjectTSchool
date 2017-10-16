package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.CustomerDTO;
import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.facade.UserFacade;
import com.tsystems.ecare.service.ContractService;
import com.tsystems.ecare.service.Service;
import com.tsystems.ecare.service.UserService;
import com.tsystems.ecare.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("userFacade")
public class UserFacadeImpl extends FacadeImpl<User, UserDTO> implements UserFacade {

    @Autowired
    private Util util;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ContractService contractService;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return userService.getAllCustomers().stream()
                .map(this::convertToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO convertToDto(User entity) {
        UserDTO userDTO = modelMapper.map(entity, UserDTO.class);
        userDTO.dateConverter(entity.getBirthDate(), entity.getPassportIssuedWhen());
        return userDTO;
    }

    private CustomerDTO convertToCustomerDto(User entity) {
        CustomerDTO customerDTO = modelMapper.map(entity, CustomerDTO.class);
        return customerDTO;
    }

    @Override
    public User convertToEntity(UserDTO dto) {
        return null;
    }

    @Override
    public UserDTO findByLogin(String login) {
        UserDTO userDTO = convertToDto(userService.findByLogin(login));
        userDTO.setContactNumbers(contractService.findContactsByUserLogin(login));
        return userDTO;
    }

    @Override
    protected Service<User> getDefaultService() {
        return userService;
    }
}
