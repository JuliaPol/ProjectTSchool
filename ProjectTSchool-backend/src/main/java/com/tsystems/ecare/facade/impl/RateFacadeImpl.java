package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.facade.RateFacade;
import com.tsystems.ecare.service.RateService;
import com.tsystems.ecare.service.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("rateFacade")
public class RateFacadeImpl extends FacadeImpl<Rate, RateDTO> implements RateFacade {


    @Autowired
    private RateService rateService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OptionFacade optionFacade;

    /**
     * The method converts Tariff {@link Rate}
     * to DTO object {@link RateDTO}.
     *
     * @param entity that will be converted
     * @return converted RateDTO
     */
    @Override
    public RateDTO convertToDto(Rate entity) {
        if (entity == null) {
            return null;
        } else {
            RateDTO rateDTO = modelMapper.map(entity, RateDTO.class);
            if (entity.getOptionList() != null) {
                rateDTO.setOptionList(optionFacade.convertList(entity.getOptionList()));
            }
            return rateDTO;
        }
    }

    @Override
    protected Service<Rate> getDefaultService() {
        return rateService;
    }


    @Override
    public Rate convertToEntity(RateDTO dto) {
        Rate rate = modelMapper.map(dto, Rate.class);
        if (dto.getOptionList() != null) {
            rate.setOptionList(optionFacade.convertToEntitiesList(dto.getOptionList()));
        }
        return rate;
    }

    @Override
    public RateDTO findByName(String name) {
        return convertToDto(rateService.findByName(name));
    }

    @Override
    public List<RateDTO> findAllForCustomer(String number) {
        return convertList(rateService.findAllForCustomer(number));
    }

    @Override
    public void editRate(RateDTO rateDTO) {
        rateService.editRate(convertToEntity(rateDTO));
    }

    @Override
    public RateDTO findForCustomerByNumber(String number) {
        return convertToDto(rateService.findForCustomerByNumber(number));
    }
}
