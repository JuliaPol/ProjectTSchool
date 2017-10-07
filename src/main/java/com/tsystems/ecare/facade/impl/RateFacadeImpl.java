package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.dto.converter.Converter;
import com.tsystems.ecare.dto.converter.RateConverter;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.facade.RateFacade;
import com.tsystems.ecare.service.RateService;
import com.tsystems.ecare.service.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("rateFacade")
public class RateFacadeImpl extends FacadeImpl<Rate, RateDTO> implements RateFacade {

    @Autowired
    private RateConverter rateConverter;

    @Autowired
    private RateService rateService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OptionFacadeImpl optionFacade;

    @Override
    protected RateDTO convertToDto(Rate entity) {
        RateDTO rateDTO = modelMapper.map(entity, RateDTO.class);
        if (entity.getOptionList() != null) {
            rateDTO.setOptionDTOList(optionFacade.convertList(entity.getOptionList()));
        }
        return rateDTO;
    }

    @Override
    protected Converter<Rate, RateDTO> getDefaultConverter() {
        return rateConverter;
    }

    @Override
    protected Service<Rate> getDefaultService() {
        return rateService;
    }

    @Override
    public RateDTO findByName(String name) {
        return convertToDto(rateService.findByName(name));
    }
}
