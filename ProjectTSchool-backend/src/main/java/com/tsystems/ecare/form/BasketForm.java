package com.tsystems.ecare.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketForm {

    private String number;
    private String rate;
    private List<String> options = new ArrayList<>();
    private Integer countOfProduct;

    public Integer getCountOfProduct() {
        int rateCount = 0;
        if(!rate.isEmpty()) {
            rateCount = 1;
        }
        if (!options.isEmpty()) {
            return options.size() + rateCount;
        }
        return rateCount;
    }
}
