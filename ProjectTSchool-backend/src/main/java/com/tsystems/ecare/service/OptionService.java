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

    List<Option> getAllAvailableOptionsForCustomer(String number);

    List<Option> getAllOptionsInRateAndContract(String number);

    List<Option> getAllIncompatibleOptions(String number, List<Option> availableOptions);

    Option findOptionByName(String name);

    List<Option> getBy(Long id);

    List<Option> checkCompatibleOptions(List<Option> optionsInContract, List<Option> availableOption);

    List<Option> checkIncompatibleOptions(List<Option> optionsInContract, List<Option> availableOption);

    List<Option> getAllFreeOptions(String number);

    boolean checkNewOptions(List<Option> optionList, List<Option> optionInRateAndContract);

    boolean checkOptionListForCompatible(List<Option> optionList, List<Option> optionInRateAndContract);

    void addIncompatible(Long current, List<String> incomp, boolean isCompatible);

    List<Option> getOptionsForRules(Long optionId);

    void deleteOption(Long id);

}
