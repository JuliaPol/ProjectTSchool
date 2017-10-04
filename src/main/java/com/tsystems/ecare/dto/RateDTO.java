package com.tsystems.ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDTO extends IdDTO{
    private String name;
    private String cost;
    private String description;
    private String calls;
    private String sms;
    private String internet;
    private List<OptionDTO> optionDTOList;
}
