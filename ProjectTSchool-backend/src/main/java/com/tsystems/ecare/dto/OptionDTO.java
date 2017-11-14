package com.tsystems.ecare.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO extends IdDTO {
    @NotNull
    private String name;

    private Integer cost;
    private Integer costOfConnection;
    private String description;
    private String image;
    private List<String> compatibleOptions;
    private List<String> incompatibleOptions;
    private List<String> compatibleOptionsOf;

}
