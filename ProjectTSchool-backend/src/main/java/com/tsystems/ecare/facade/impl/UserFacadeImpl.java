package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.CustomerDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.dto.UserWithPasswordDTO;
import com.tsystems.ecare.entities.Address;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.facade.UserFacade;
import com.tsystems.ecare.service.ContractService;
import com.tsystems.ecare.service.Service;
import com.tsystems.ecare.service.UserService;
import com.tsystems.ecare.util.Util;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component("userFacade")
public class UserFacadeImpl extends FacadeImpl<User, UserDTO> implements UserFacade {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;


    private static Logger log = Logger.getLogger(UserFacadeImpl.class);

    /**
     * The method gets all customers.
     *
     * @return list customers
     */
    @Override
    public List<CustomerDTO> getAllCustomers() {
        return userService.getAllCustomers().stream()
                .map(this::convertToCustomerDto)
                .collect(Collectors.toList());
    }

    /**
     * The method converts User {@link User}
     * to DTO object {@link UserDTO}.
     *
     * @param entity that will be converted
     * @return converted UserDTO
     */
    @Override
    public UserDTO convertToDto(User entity) {
        UserDTO userDTO = modelMapper.map(entity, UserDTO.class);
        userDTO.dateConverter(entity.getBirthDate(), entity.getPassportIssuedWhen());
        return userDTO;
    }

    /**
     * The method creates new customer.
     *
     * @param userDTO
     */
    @Override
    public void createCustomer(UserWithPasswordDTO userDTO) {
        try {
            userService.saveCustomer(convertToEntityWithException(userDTO));
            userService.sendEmailToNewCustomer(userDTO);
        } catch (ParseException e) {
            log.error("Couldn't create a customer", e);
        }
    }

    /**
     * The method converts User {@link User}
     * to DTO object {@link CustomerDTO}.
     *
     * @param entity that will be converted
     * @return converted CustomerDTO
     */
    @Override
    public CustomerDTO convertToCustomerDto(User entity) {
        CustomerDTO customerDTO = modelMapper.map(entity, CustomerDTO.class);
        customerDTO.dateConverter(entity.getRegistrationDate());
        return customerDTO;
    }


    /**
     * The method converts User {@link UserWithPasswordDTO}
     * to entity object {@link User}. The methods sets password and login.
     *
     * @param dto that will be converted
     * @return converted User
     */
    private User convertToEntityWithException(UserWithPasswordDTO dto) throws ParseException {
        Address address = modelMapper.map(dto.getAddress(), Address.class);
        User user = new User();
        user.setAddress(address);
        user.setEmail(dto.getEmail());
        if (dto.getLogin() != null) {
            user.setLogin(dto.getLogin());
        } else {
            user.setLogin(dto.getEmail());
        }
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassportNumber(dto.getPassportNumber());
        user.setPassportIssuedByWhom(dto.getPassportIssuedByWhom());
        user.setComment(dto.getComment());
        if (dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            user.setPassword(passwordEncoder.encode(user.getLastName()));
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (dto.getBirthDate() != null) {
            user.setBirthDate(formatter.parse(dto.getBirthDate()));
        }
        if (dto.getPassportIssuedWhen() != null) {
            user.setPassportIssuedWhen(formatter.parse(dto.getPassportIssuedWhen()));
        }
        return user;
    }

    /**
     * The method converts User {@link UserDTO}
     * to entity object {@link User}.
     *
     * @param dto that will be converted
     * @return converted User
     */
    @Override
    public User convertToEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    /**
     * The method finds user by login and converts User {@link CustomerDTO}
     * to entity object {@link User}.
     *
     * @param login
     * @return converted User
     */
    @Override
    public CustomerDTO findByLoginWithContract(String login) {
        return convertToCustomerDto(userService.findByLogin(login));
    }

    @Override
    protected Service<User> getDefaultService() {
        return userService;
    }
}
