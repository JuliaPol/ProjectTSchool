package com.tsystems.ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO extends IdDTO {
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String zipcode;
}
