package com.tsystems.ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketDTO extends IdDTO {

    private UserDTO user;

    private Long rateId;

    private String rateName;

    private Integer rateCalls;

    private Integer rateSms;

    private Integer rateInternet;

    private Integer rateCost;

    private String rateDescription;

    private List<OptionDTO> optionList;

    private String warning;

}
