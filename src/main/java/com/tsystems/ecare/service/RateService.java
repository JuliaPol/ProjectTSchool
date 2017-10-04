package com.tsystems.ecare.service;

import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.entities.Rate;

public interface RateService extends Service<Rate> {
    RateDTO findByName(String name);
}
