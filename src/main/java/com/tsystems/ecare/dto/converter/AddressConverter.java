package com.tsystems.ecare.dto.converter;

import com.tsystems.ecare.dto.AddressDTO;
import com.tsystems.ecare.entities.Address;
import org.springframework.stereotype.Component;

@Component("addressConverter")
public class AddressConverter implements Converter<Address, AddressDTO>{
    @Override
    public AddressDTO from(Address source) {
        AddressDTO address = new AddressDTO();
        address.setId(source.getId());
        address.setCountry(source.getCountry() == null ? "" : source.getCountry());
        address.setCity(source.getCity() == null ? "" : source.getCity());
        address.setStreet(source.getStreet() == null ? "" : source.getStreet());
        address.setHouseNumber(source.getHouseNumber() == null ? "" : source.getHouseNumber().toString());
        address.setZipcode(source.getZipcode() == null ? "" : source.getZipcode());
        return address;
    }

    @Override
    public Address to(AddressDTO target) throws Exception {
        Address address = new Address();
        address.setId(target.getId());
        address.setCountry(target.getCountry() == null ? "" : target.getCountry());
        address.setCity(target.getCity() == null ? "" : target.getCity());
        address.setStreet(target.getStreet() == null ? "" : target.getStreet());
        address.setHouseNumber(target.getHouseNumber() == null ? null : Integer.parseInt(target.getHouseNumber()));
        address.setZipcode(target.getZipcode() == null ? "" : target.getZipcode());
        return address;
    }
}
