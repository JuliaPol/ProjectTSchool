package com.tsystems.ecare.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsystems.ecare.dto.deserializer.AddressDeserializer;
import com.tsystems.ecare.dto.serializer.AddressSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonSerialize
//@JsonDeserialize
public class AddressDTO extends IdDTO{
    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private String zipcode;
}
