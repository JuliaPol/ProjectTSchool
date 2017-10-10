package com.tsystems.ecare.service;

import com.tsystems.ecare.entities.Option;

import java.util.List;

public interface OptionService  extends Service<Option>{
    List<Option> getAllOptionsForCustomer(String number);
    List<Option> getAllAvailableOptionsForCustomer(String number);
}
