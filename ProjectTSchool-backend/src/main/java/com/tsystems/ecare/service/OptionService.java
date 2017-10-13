package com.tsystems.ecare.service;

import com.tsystems.ecare.entities.Option;

import java.util.List;

public interface OptionService  extends Service<Option>{
    List<Option> getAllOptionsForCustomer(String number);
    List<Option> getAllAvailableOptionsForCustomer(String number);
    List<Option> getAllOptionsInRateAndContract(String number);
    List<Option> getAllIncompatibleOptions(String number);
    Option findOptionByName(String name);
}
