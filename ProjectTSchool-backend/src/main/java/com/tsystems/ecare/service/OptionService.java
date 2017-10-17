package com.tsystems.ecare.service;

import com.tsystems.ecare.entities.Option;

import java.util.List;
import java.util.Set;

public interface OptionService  extends Service<Option>{
    List<Option> getAllOptionsForCustomer(String number);
    List<Option> getAllAvailableOptionsForCustomer(String number);
    List<Option> getAllOptionsInRateAndContract(String number);
    List<Option> getAllIncompatibleOptions(String number, List<Option> availableOptions);
    Option findOptionByName(String name);
    Set<Option> getBy(Long id);
    List<Option> checkCompatibleOptions(List<Option> optionsInContract, List<Option> availableOption);
    List<Option> checkIncompatibleOptions(List<Option> optionsInContract, List<Option> availableOption);
    List<Option> getAllFreeOptions(String number);
}
