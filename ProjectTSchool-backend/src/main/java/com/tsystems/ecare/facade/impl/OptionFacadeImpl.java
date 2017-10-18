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
        if (entity.getCompOptions() != null) {
            optionDTO.setCompatibleOptions(convertList(entity.getCompOptions()));
        }
        if (entity.getIncOptions() != null) {
            optionDTO.setIncompatibleOptions(convertList(entity.getIncOptions()));
        }
        return optionDTO;
    }

    @Override
    public List<OptionDTO> getAllOptionsForCustomer(String number) {
        return convertList(optionService.getAllOptionsForCustomer(number));
    }

    @Override
    public List<OptionDTO> getAllAvailableOptionsForCustomer(String number) {
        return convertList(optionService.getAllAvailableOptionsForCustomer(number));
    }

    @Override
    public List<OptionDTO> getAllIncompatibleOptionsForCustomer(String number) {
        return convertList(optionService.getAllIncompatibleOptions(number,
                optionService.getAllAvailableOptionsForCustomer(number)));
    }

    @Override
    public List<OptionDTO> getBy(Long id) {
        return convertList(optionService.getBy(id));
    }

    @Override
    public Option convertToEntity(OptionDTO dto) {
        Option option = modelMapper.map(dto, Option.class);
        if (dto.getCompatibleOptions() != null) {
            option.setCompOptions(convertToEntitiesList(dto.getCompatibleOptions()));
        }
        if (dto.getIncompatibleOptions() != null) {
            option.setIncOptions(convertToEntitiesList(dto.getIncompatibleOptions()));
        }
        return option;
    }

    @Override
    protected Service<Option> getDefaultService() {
        return optionService;
    }
}
