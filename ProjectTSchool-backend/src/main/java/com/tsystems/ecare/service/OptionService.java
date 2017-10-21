package com.tsystems.ecare.service;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Option;

import java.util.List;
import java.util.Set;

/**
 * Service for {@link Option}
 */

public interface OptionService extends Service<Option> {

    /**
     * Method finds all orders in database.
     * @return list of found orders.
     */
    List<Option> getAllOptionsForCustomer(String number);

    List<Option> getAllAvailableOptionsForCustomer(String number);

    List<Option> getAllOptionsInRateAndContract(String number);

    List<Option> getAllIncompatibleOptions(String number, List<Option> availableOptions);

    Option findOptionByName(String name);

    List<Option> getBy(Long id);

    List<Option> checkCompatibleOptions(List<Option> optionsInContract, List<Option> availableOption);

    List<Option> checkIncompatibleOptions(List<Option> optionsInContract, List<Option> availableOption);

    List<Option> getAllFreeOptions(String number);

    boolean checkNewOptions(List<Option> optionList);

    void addIncompatible(Long current, List<String> incomp, boolean isCompatible);

    void deleteOption(Long id);

    void editRateOptions(Long id, Option option);
}
