package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.service.OptionService;
import com.tsystems.ecare.service.Service;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("optionFacade")
public class OptionFacadeImpl extends FacadeImpl<Option, OptionDTO> implements OptionFacade {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OptionService optionService;

    private static Logger log = Logger.getLogger(OptionFacadeImpl.class.getName());

    @Override
    public OptionDTO convertToDto(Option entity) {
        OptionDTO optionDTO = modelMapper.map(entity, OptionDTO.class);
        List<Option> compOptions = entity.getCompOptions();
        if (compOptions != null) {
            List<String> compOptionsString = compOptions.stream()
                    .map(Option::getName)
                    .collect(Collectors.toList());
            optionDTO.setCompatibleOptions(compOptionsString);
        }
        List<Option> incOptions = entity.getIncOptions();
        if (incOptions != null) {
            List<String> incOptionsString = incOptions.stream()
                    .map(Option::getName)
                    .collect(Collectors.toList());
            optionDTO.setIncompatibleOptions(incOptionsString);
        }
        return optionDTO;
    }

    @Override
    public List<OptionDTO> getAllOptionsForCustomer(String number) {
        return convertList(optionService.getAllOptionsForCustomer(number));
    }

    @Override
    public List<OptionDTO> getAllAvailableOptionsForCustomer(String number) {
        List<Option> optionList = optionService.getAllAvailableOptionsForCustomer(number);
        log.info("Comp Option:");
        for (Option o : optionList) {
            log.info(o.getId() +"  " + o.getName());
        }
        return convertList(optionList);
    }

    @Override
    public List<OptionDTO> getAllIncompatibleOptionsForCustomer(String number) {
        List<Option> optionList = optionService.getAllIncompatibleOptions(number,
                optionService.getAllAvailableOptionsForCustomer(number));
        log.info("Incomp Option:");
        for (Option o : optionList) {
            log.info(o.getId() +"  " + o.getName());
        }
        return convertList(optionList);
    }

    @Override
    public List<OptionDTO> getBy(Long id) {
        return convertList(optionService.getBy(id));
    }

    @Override
    public boolean checkNewOptions(List<OptionDTO> optionList) {
        return optionService.checkNewOptions(convertToEntitiesList(optionList));
    }

    @Override
    public Option convertToEntity(OptionDTO dto) {
        Option option = modelMapper.map(dto, Option.class);
        if (dto.getCompatibleOptions() != null) {
            List<Option> optionList = dto.getCompatibleOptions()
                    .stream()
                    .map(o->optionService.findOptionByName(o))
                    .collect(Collectors.toList());
            option.setCompOptions(optionList);
        }
        if (dto.getIncompatibleOptions() != null) {
            List<Option> optionList = dto.getIncompatibleOptions()
                    .stream()
                    .map(o->optionService.findOptionByName(o))
                    .collect(Collectors.toList());
            option.setIncOptions(optionList);
        }
        return option;
    }

    @Override
    protected Service<Option> getDefaultService() {
        return optionService;
    }
}
