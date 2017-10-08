package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.service.OptionService;
import com.tsystems.ecare.service.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("optionFacade")
public class OptionFacadeImpl extends FacadeImpl<Option, OptionDTO> implements OptionFacade {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OptionService optionService;

    @Override
    public OptionDTO convertToDto(Option entity) {
        OptionDTO optionDTO = modelMapper.map(entity, OptionDTO.class);
//        if (entity.getCompatibleOption() != null) {
//            optionDTO.setCompatibleOption(convertToDto(entity.getCompatibleOption()));
//        }
//        if (entity.getIncompatibleOption() != null) {
//            optionDTO.setIncompatibleOption(convertToDto(entity.getIncompatibleOption()));
//        }
        return optionDTO;
    }

    @Override
    public List<OptionDTO> getAllOptionsForCustomer(String number) {
        return convertList(optionService.getAllOptionsForCustomer(number));
    }
    @Override
    protected Option convertToEntity(OptionDTO dto) {
        return null;
    }

    @Override
    protected Service<Option> getDefaultService() {
        return null;
    }
}
