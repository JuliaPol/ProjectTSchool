package com.tsystems.ecare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO  extends IdDTO {
    private RateDTO rate;
    private String number;
    private String status;
    private String creationDate;
    private List<OptionDTO> optionList;
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    public void dateConverter(Date creationDate) {
        if (creationDate == null) {
            this.creationDate = null;
        } else {
            this.creationDate = dateFormat.format(creationDate);
        }
    }

    public Date dateConverterInEntity() throws ParseException {
        if (this.creationDate.equals("")) {
            return null;
        } else {
            return dateFormat.parse(creationDate);
        }
    }
}
