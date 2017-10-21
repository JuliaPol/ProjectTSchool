package com.tsystems.ecare.service;

import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.entities.Rate;

import java.util.List;

/**
 * Service for {@link Rate}
 */
public interface RateService extends Service<Rate> {
    Rate findByName(String name);
    List<Rate> findAllForCustomer(String number);
    Rate findForCustomerByNumber(String number);
    void editRate(Rate rate);
    void deleteRate(Long id);
}
