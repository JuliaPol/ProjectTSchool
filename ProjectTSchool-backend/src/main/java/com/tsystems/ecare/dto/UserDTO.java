package com.tsystems.ecare.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends IdDTO {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    private String login;

    private String password;

    private String birthDate;

    private String passportNumber;

    private String passportIssuedWhen;

    private String passportIssuedByWhom;

    private AddressDTO address;

    private List<String> contactNumbers;

    public void dateConverter(Date birthDate, Date passportIssuedWhen) {
        if (birthDate == null) {
            this.birthDate = null;
        } else {
            this.birthDate = dateFormat.format(birthDate);
        }
        if (passportIssuedWhen == null) {
            this.passportIssuedWhen = null;
        } else {
            this.passportIssuedWhen = dateFormat.format(passportIssuedWhen);
        }
    }
}
