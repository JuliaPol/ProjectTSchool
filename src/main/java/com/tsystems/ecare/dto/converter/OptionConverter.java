package com.tsystems.ecare.dto.converter;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Option;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component("optionConverter")
public class OptionConverter implements Converter<Option, OptionDTO>{
    @Override
    public OptionDTO from(Option source) {
        OptionDTO dto = new OptionDTO();
        dto.setId(source.getId());
        dto.setName(source.getName());
        dto.setCost(source.getCost());
        dto.setCostOfConnection(source.getCostOfConnection());
        dto.setDescription(source.getDescription() == null ? "" : source.getDescription());
        if (source.getCompatibleOption() != null)
            dto.setCompatibleOption(this.from(source.getCompatibleOption()));
        if (source.getIncompatibleOption() != null)
            dto.setIncompatibleOption(this.from(source.getIncompatibleOption()));
        return dto;
    }

    @Override
    public Option to(OptionDTO target) throws Exception {
        return null;
    }
}
