package com.tsystems.ecare.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsystems.ecare.dto.deserializer.UserDeserializer;
import com.tsystems.ecare.dto.serializer.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonSerialize
//@JsonDeserialize
public class UserDTO extends IdDTO {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String login;

    private String birthDate;

    private String passportNumber;

    private String passportIssuedWhen;

    private String passportIssuedByWhom;

    private AddressDTO address;

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
