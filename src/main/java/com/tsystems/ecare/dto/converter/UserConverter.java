package com.tsystems.ecare.dto.converter;

import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component("userConverter")
public class UserConverter implements Converter<User, UserDTO> {

    @Autowired
    private AddressConverter addressConverter;

    @Override
    public UserDTO from(User source) {
        UserDTO dto = new UserDTO();
        dto.setId(source.getId());
        dto.setFirstName(source.getFirstName());
        dto.setLastName(source.getLastName());
        dto.setEmail(source.getEmail());
        dto.setLogin(source.getLogin());
        dto.setBirthDate(source.getBirthDate() == null ? "" : (new SimpleDateFormat("yyyy-MM-dd"))
                .format(source.getBirthDate()));
        dto.setPassportNumber(source.getPassportNumber() == null ? "" : source.getPassportNumber());
        dto.setPassportIssuedWhen(source.getPassportIssuedWhen() == null ? "" :(new SimpleDateFormat("yyyy-MM-dd"))
                .format(source.getPassportIssuedWhen()));
        dto.setPassportIssuedByWhom(source.getPassportIssuedByWhom() == null ? "" : source.getPassportIssuedByWhom());
        if (source.getAddress() != null)
            dto.setAddress(addressConverter.from(source.getAddress()));
        return dto;
    }

    @Override
    public User to(UserDTO target) throws Exception {
        User user = new User();
        user.setId(target.getId());
        user.setFirstName(target.getFirstName());
        user.setLastName(target.getLastName());
        user.setEmail(target.getEmail());
        user.setLogin(target.getLogin());
        user.setBirthDate(target.getBirthDate() == null ? null : (new SimpleDateFormat("yyyy-MM-dd")
                .parse(target.getBirthDate())));
        user.setPassportNumber(target.getPassportNumber() == null ? "" : target.getPassportNumber());
        user.setPassportIssuedWhen(target.getPassportIssuedWhen() == null ? null : (new SimpleDateFormat("yyyy-MM-dd")
                .parse(target.getPassportIssuedWhen())));
        user.setPassportIssuedByWhom(target.getPassportIssuedByWhom() == null ? "" : target.getPassportIssuedByWhom());
        if (target.getAddress() != null)
            user.setAddress(addressConverter.to(target.getAddress()));
        return user;
    }
}
