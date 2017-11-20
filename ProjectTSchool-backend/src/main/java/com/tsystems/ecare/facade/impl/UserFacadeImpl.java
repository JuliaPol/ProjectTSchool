package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dao.RoleDao;
import com.tsystems.ecare.dto.CustomerDTO;
import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.dto.UserWithPasswordDTO;
import com.tsystems.ecare.entities.Address;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.facade.UserFacade;
import com.tsystems.ecare.service.ContractService;
import com.tsystems.ecare.service.Service;
import com.tsystems.ecare.service.UserService;
import com.tsystems.ecare.util.Util;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component("userFacade")
public class UserFacadeImpl extends FacadeImpl<User, UserDTO> implements UserFacade {

    @Autowired
    private Util util;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ContractService contractService;

    private static Logger log = Logger.getLogger(UserFacadeImpl.class.getName());

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

    @Override
    public void createCustomer(UserWithPasswordDTO userDTO){
        try {
            userService.saveCustomer(convertToEntityWithException(userDTO));
            userService.sendEmailToNewCustomer(userDTO);
        } catch (ParseException e) {
            log.error("Couldn't create a customer", e);
        }
    }

    @Override
    public CustomerDTO convertToCustomerDto(User entity) {
        CustomerDTO customerDTO = modelMapper.map(entity, CustomerDTO.class);
        customerDTO.dateConverter(entity.getRegistrationDate());
        return customerDTO;
    }


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

    @Override
    public User convertToEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    @Override
    public UserDTO findByLogin(String login) {
        UserDTO userDTO = convertToDto(userService.findByLogin(login));
        userDTO.setContactNumbers(contractService.findContactsByUserLogin(login));
        return userDTO;
    }

    @Override
    public CustomerDTO findByLoginWithContract(String login) {
        return convertToCustomerDto(userService.findByLogin(login));
    }

    @Override
    protected Service<User> getDefaultService() {
        return userService;
    }
}
