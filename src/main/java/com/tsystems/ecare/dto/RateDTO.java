package com.tsystems.ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDTO extends IdDTO {
    @NotNull
    private String name;

    private Integer cost;
    private String description;
    private Integer calls;
    private Integer sms;
    private Integer internet;
    private List<OptionDTO> optionList;
}
