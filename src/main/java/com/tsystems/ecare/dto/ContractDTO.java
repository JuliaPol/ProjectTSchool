package com.tsystems.ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO  extends IdDTO {
    private RateDTO rate;
    private String number;
    private List<OptionDTO> optionList;
}
