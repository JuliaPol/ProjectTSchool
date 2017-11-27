package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Option;

import java.util.List;

public interface OptionFacade extends Facade<OptionDTO, Option> {
    List<OptionDTO> getAllFreeOption(String number);

    boolean checkNewOptions(List<OptionDTO> optionList, String number);

    boolean checkNewOptionsForCompatible(List<OptionDTO> optionList, String number);

    void addIncompatible(Long current, List<String> incomp, boolean isCompatible);

    void create(OptionDTO optionDTO);

    void edit(OptionDTO optionDTO);

    void deleteOption(Long id);
}
