package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Option;

import java.util.List;

public interface OptionFacade extends Facade<OptionDTO, Option> {
    List<OptionDTO> getAllOptionsForCustomer(String number);
    List<OptionDTO> getAllAvailableOptionsForCustomer(String number);
}
