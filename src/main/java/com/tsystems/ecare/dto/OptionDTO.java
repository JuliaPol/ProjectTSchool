package com.tsystems.ecare.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsystems.ecare.dto.deserializer.OptionDeserializer;
import com.tsystems.ecare.dto.serializer.OptionSerializer;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonSerialize
//@JsonDeserialize
public class OptionDTO extends IdDTO{
    @NotNull
    private String name;

    private Integer cost;
    private Integer costOfConnection;
    private String description;
    private OptionDTO compatibleOption;
    private OptionDTO incompatibleOption;
}
