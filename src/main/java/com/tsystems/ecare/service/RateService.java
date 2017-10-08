package com.tsystems.ecare.service;

import com.tsystems.ecare.entities.Rate;

import java.util.List;

public interface RateService extends Service<Rate> {
    Rate findByName(String name);
    List<Rate> findAllForCustomer(String number);
    Rate findForCustomerByNumber(String number);
}
