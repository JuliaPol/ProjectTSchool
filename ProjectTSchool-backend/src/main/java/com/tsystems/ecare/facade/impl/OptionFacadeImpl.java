package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.service.OptionService;
import com.tsystems.ecare.service.Service;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("optionFacade")
public class OptionFacadeImpl extends FacadeImpl<Option, OptionDTO> implements OptionFacade {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OptionService optionService;

    @Autowired
    private JmsTemplate jmsTemplate;

    private static Logger log = Logger.getLogger(OptionFacadeImpl.class.getName());

    /**
     * The method converts Option {@link Option}
     * to DTO object {@link OptionDTO}.
     *
     * @param entity that will be converted
     * @return converted OptionDTO
     */
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
        List<Option> compOptionsOf = entity.getCompOptionsOf();
        if (compOptionsOf != null) {
            List<String> compOptionsOfString = compOptionsOf.stream()
                    .map(Option::getName)
                    .collect(Collectors.toList());
            optionDTO.setCompatibleOptionsOf(compOptionsOfString);
        }
        return optionDTO;
    }


    /**
     * The method returns list options that aren't contained in tariff or contract.
     *
     * @param number
     * @return list of free options
     */
    @Override
    public List<OptionDTO> getAllFreeOption(String number) {
        return convertList(optionService.getAllFreeOptions(number));
    }

    /**
     * The method checks if options in list are incompatible
     *
     * @param optionList
     * @return false if incompatible or true if not
     */
    @Override
    public boolean checkNewOptions(List<OptionDTO> optionList, String number) {
        return optionService.checkNewOptions(convertToEntitiesList(optionList), number);
    }

    /**
     * The method checks if options in list are compatible with options in rate and contract
     *
     * @param optionList
     * @return false if incompatible or true if not
     */
    @Override
    public boolean checkNewOptionsForCompatible(List<OptionDTO> optionList, String number) {
        return optionService.checkOptionListForCompatible(convertToEntitiesList(optionList),
                number);
    }

    /**
     * The method converts OptionDTO {@link OptionDTO}
     * to entity object {@link Option}.
     *
     * @param dto that will be converted
     * @return converted Option
     */
    @Override
    public Option convertToEntity(OptionDTO dto) {
        Option option = modelMapper.map(dto, Option.class);
        if (dto.getCompatibleOptions() != null) {
            List<Option> optionList = dto.getCompatibleOptions()
                    .stream()
                    .map(o -> optionService.findOptionByName(o))
                    .collect(Collectors.toList());
            option.setCompOptions(optionList);
        }
        if (dto.getIncompatibleOptions() != null) {
            List<Option> optionList = dto.getIncompatibleOptions()
                    .stream()
                    .map(o -> optionService.findOptionByName(o))
                    .collect(Collectors.toList());
            option.setIncOptions(optionList);
        }
        if (dto.getCompatibleOptionsOf() != null) {
            List<Option> optionList = dto.getCompatibleOptionsOf()
                    .stream()
                    .map(o -> optionService.findOptionByName(o))
                    .collect(Collectors.toList());
            option.setCompOptionsOf(optionList);
        }
        return option;
    }

    /**
     * The method creates an option and sends message.
     *
     * @param optionDTO
     */
    @Override
    public void create(OptionDTO optionDTO) {
        try {
            if (optionDTO.getImage() == null) {
                optionDTO.setImage("http://localhost:8080/img/picture.png");
            }
            optionService.insert(convertToEntity(optionDTO));
            jmsTemplate.send(s -> s.createTextMessage("create: option created "));
        } catch (Exception e) {
            log.error("Couldn't create an option:", e);
        }
    }

    /**
     * The method deletes an option and sends message.
     *
     * @param id option
     */
    @Override
    public void deleteOption(Long id) {
        optionService.deleteOption(id);
        jmsTemplate.send(s -> s.createTextMessage("delete: option deleted " + id));
    }

    /**
     * The method edits an option and sends message.
     *
     * @param optionDTO
     */
    @Override
    public void edit(OptionDTO optionDTO) {
        try {
            optionService.update(convertToEntity(optionDTO));
            jmsTemplate.send(s -> s.createTextMessage("edit: option changed " + optionDTO.getId()));
        } catch (Exception e) {
            log.error("Couldn't edit an option:", e);
        }
    }

    /**
     * The method adds rules for options. They can be compatible or incompatible.
     *
     * @param current      option id
     * @param incomp       list options
     * @param isCompatible options are compatible or not
     */
    @Override
    public void addIncompatible(Long current, List<String> incomp, boolean isCompatible) {
        optionService.addIncompatible(current, incomp, isCompatible);
    }

    @Override
    protected Service<Option> getDefaultService() {
        return optionService;
    }
}
