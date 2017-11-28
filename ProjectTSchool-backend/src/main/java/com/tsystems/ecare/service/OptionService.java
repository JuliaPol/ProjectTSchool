package com.tsystems.ecare.service;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Option;

import java.util.List;
import java.util.Set;

/**
 * Service for {@link Option}
 */

public interface OptionService extends Service<Option> {

    List<Option> getAllOptionsForCustomer(String number);

    List<Option> getAllOptionsInRateAndContract(String number);

    Option findOptionByName(String name);

    List<Option> checkCompatibleOptions(List<Option> optionsInContract, List<Option> availableOption);

    List<Option> checkIncompatibleOptions(List<Option> optionsInContract, List<Option> availableOption);

    List<Option> getAllFreeOptions(String number);

    boolean checkNewOptions(List<Option> optionList, String number);

    boolean checkOptionListForCompatible(List<Option> optionList, String number);

    void addIncompatible(Long current, List<String> incomp, boolean isCompatible);

    void deleteOption(Long id);

}
