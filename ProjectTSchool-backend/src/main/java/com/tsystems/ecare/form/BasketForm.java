package com.tsystems.ecare.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketForm {

    private String number;
    private String rate;
    private Set<String> options = new HashSet<>();
    private Integer countOfProduct;

    public Integer getCountOfProduct() {
        int rateCount = 0;
        if(rate!= null) {
            rateCount = 1;
        }
        if (!options.isEmpty()) {
            return options.size() + rateCount;
        }
        return rateCount;
    }
}
