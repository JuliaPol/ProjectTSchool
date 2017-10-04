package com.tsystems.ecare.dto.converter;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component("rateConverter")
public class RateConverter implements Converter<Rate, RateDTO> {
    @Autowired
    private OptionConverter optionConverter;

    @Override
    public RateDTO from(Rate source) {
        RateDTO dto = new RateDTO();
        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setCost(source.getCost());
        dto.setCalls(source.getCalls());
        dto.setSms(source.getSms());
        dto.setInternet(source.getInternet());
        dto.setDescription(source.getDescription() == null ? "" : source.getDescription());
        if (source.getOptionList() != null) {
            List<OptionDTO> optionList = new LinkedList<>();
            for (Option option : source.getOptionList()) {
                optionList.add(optionConverter.from(option));
            }
            dto.setOptionDTOList(optionList);
        }
        return dto;
    }

    @Override
    public Rate to(RateDTO target) throws Exception {
        return null;
    }
}
