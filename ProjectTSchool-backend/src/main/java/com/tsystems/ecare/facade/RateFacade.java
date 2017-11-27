package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.entities.Rate;

import java.util.List;

public interface RateFacade extends Facade<RateDTO, Rate> {

    void editRate(RateDTO rateDTO);

    void deleteRate(Long id);

    void create(RateDTO rateDTO);
}
