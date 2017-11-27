package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.facade.RateFacade;
import com.tsystems.ecare.service.RateService;
import com.tsystems.ecare.service.Service;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("rateFacade")
public class RateFacadeImpl extends FacadeImpl<Rate, RateDTO> implements RateFacade {

    @Autowired
    private RateService rateService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OptionFacade optionFacade;

    @Autowired
    private JmsTemplate jmsTemplate;

    private static Logger log = Logger.getLogger(RateFacadeImpl.class.getName());

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
    public void editRate(RateDTO rateDTO) {
        rateService.editRate(convertToEntity(rateDTO));
        jmsTemplate.send(s -> s.createTextMessage("edit: rate changed " + rateDTO.getId()));
    }

    @Override
    public void deleteRate(Long id) {
        rateService.deleteRate(id);
        jmsTemplate.send(s -> s.createTextMessage("delete: rate delete " + id));
    }

    @Override
    public void create(RateDTO rateDTO) {
        try {
            if (rateDTO.getImage() == null) {
                rateDTO.setImage("http://localhost:8080/img/picture.png");
            }
            rateService.insert(convertToEntity(rateDTO));
            jmsTemplate.send(s -> s.createTextMessage("create: rate created "));
        } catch (Exception e) {
            log.error("Couldn't create a rate:", e);
        }
    }
}
