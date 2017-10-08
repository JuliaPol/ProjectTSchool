package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.entities.Rate;

import java.util.List;

public interface RateFacade extends Facade<RateDTO, Rate> {
    RateDTO findByName(String name);
    List<RateDTO> findAllForCustomer(String number);
    RateDTO findForCustomerByNumber(String number);
}
