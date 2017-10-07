package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.dto.converter.Converter;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.service.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("optionFacade")
public class OptionFacadeImpl extends FacadeImpl<Option, OptionDTO> implements OptionFacade {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected OptionDTO convertToDto(Option entity) {
        OptionDTO optionDTO = modelMapper.map(entity, OptionDTO.class);
        if (entity.getCompatibleOption() != null) {
            optionDTO.setCompatibleOption(convertToDto(entity.getCompatibleOption()));
        }
        if (entity.getIncompatibleOption() != null) {
            optionDTO.setIncompatibleOption(convertToDto(entity.getIncompatibleOption()));
        }
        return optionDTO;
    }

    @Override
    protected Converter<Option, OptionDTO> getDefaultConverter() {
        return null;
    }

    @Override
    protected Service<Option> getDefaultService() {
        return null;
    }
}