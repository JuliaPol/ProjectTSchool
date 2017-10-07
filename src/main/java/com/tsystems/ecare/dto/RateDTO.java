package com.tsystems.ecare.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonSerialize
//@JsonDeserialize
public class RateDTO extends IdDTO{
    @NotNull
    private String name;

    private Integer cost;
    private String description;
    private Integer calls;
    private Integer sms;
    private Integer internet;
    private List<OptionDTO> optionDTOList;
}
