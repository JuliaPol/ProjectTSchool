package com.tsystems.ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO {
    private String name;
    private String cost;
    private String costOfConnection;
    private String description;
    private OptionDTO compatibleOption;
    private OptionDTO incompatibleOption;
}
