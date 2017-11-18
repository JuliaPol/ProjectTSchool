package com.tsystems.ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String registrationDate;
    private String email;
    private String comment;
    private List<ContractDTO> contractList;
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    public void dateConverter(Date registrationDate) {
        if (registrationDate == null) {
            this.registrationDate = null;
        } else {
            this.registrationDate = dateFormat.format(registrationDate);
        }
    }
}
