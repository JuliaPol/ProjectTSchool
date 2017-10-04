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

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = UserSerializer.class)
@JsonDeserialize(using = UserDeserializer.class)
public class UserDTO extends IdDTO{
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
}
