package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Option;

import java.util.List;

public interface OptionFacade extends Facade<OptionDTO, Option> {
    List<OptionDTO> getAllOptionsForCustomer(String number);
    List<OptionDTO> getAllAvailableOptionsForCustomer(String number);
    List<OptionDTO> getAllIncompatibleOptionsForCustomer(String number);
    List<OptionDTO> getBy(Long id);
    boolean checkNewOptions(List<OptionDTO> optionList);
    void addIncompatible(Long current, List<String> incomp, boolean isCompatible);
    void create(OptionDTO optionDTO);
    void edit(OptionDTO optionDTO);
    void deleteOption(Long id);
}
