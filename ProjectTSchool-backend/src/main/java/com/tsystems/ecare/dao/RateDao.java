package com.tsystems.ecare.dao;

import com.tsystems.ecare.entities.Rate;

import java.util.List;

public interface RateDao extends JpaDao<Rate> {
    Rate findByName(String name);

    List<Rate> findAllForCustomer(String number);

    Rate findForCustomerByNumber(String number);
}
