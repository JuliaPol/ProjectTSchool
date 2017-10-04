package com.tsystems.ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String birthDate;
    private String passportNumber;
    private String passportIssuedWhen;
    private String passportIssuedByWhom;
    private String address;
}
