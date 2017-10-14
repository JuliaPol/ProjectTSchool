package com.tsystems.ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketDTO extends IdDTO {

    private String firstName;

    private String lastName;

    private List<String> contactNumbers;
}
