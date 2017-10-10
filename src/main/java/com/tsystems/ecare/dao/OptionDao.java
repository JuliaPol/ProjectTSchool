package com.tsystems.ecare.dao;

import com.tsystems.ecare.entities.Option;

import java.util.List;

public interface OptionDao extends JpaDao<Option>{
    List<Option> getAllOptionsForCustomer(String number);
    List<Option> findAllOptionsInRateForCustomer(String number);
}
